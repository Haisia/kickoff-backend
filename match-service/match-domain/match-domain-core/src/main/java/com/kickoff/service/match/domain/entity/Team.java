package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.Logo;
import com.kickoff.service.match.domain.valueobject.TeamId;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@Getter @Setter
@Table(name = "teams")
@Entity
public class Team extends BaseEntity {
  @EmbeddedId
  private TeamId id;
  @Column(unique = true)
  private Long apiFootballTeamId;
  private String name;
  private String code;
  private String country;
  private Integer founded;
  private Boolean national;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "league_id")
  private League league;

  @ElementCollection
  @CollectionTable(name = "team_logos", joinColumns = @JoinColumn(name = "team_id"))
  private List<Logo> logos = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
  private List<Venue> venues = new ArrayList<>();

  @OneToMany(mappedBy = "currentTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Player> players = new ArrayList<>();

  @Builder
  public Team(TeamId id, Long apiFootballTeamId, String name, String code, String country, Integer founded, Boolean national) {
    if (id == null) id = TeamId.generate();
    this.id = id;
    this.apiFootballTeamId = apiFootballTeamId;
    this.name = name;
    this.code = code;
    this.country = country;
    this.founded = founded;
    this.national = national;
  }

  public void addLogo(Logo logo) {
    logos.add(logo);
  }

  public String getLogoUrlAnything() {
    if (logos.isEmpty()) return "";
    return logos.getFirst().getUrl();
  }

  public void addVenue(Venue venue) {
    if (venue == null) return;
    venue.setTeam(this);
    venues.add(venue);
  }

  public void addPlayer(Player player) {
    if (player == null) return;
    if (hasPlayer(player)) return;

    player.setCurrentTeam(this);
    players.add(player);
  }

  public void addPlayers(List<Player> players) {
    players.forEach(this::addPlayer);
  }

  public boolean hasPlayer(Player player) {
    if(players.contains(player)) return true;
    if(getPlayerByApiFootballPlayerId(player.getApiFootballPlayerId()).isPresent()) return true;
    return false;
  }

  public Optional<Player> getPlayerByApiFootballPlayerId(Long apiFootballTeamId) {
    return players.stream()
      .filter(player -> player.getApiFootballPlayerId().equals(apiFootballTeamId))
      .findFirst()
      ;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Team team = (Team) o;
    return Objects.equals(id, team.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
