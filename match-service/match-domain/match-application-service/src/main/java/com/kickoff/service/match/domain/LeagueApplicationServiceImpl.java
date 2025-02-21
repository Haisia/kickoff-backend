package com.kickoff.service.match.domain;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.port.input.*;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import com.kickoff.service.match.domain.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LeagueApplicationServiceImpl implements LeagueApiPullUseCase, TeamApiPullUseCase, PlayerApiPullUseCase, GetLeagueUseCase, FixtureApiPullUseCase, GetFixtureUseCase, GetHeadToHeadUseCase {

  private final LeagueApiPullHandler leagueApiPullHandler;
  private final TeamApiPullHandler teamApiPullHandler;
  private final PlayerApiPullHandler playerApiPullHandler;
  private final LeagueRankingGetHandler leagueRankingGetHandler;
  private final FixtureApiPullHandler fixtureApiPullHandler;
  private final LeagueFixtureGetHandler leagueFixtureGetHandler;

  private final LeagueRepository leagueRepository;

  @Transactional
  @Override
  public void leagueApiPull() {
    leagueApiPullHandler.leagueApiPull();
  }

  @Transactional
  @Override
  public void initRanking() {
    leagueApiPullHandler.initRanking();
  }

  @Transactional
  @Override
  public void teamApiPullAndMappingSeason() {
    teamApiPullHandler.teamApiPullAndMappingSeason();
  }

  @Transactional
  @Override
  public void playerApiPull() {
    playerApiPullHandler.playerApiPull();
  }

  @Transactional
  @Override
  public ResponseContainer<LeagueTeamsResponse> getLeagueSeasonRanking(LeagueSeasonQuery query) {
    LeagueTeamsResponse leagueSeasonRanking = leagueRankingGetHandler.getLeagueSeasonRanking(LeagueId.of(query.getLeagueId()), query.getYear());
    return new ResponseContainer<>(query, List.of(leagueSeasonRanking));
  }

  @Transactional
  @Override
  public ResponseContainer<LeagueTeamsResponse> getLeagueSeasonRankingForMainPage() {
    List<LeagueTeamsResponse> leagueSeasonRankingForMainPage = leagueRankingGetHandler.getLeagueSeasonRankingForMainPage();
    return new ResponseContainer<>("", leagueSeasonRankingForMainPage);
  }

  @Transactional
  @Override
  public void initFixtures() {
    fixtureApiPullHandler.initFixtures();
  }

  @Transactional
  @Override
  public ResponseContainer<FixtureResponse> getLeagueSeasonFixture(LeagueFixtureQuery query) {
    LeagueId leagueId = LeagueId.of(query.getLeagueId());
    FixtureId fixtureId = FixtureId.of(query.getFixtureId());

    League league = leagueRepository.findById(leagueId).orElseThrow();
    Fixture fixture = league.getFixture(query.getYear(), fixtureId).orElseThrow();

    return new ResponseContainer<>(query, List.of(FixtureResponse.from(fixture)));
  }

  @Transactional
  @Override
  public ResponseContainer<FixtureResponse> getLeagueSeasonFixtures(LeagueSeasonQuery query) {
    return leagueFixtureGetHandler.getLeagueSeasonFixtures(query);
  }

  @Transactional
  @Override
  public ResponseContainer<LeagueFixtureResponse> getLeagueSeasonFixturesForMainPage() {
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

  @Transactional
  @Override
  public ResponseContainer<LeagueFixtureResponse> getLeagueSeasonInPlayFixtures() {
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

  @Transactional
  @Override
  public ResponseContainer<FixtureResponse> getHeadToHeadSimple(LeagueFixtureQuery query) {
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
