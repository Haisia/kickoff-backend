package com.kickoff.service.match.domain.service.query;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixtureQuery;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.exception.LeagueNotFoundException;
import com.kickoff.service.match.domain.exception.SeasonNotFoundException;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.match.domain.service.query.helper.FixtureGetQueryHelper;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import com.kickoff.service.match.domain.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FixtureQueryService {

  private final LeagueRepository leagueRepository;
  private final FixtureGetQueryHelper fixtureGetQueryHelper;

  public ResponseContainer<FixtureResponse> fixtureGet(FixtureQuery query) {
    FixtureId fixtureId = FixtureId.of(query.getFixtureId());
    com.kickoff.service.match.domain.entity.Fixture fixture = fixtureGetQueryHelper.fixtureGet(fixtureId);
    return new ResponseContainer<>(query, List.of(FixtureResponse.from(fixture)));
  }

  public ResponseContainer<FixtureResponse> fixtureList(LeagueSeasonQuery query) {
    League league = leagueRepository.findById(LeagueId.of(query.getLeagueId()))
      .orElseThrow(() -> new LeagueNotFoundException(query.getLeagueId()));

    Season season = league.getSeasonByYear(query.getYear())
      .orElseThrow(() -> new SeasonNotFoundException(query.getYear(), league.getId()));

    List<FixtureResponse> responses = season.getFixtures()
      .stream()
      .map(FixtureResponse::from)
      .toList();

    return new ResponseContainer<>(query, responses);
  }

  public ResponseContainer<LeagueFixtureResponse> fixtureMainList() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    List<LeagueFixtureResponse> result = new ArrayList<>();

    for (League league : leagues) {
      Year year = league.getLatestSeasonYear();
      Season season = league.getSeasonByYear(year).orElseThrow(() -> new SeasonNotFoundException(year, league.getId()));
      List<FixtureResponse> responses = season.findFixturesWithinTwoWeeks()
        .stream()
        .map(FixtureResponse::from)
        .toList();

      result.add(LeagueFixtureResponse.from(league, year.getValue(), responses));
    }
    return new ResponseContainer<>("", result);
  }

  public ResponseContainer<LeagueFixtureResponse> fixtureInPlayList() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    List<LeagueFixtureResponse> result = new ArrayList<>();

    for (League league : leagues) {
      Year year = league.getLatestSeasonYear();
      List<FixtureResponse> responses = league.getInPlayFixture()
        .stream()
        .map(FixtureResponse::from)
        .toList();

      result.add(LeagueFixtureResponse.from(league, year.getValue(), responses));
    }

    return new ResponseContainer<>("", result);
  }

  public ResponseContainer<FixtureResponse> fixtureH2HSimpleList(FixtureQuery query) {
    FixtureId fixtureId = FixtureId.of(query.getFixtureId());
    com.kickoff.service.match.domain.entity.Fixture fixture = fixtureGetQueryHelper.fixtureGet(fixtureId);
    League league = fixture.getSeason().getLeague();
    TeamId homeTeamId = fixture.getHomeTeam().getId();
    TeamId awayTeamId = fixture.getAwayTeam().getId();

    List<FixtureResponse> response = league.getH2HRecent5Games(homeTeamId, awayTeamId).stream()
      .map(FixtureResponse::from)
      .toList();

    return new ResponseContainer<>(query, response);
  }
}
