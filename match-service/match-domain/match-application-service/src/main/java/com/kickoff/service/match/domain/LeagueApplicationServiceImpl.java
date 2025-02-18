package com.kickoff.service.match.domain;

import com.kickoff.service.match.domain.port.input.LeagueApiPullUseCase;
import com.kickoff.service.match.domain.port.input.PlayerApiPullUseCase;
import com.kickoff.service.match.domain.port.input.TeamApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class LeagueApplicationServiceImpl implements LeagueApiPullUseCase, TeamApiPullUseCase, PlayerApiPullUseCase {

  private final LeagueApiPullHandler leagueApiPullHandler;
  private final TeamApiPullHandler teamApiPullHandler;
  private final PlayerApiPullHandler playerApiPullHandler;

  @Transactional
  @Override
  public void leagueApiPull() {
    leagueApiPullHandler.leagueApiPull();
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
}
