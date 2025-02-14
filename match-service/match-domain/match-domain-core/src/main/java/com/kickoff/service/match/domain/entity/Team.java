package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.Logo;
import com.kickoff.service.match.domain.valueobject.TeamId;
import com.kickoff.service.match.domain.valueobject.Venue;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  @ElementCollection
  @CollectionTable(name = "team_logos", joinColumns = @JoinColumn(name = "team_id"))
  private List<Logo> logos = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "team_venue", joinColumns = @JoinColumn(name = "team_id"))
  private List<Venue> venues = new ArrayList<>();

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

  public void addVenue(Venue venue) {
    venues.add(venue);
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
