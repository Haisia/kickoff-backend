package com.kickoff.service.match.domain;

import com.kickoff.service.match.domain.port.input.LeagueApiPullUseCase;
import com.kickoff.service.match.domain.port.input.TeamApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LeagueApplicationServiceImpl implements LeagueApiPullUseCase, TeamApiPullUseCase {

  private final LeagueApiPullHandler leagueApiPullHandler;
  private final TeamApiPullHandler teamApiPullHandler;

  @Override
  public void leagueApiPull() {
    leagueApiPullHandler.leagueApiPull();
  }

  @Override
  public void teamApiPull() {
    teamApiPullHandler.teamApiPull();
  }
}
