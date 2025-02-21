package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import com.kickoff.service.match.domain.valueobject.SeasonId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "seasons")
@Entity
public class Season extends BaseEntity {
  @EmbeddedId
  private SeasonId id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "league_id", nullable = false)
  private League league;
  private Year year;
  private LocalDate startDate;
  private LocalDate endDate;

  @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Fixture> fixtures = new ArrayList<>();

  @Builder
  public Season(SeasonId id, League league, Year year, LocalDate startDate, LocalDate endDate) {
    if (id == null) id = SeasonId.generate();
    this.id = id;
    this.league = league;
    this.year = year;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void addFixture(Fixture fixture) {
    if (fixture == null) return;
    fixture.setSeason(this);
    fixtures.add(fixture);
  }

  public Optional<Fixture> getFixture(FixtureId fixtureId) {
    return fixtures.stream()
      .filter(fixture -> fixture.getId().equals(fixtureId))
      .findFirst();
  }

  public List<Fixture> findFixturesWithinTwoWeeks() {
    LocalDateTime startRange = LocalDate.now().minusWeeks(2).atStartOfDay();
    LocalDateTime endRange = LocalDate.now().plusWeeks(2).plusDays(1).atStartOfDay();

    return fixtures.stream()
      .filter(fixture -> {
        LocalDateTime fixtureDate = fixture.getFixtureDateTime().getDate();
        return fixtureDate.isAfter(startRange) && fixtureDate.isBefore(endRange);
      })
      .collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Season season = (Season) o;
    return Objects.equals(id, season.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
