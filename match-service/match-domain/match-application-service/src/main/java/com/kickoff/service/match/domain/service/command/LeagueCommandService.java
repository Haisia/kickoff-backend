package com.kickoff.service.match.domain.service.command;

import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LeagueCommandService {

  private final LeagueApiPullHandler leagueApiPullHandler;
  private final TeamApiPullHandler teamApiPullHandler;
  private final PlayerApiPullHandler playerApiPullHandler;
  private final FixtureApiPullHandler fixtureApiPullHandler;

  private final LeagueRepository leagueRepository;

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
