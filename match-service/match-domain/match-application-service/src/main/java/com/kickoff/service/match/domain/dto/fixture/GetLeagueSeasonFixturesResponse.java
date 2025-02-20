package com.kickoff.service.match.domain.dto.fixture;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.valueobject.FixtureStatusType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Builder
@Data
public class GetLeagueSeasonFixturesResponse {
  public UUID id;
  public String referee;
  public FixtureDateTime fixtureDateTime;
  public Venue venue;
  public FixtureStatus fixtureStatus;
  public Team homeTeam;
  public Team awayTeam;
  public Score halfTimeScore;
  public Score fullTimeScore;
  public Score extraTimeScore;
  public Score penaltyTimeScore;

  public static GetLeagueSeasonFixturesResponse from(Fixture fixture) {
    if (fixture == null) return null;
    return GetLeagueSeasonFixturesResponse.builder()
      .id(fixture.getId().getId())
      .referee(fixture.getReferee())
      .fixtureDateTime(mapFixtureDateTimeFrom(fixture.getFixtureDateTime()))
      .venue(mapVenueFrom(fixture.getVenue()))
      .fixtureStatus(mapFixtureStatusFrom(fixture.getFixtureStatus()))
      .homeTeam(mapTeamFrom(fixture.getHomeTeam()))
      .awayTeam(mapTeamFrom(fixture.getAwayTeam()))
      .halfTimeScore(mapScoreFrom(fixture.getHalfTimeScore()))
      .fullTimeScore(mapScoreFrom(fixture.getFullTimeScore()))
      .extraTimeScore(mapScoreFrom(fixture.getExtraTimeScore()))
      .penaltyTimeScore(mapScoreFrom(fixture.getPenaltyTimeScore()))
      .build();
  }

  private static FixtureDateTime mapFixtureDateTimeFrom(com.kickoff.service.match.domain.valueobject.FixtureDateTime fixtureDateTime) {
    if (fixtureDateTime == null) return null;
    return FixtureDateTime.builder()
      .dateTimeZone(fixtureDateTime.getDateTimeZone())
      .date(fixtureDateTime.getDate())
      .timestamp(fixtureDateTime.getTimestamp())
      .firstPeriod(fixtureDateTime.getFirstPeriod())
      .secondPeriod(fixtureDateTime.getSecondPeriod())
      .build();
  }

  private static Venue mapVenueFrom(com.kickoff.service.match.domain.entity.Venue venue) {
    if (venue == null) return null;
    return Venue.builder()
      .id(venue.getId().getId())
      .name(venue.getName())
      .city(venue.getCity())
      .image(venue.getImage())
      .build();
  }

  private static FixtureStatus mapFixtureStatusFrom(com.kickoff.service.match.domain.valueobject.FixtureStatus fixtureStatus) {
    if (fixtureStatus == null) return null;
    FixtureStatusType fixtureStatusType = fixtureStatus.getFixtureStatusType();
    return FixtureStatus.builder()
      .code(fixtureStatusType.getCode())
      .name(fixtureStatusType.getName())
      .type(fixtureStatusType.getType())
      .description(fixtureStatusType.getDescription())
      .elapsed(fixtureStatus.getElapsed())
      .extra(fixtureStatus.getExtra())
      .build();
  }

  private static Team mapTeamFrom(com.kickoff.service.match.domain.entity.Team team) {
    if (team == null) return null;
    return Team.builder()
      .id(team.getId().getId())
      .name(team.getName())
      .code(team.getCode())
      .build();
  }

  private static Score mapScoreFrom(com.kickoff.service.match.domain.valueobject.Score score) {
    if (score == null) return null;
    return Score.builder()
      .home(score.getHome())
      .away(score.getAway())
      .build();
  }

  @Builder
  @Data
  public static class FixtureDateTime {
    public ZoneId dateTimeZone;
    public LocalDateTime date;
    public Long timestamp;
    public Long firstPeriod;
    public Long secondPeriod;
  }

  @Builder
  @Data
  public static class Venue {
    public UUID id;
    public String name;
    public String city;
    public String image;
  }

  @Builder
  @Data
  public static class FixtureStatus {
    public String code;
    public String name;
    public String type;
    public String description;
    public Integer elapsed;
    public String extra;
  }

  @Builder
  @Data
  public static class Team {
    public UUID id;
    public String name;
    public String code;
  }

  @Builder
  @Data
  public static class Score {
    public Integer home;
    public Integer away;
  }
}
