package com.kickoff.service.match.externalapi.mapper.helper;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.entity.Venue;
import com.kickoff.service.match.domain.valueobject.FixtureDateTime;
import com.kickoff.service.match.domain.valueobject.FixtureStatus;
import com.kickoff.service.match.domain.valueobject.FixtureStatusType;
import com.kickoff.service.match.domain.valueobject.Score;
import com.kickoff.service.match.externalapi.dto.rapidapi.fixtures.FixtureDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.fixtures.FixturesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.VenueDto;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.time.ZoneId;

@Component
public class FixturesResponseToFixtureHelper {
  public Fixture fixturesResponseToFixture(FixturesResponse fixturesResponse, League league) {
    FixtureDto fixtureDto = fixturesResponse.getFixture();
    FixturesResponse.LeagueDto leagueDto = fixturesResponse.getLeague();
    FixturesResponse.TeamsDto teamsDto = fixturesResponse.getTeams();
    FixturesResponse.ScoreDto goalsDto = fixturesResponse.getGoals();
    FixturesResponse.ScoresDto scoresDto = fixturesResponse.getScore();
    FixtureDto.StatusDto status = fixtureDto.getStatus();
    Season season = league.getSeasonByYear(Year.of(leagueDto.getSeason())).orElse(null);

    return Fixture.builder()
      .apiFootballFixtureId(fixtureDto.getId())
      .season(season)
      .referee(fixtureDto.getReferee())
      .fixtureDateTime(toFixtureDateTime(fixtureDto))
      .venue(toVenue(fixtureDto.getVenue(), league))
      .fixtureStatus(toFixtureStatus(status))
      .homeTeam(league.getTeamByApIFootballTeamId(teamsDto.getHome().getId()).orElse(null))
      .awayTeam(league.getTeamByApIFootballTeamId(teamsDto.getAway().getId()).orElse(null))
      .halfTimeScore(new Score(scoresDto.getHalftime().getHome(), scoresDto.getHalftime().getAway()))
      .fullTimeScore(new Score(scoresDto.getFulltime().getHome(), scoresDto.getFulltime().getAway()))
      .extraTimeScore(new Score(scoresDto.getExtratime().getHome(), scoresDto.getExtratime().getAway()))
      .penaltyTimeScore(new Score(scoresDto.getPenalty().getHome(), scoresDto.getPenalty().getAway()))
      .build();
  }

  private FixtureDateTime toFixtureDateTime(FixtureDto fixtureDto) {
    Long firstPeriod = fixtureDto.getPeriods() != null ? fixtureDto.getPeriods().getFirst() : null;
    Long secondPeriod = fixtureDto.getPeriods() != null ? fixtureDto.getPeriods().getSecond() : null;
    return FixtureDateTime.builder()
      .dateTimeZone(ZoneId.of(fixtureDto.getTimezone()))
      .date(fixtureDto.getDate().toLocalDateTime())
      .timestamp(fixtureDto.getTimestamp())
      .firstPeriod(firstPeriod)
      .secondPeriod(secondPeriod)
      .build();
  }

  private Venue toVenue(VenueDto venueDto, League league) {
    return league.getVenueByApiFootballVenueId(venueDto.getId())
      .orElse(null);
  }

  private FixtureStatus toFixtureStatus(FixtureDto.StatusDto statusDto) {
    return FixtureStatus.builder()
      .fixtureStatusType(FixtureStatusType.parseCodeIgnoreCase(statusDto.getShortName()))
      .elapsed(statusDto.getElapsed())
      .extra(statusDto.getExtra())
      .build();
  }
}
