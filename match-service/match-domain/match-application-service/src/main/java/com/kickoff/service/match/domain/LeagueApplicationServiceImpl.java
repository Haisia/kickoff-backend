package com.kickoff.service.match.domain;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixturesWithLeagueResponse;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesQuery;
import com.kickoff.service.match.domain.dto.fixture.GetFixtureResponse;
import com.kickoff.service.match.domain.dto.headtohead.GetHeadToHeadSimpleQuery;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingQuery;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingResponse;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.port.input.*;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
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
  private final FixtureRepository fixtureRepository;

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
  public ResponseContainer<GetFixtureResponse> getLeagueSeasonFixtures(GetLeagueSeasonFixturesQuery query) {
    return leagueFixtureGetHandler.getLeagueSeasonFixtures(query);
  }

  @Transactional
  @Override
  public ResponseContainer<FixturesWithLeagueResponse> getLeagueSeasonFixturesForMainPage() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    List<FixturesWithLeagueResponse> result = new ArrayList<>();

    for (League league : leagues) {
      Year year = league.getLatestSeasonYear();
      Season season = league.getSeasonByYear(year).orElseThrow();
      List<GetFixtureResponse> responses = season.findFixturesWithinTwoWeeks()
        .stream()
        .map(GetFixtureResponse::from)
        .toList();

      result.add(FixturesWithLeagueResponse.from(league, year.getValue(), responses));
    }
    return new ResponseContainer<>("", result);
  }

  @Transactional
  @Override
  public ResponseContainer<FixturesWithLeagueResponse> getLeagueSeasonInPlayFixtures() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    List<FixturesWithLeagueResponse> result = new ArrayList<>();

    for (League league : leagues) {
      Year year = league.getLatestSeasonYear();
      Season season = league.getSeasonByYear(year).orElseThrow();
      List<GetFixtureResponse> responses = league.getInPlayFixture()
        .stream()
        .map(GetFixtureResponse::from)
        .toList();

      result.add(FixturesWithLeagueResponse.from(league, year.getValue(), responses));
    }

    return new ResponseContainer<>("", result);
  }

  @Transactional
  @Override
  public ResponseContainer<GetFixtureResponse> getFixtureById(FixtureId fixtureId) {
    Fixture fixture = fixtureRepository.findById(fixtureId).orElseThrow();
    GetFixtureResponse response = GetFixtureResponse.from(fixture);
    return new ResponseContainer<>(fixtureId, List.of(response));
  }

  @Transactional
  @Override
  public ResponseContainer<GetFixtureResponse> getHeadToHeadSimple(GetHeadToHeadSimpleQuery query) {
    TeamId teamId1 = TeamId.of(query.getTeamIds().getFirst());
    TeamId teamId2 = TeamId.of(query.getTeamIds().getLast());

    List<GetFixtureResponse> responses = leagueFixtureGetHandler.getHeadToHeadSimple(teamId1, teamId2);
    return new ResponseContainer<>(query, responses);
  }

  @Transactional
  @Override
  public ResponseContainer<GetFixtureResponse> getHeadToHeadSimple(FixtureId fixtureId) {
    Fixture fixture = fixtureRepository.findById(fixtureId).orElseThrow();
    Team homeTeam = fixture.getHomeTeam();
    Team awayTeam = fixture.getAwayTeam();
    List<GetFixtureResponse> responses = leagueFixtureGetHandler.getHeadToHeadSimple(homeTeam.getId(), awayTeam.getId());
    return new ResponseContainer<>(fixtureId, responses);
  }
}
