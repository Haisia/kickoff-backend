package com.kickoff.service.match.domain.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * ❗❗❗❗
 * `Command` Service 이므로 LeagueRepository 외의 repository 는 의존하지 말 것
 * ❗❗❗❗
 * */
@RequiredArgsConstructor
@Transactional
@Service
public class LeagueCommandService {

  private final LeagueApiPullHandler leagueApiPullHandler;
  private final TeamApiPullHandler teamApiPullHandler;
  private final PlayerApiPullHandler playerApiPullHandler;
  private final FixtureApiPullHandler fixtureApiPullHandler;

  public void leagueApiPull() {
    leagueApiPullHandler.leagueApiPull();
  }

  public void initRanking() {
    leagueApiPullHandler.initRanking();
  }

  public void teamApiPullAndMappingSeason() {
    teamApiPullHandler.teamApiPullAndMappingSeason();
  }

  public void playerApiPull() {
    playerApiPullHandler.playerApiPull();
  }

  public void initFixtures() {
    fixtureApiPullHandler.initFixtures();
  }

}
