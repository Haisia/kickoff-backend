package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.FixtureDateTime;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import com.kickoff.service.match.domain.valueobject.FixtureStatus;
import com.kickoff.service.match.domain.valueobject.Score;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "fixtures")
@Entity
public class Fixture extends BaseEntity {
  @EmbeddedId
  private FixtureId id;
  private Long apiFootballFixtureId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "season_id", nullable = false)
  private Season season;
  // 심판 이름
  private String referee;
  private FixtureDateTime fixtureDateTime;

  @ManyToOne
  @JoinColumn(name = "venue_id")
  private Venue venue;

  @Embedded
  private FixtureStatus fixtureStatus;

  @ManyToOne
  @JoinColumn(name = "home_team_id")
  private Team homeTeam;
  @ManyToOne
  @JoinColumn(name = "away_team_id")
  private Team awayTeam;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "half_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "half_away_score")),
  })
  @Embedded
  private Score halfTimeScore;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "full_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "full_away_score")),
  })
  @Embedded
  private Score fullTimeScore;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "extra_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "extra_away_score")),
  })
  @Embedded
  private Score extraTimeScore;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "penalty_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "penalty_away_score")),
  })
  @Embedded
  private Score penaltyTimeScore;

  @Builder
  public Fixture(FixtureId id, Long apiFootballFixtureId, Season season, String referee, FixtureDateTime fixtureDateTime, Venue venue, FixtureStatus fixtureStatus, Team homeTeam, Team awayTeam, Score halfTimeScore, Score fullTimeScore, Score extraTimeScore, Score penaltyTimeScore) {
    if (id == null) id = FixtureId.generate();
    this.id = id;
    this.apiFootballFixtureId = apiFootballFixtureId;
    this.season = season;
    this.referee = referee;
    this.fixtureDateTime = fixtureDateTime;
    this.venue = venue;
    this.fixtureStatus = fixtureStatus;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.halfTimeScore = halfTimeScore;
    this.fullTimeScore = fullTimeScore;
    this.extraTimeScore = extraTimeScore;
    this.penaltyTimeScore = penaltyTimeScore;
  }
}
