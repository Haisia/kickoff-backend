package com.kickoff.service.match.domain;

import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LeagueApiPullHandler {

  private final LeagueExternalApiService leagueExternalApiService;
  private final LeagueRepository leagueRepository;

  public void leagueApiPull() {
    leagueRepository.saveAll(leagueExternalApiService.pullLeagues());
  }
}
