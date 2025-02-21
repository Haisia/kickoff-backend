package com.kickoff.service.match.domain.service.query;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.exception.LeagueDomainException;
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
      .orElseThrow(() -> new LeagueDomainException(String.format("[*] league를 찾을 수 없습니다. : leagueId=%s", query.getLeagueId()), CustomHttpStatus.BAD_REQUEST));

    Season season = league.getSeasonByYear(query.getYear())
      .orElseThrow(() -> new LeagueDomainException(String.format("[*] season을 찾을 수 없습니다. : leagueId=%s, seasonId=%s", query.getLeagueId(), query.getYear()), CustomHttpStatus.BAD_REQUEST));

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
      Season season = league.getSeasonByYear(year).orElseThrow();
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
      Season season = league.getSeasonByYear(year).orElseThrow();
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

    League league = leagueRepository.findById(leagueId).orElseThrow();
    Fixture fixture = league.getFixture(query.getYear(), fixtureId).orElseThrow();

    TeamId homeTeamId = fixture.getHomeTeam().getId();
    TeamId awayTeamId = fixture.getAwayTeam().getId();
    List<FixtureResponse> response = league.getH2HRecent5Games(homeTeamId, awayTeamId).stream()
      .map(FixtureResponse::from)
      .toList();

    return new ResponseContainer<>(query, response);
  }
}
