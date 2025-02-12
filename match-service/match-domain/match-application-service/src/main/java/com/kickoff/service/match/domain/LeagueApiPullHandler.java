package com.kickoff.service.match.domain;

import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiClient;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LeagueApiPullHandler {

  private final LeagueExternalApiClient leagueExternalApiClient;
  private final LeagueRepository leagueRepository;

  public void leagueApiPull() {
    leagueRepository.saveAll(leagueExternalApiClient.pullLeagues());
  }
}
