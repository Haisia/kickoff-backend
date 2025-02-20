package com.kickoff.service.match.domain;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesForMainPageResponse;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesInPlayResponse;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesQuery;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesResponse;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingQuery;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.port.input.*;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LeagueApplicationServiceImpl implements LeagueApiPullUseCase, TeamApiPullUseCase, PlayerApiPullUseCase, GetLeagueUseCase, FixtureApiPullUseCase, GetFixtureUseCase {

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
  public ResponseContainer<GetLeagueSeasonRankingResponse> getLeagueSeasonRanking(GetLeagueSeasonRankingQuery query) {
    GetLeagueSeasonRankingResponse leagueSeasonRanking = leagueRankingGetHandler.getLeagueSeasonRanking(LeagueId.of(query.getLeagueId()), query.getYear());
    return new ResponseContainer<>(query, List.of(leagueSeasonRanking));
  }

  @Transactional
  @Override
  public ResponseContainer<GetLeagueSeasonRankingResponse> getLeagueSeasonRankingForMainPage() {
    List<GetLeagueSeasonRankingResponse> leagueSeasonRankingForMainPage = leagueRankingGetHandler.getLeagueSeasonRankingForMainPage();
    return new ResponseContainer<>("", leagueSeasonRankingForMainPage);
  }

  @Transactional
  @Override
  public void initFixtures() {
    fixtureApiPullHandler.initFixtures();
  }

  @Transactional
  @Override
  public ResponseContainer<GetLeagueSeasonFixturesResponse> getLeagueSeasonFixtures(GetLeagueSeasonFixturesQuery query) {
    return leagueFixtureGetHandler.getLeagueSeasonFixtures(query);
  }

  @Transactional
  @Override
  public ResponseContainer<GetLeagueSeasonFixturesForMainPageResponse> getLeagueSeasonFixturesForMainPage() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    List<GetLeagueSeasonFixturesForMainPageResponse> result = new ArrayList<>();

    for (League league : leagues) {
      Year year = league.getLatestSeasonYear();
      Season season = league.getSeasonByYear(year).orElseThrow();
      List<GetLeagueSeasonFixturesResponse> responses = season.findFixturesWithinTwoWeeks()
        .stream()
        .map(GetLeagueSeasonFixturesResponse::from)
        .toList();

      result.add(GetLeagueSeasonFixturesForMainPageResponse.from(league, year.getValue(), responses));
    }
    return new ResponseContainer<>("", result);
  }

  @Transactional
  @Override
  public ResponseContainer<GetLeagueSeasonFixturesInPlayResponse> getLeagueSeasonInPlayFixtures() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    List<GetLeagueSeasonFixturesInPlayResponse> result = new ArrayList<>();

    for (League league : leagues) {
      Year year = league.getLatestSeasonYear();
      Season season = league.getSeasonByYear(year).orElseThrow();
      List<GetLeagueSeasonFixturesResponse> responses = league.getInPlayFixture()
        .stream()
        .map(GetLeagueSeasonFixturesResponse::from)
        .toList();

      result.add(GetLeagueSeasonFixturesInPlayResponse.from(league, year.getValue(), responses));
    }

    return new ResponseContainer<>("", result);
  }
}
