package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.RankStatus;
import com.kickoff.service.match.domain.valueobject.ScoreStats;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Table(name = "season_map_team", uniqueConstraints = @UniqueConstraint(columnNames = {"season_id", "team_id"}))
@Embeddable
public class SeasonMapTeam extends BaseEntity {

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "season_id", nullable = false)
  private Season season;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  private Team team;

  private Integer rank;
  @Enumerated(EnumType.STRING)
  private RankStatus rankStatus;
  // 승점. 승-3, 무-1, 패-0 의 합
  private Integer points;
  private Integer goalsDiff;
  // 최근 전적. 예) WDWWL
  private String form;

  @AttributeOverrides({
    @AttributeOverride(name = "played", column = @Column(name = "all_played")),
    @AttributeOverride(name = "win", column = @Column(name = "all_win")),
    @AttributeOverride(name = "draw", column = @Column(name = "all_draw")),
    @AttributeOverride(name = "lose", column = @Column(name = "all_lose")),
    @AttributeOverride(name = "goalsFor", column = @Column(name = "all_goals_for")),
    @AttributeOverride(name = "goalsAgainst", column = @Column(name = "all_goals_against"))
  })
  @Embedded
  private ScoreStats allScoreStats;
  @AttributeOverrides({
    @AttributeOverride(name = "played", column = @Column(name = "home_played")),
    @AttributeOverride(name = "win", column = @Column(name = "home_win")),
    @AttributeOverride(name = "draw", column = @Column(name = "home_draw")),
    @AttributeOverride(name = "lose", column = @Column(name = "home_lose")),
    @AttributeOverride(name = "goalsFor", column = @Column(name = "home_goals_for")),
    @AttributeOverride(name = "goalsAgainst", column = @Column(name = "home_goals_against"))
  })
  @Embedded
  private ScoreStats homeScoreStats;
  @AttributeOverrides({
    @AttributeOverride(name = "played", column = @Column(name = "away_played")),
    @AttributeOverride(name = "win", column = @Column(name = "away_win")),
    @AttributeOverride(name = "draw", column = @Column(name = "away_draw")),
    @AttributeOverride(name = "lose", column = @Column(name = "away_lose")),
    @AttributeOverride(name = "goalsFor", column = @Column(name = "away_goals_for")),
    @AttributeOverride(name = "goalsAgainst", column = @Column(name = "away_goals_against"))
  })
  @Embedded
  private ScoreStats awayScoreStats;

  @Builder
  public SeasonMapTeam(Season season, Team team, Integer rank, RankStatus rankStatus, Integer points, Integer goalsDiff, String form, ScoreStats allScoreStats, ScoreStats homeScoreStats, ScoreStats awayScoreStats) {
    this.season = season;
    this.team = team;
    this.rank = rank;
    this.rankStatus = rankStatus;
    this.points = points;
    this.goalsDiff = goalsDiff;
    this.form = form;
    this.allScoreStats = allScoreStats;
    this.homeScoreStats = homeScoreStats;
    this.awayScoreStats = awayScoreStats;
  }
}