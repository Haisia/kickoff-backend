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
public class FixtureResponse {
  UUID id;
  String referee;
  FixtureDateTime fixtureDateTime;
  Venue venue;
  FixtureStatus fixtureStatus;
  Team homeTeam;
  Team awayTeam;
  Score halfTimeScore;
  Score fullTimeScore;
  Score extraTimeScore;
  Score penaltyTimeScore;

  public static FixtureResponse from(Fixture fixture) {
    if (fixture == null) return null;
    return FixtureResponse.builder()
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
      .progressStatus(fixtureStatusType.getProgressStatus().name())
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
      .logo(team.getLogoUrlAnything())
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
  private static class FixtureDateTime {
    public ZoneId dateTimeZone;
    public LocalDateTime date;
    public Long timestamp;
    public Long firstPeriod;
    public Long secondPeriod;
  }

  @Builder
  @Data
  private static class Venue {
    public UUID id;
    public String name;
    public String city;
    public String image;
  }

  @Builder
  @Data
  private static class FixtureStatus {
    public String code;
    public String name;
    public String progressStatus;
    public String description;
    public Integer elapsed;
    public String extra;
  }

  @Builder
  @Data
  private static class Team {
    public UUID id;
    public String name;
    public String code;
    public String logo;
  }

  @Builder
  @Data
  private static class Score {
    public Integer home;
    public Integer away;
  }
}
