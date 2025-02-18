package com.kickoff.service.match.domain;

import com.kickoff.common.constant.Constant;
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
    leagueRepository.saveAll(leagueExternalApiService.initLeagues());
  }

  public void initRanking() {
    leagueExternalApiService.initRanking(leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS));
  }
}
