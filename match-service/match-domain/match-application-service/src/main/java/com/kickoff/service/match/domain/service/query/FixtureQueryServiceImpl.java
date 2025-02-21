package com.kickoff.service.match.domain.service.query;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.exception.FixtureNotFoundException;
import com.kickoff.service.match.domain.exception.LeagueNotFoundException;
import com.kickoff.service.match.domain.exception.SeasonNotFoundException;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
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
public class FixtureQueryServiceImpl implements FixtureQueryService {

  private final LeagueRepository leagueRepository;

  @Override
  public ResponseContainer<FixtureResponse> fixtureGet(LeagueFixtureQuery query) {
    LeagueId leagueId = LeagueId.of(query.getLeagueId());
    FixtureId fixtureId = FixtureId.of(query.getFixtureId());

    League league = leagueRepository.findById(leagueId).orElseThrow();
    Fixture fixture = league.getFixture(query.getYear(), fixtureId).orElseThrow();

    return new ResponseContainer<>(query, List.of(FixtureResponse.from(fixture)));
  }

  @Override
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

  @Override
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

  @Override
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

  @Override
  public ResponseContainer<FixtureResponse> fixtureH2HSimpleList(LeagueFixtureQuery query) {
    LeagueId leagueId = LeagueId.of(query.getLeagueId());
    FixtureId fixtureId = FixtureId.of(query.getFixtureId());

    League league = leagueRepository.findById(leagueId).orElseThrow(() -> new LeagueNotFoundException(leagueId));
    Fixture fixture = league.getFixture(query.getYear(), fixtureId).orElseThrow(() -> new FixtureNotFoundException(query.getYear(), fixtureId));

    TeamId homeTeamId = fixture.getHomeTeam().getId();
    TeamId awayTeamId = fixture.getAwayTeam().getId();
    List<FixtureResponse> response = league.getH2HRecent5Games(homeTeamId, awayTeamId).stream()
      .map(FixtureResponse::from)
      .toList();

    return new ResponseContainer<>(query, response);
  }
}
