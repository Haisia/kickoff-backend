package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.match.domain.valueobject.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "fixtures")
@Entity
public class Fixture extends BaseEntity {
  @EmbeddedId
  private FixtureId id;
  @Column(unique = true)
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

  @OneToMany(mappedBy = "fixture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<FixtureStatistic> fixtureStatistics = new ArrayList<>();


  @OneToMany(mappedBy = "fixture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<FixtureComment> comments;

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

  public FixtureComment createComment(String comment, MemberId createdBy) {
    FixtureComment createdFixtureComment = FixtureComment.builder()
      .comment(comment)
      .fixture(this)
      .createdBy(createdBy)
      .build();
    this.comments.add(createdFixtureComment);
    return createdFixtureComment;
  }

  public void addFixtureStatistic(FixtureStatistic fixtureStatistic) {
    fixtureStatistic.setFixture(this);

    getFixtureStatistic(fixtureStatistic).ifPresentOrElse(
      (fs) -> fs.setValue(fixtureStatistic.getValue()),
      () -> fixtureStatistics.add(fixtureStatistic)
    );
  }

  public Optional<FixtureStatistic> getFixtureStatistic(FixtureStatistic fixtureStatistic) {
    return fixtureStatistics.stream()
      .filter(fs ->
        fs.getTeam().equals(fixtureStatistic.getTeam()) &&
        fs.getType().equals(fixtureStatistic.getType())
      ).findFirst()
      ;
  }

  public void updateFixture(Fixture fixture) {
    if(fixture.fixtureStatus != null) this.fixtureStatus = fixture.fixtureStatus;
    if(fixture.halfTimeScore != null) this.halfTimeScore = fixture.halfTimeScore;
    if(fixture.fullTimeScore != null) this.fullTimeScore = fixture.fullTimeScore;
    if(fixture.extraTimeScore != null) this.extraTimeScore = fixture.extraTimeScore;
    if(fixture.penaltyTimeScore != null) this.penaltyTimeScore = fixture.penaltyTimeScore;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Fixture fixture = (Fixture) o;
    return Objects.equals(id, fixture.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
