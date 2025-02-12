package com.kickoff.service.match.domain;

import com.kickoff.service.match.domain.port.input.LeagueApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LeagueApplicationServiceImpl implements LeagueApiPullUseCase {

  private final LeagueApiPullHandler leagueApiPullHandler;

  @Override
  public void leagueApiPull() {
    leagueApiPullHandler.leagueApiPull();
  }
}
