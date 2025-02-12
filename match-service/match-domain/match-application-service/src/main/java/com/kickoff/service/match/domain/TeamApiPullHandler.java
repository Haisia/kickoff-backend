package com.kickoff.service.match.domain;

import com.kickoff.common.constant.Constant;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiClient;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TeamApiPullHandler {

  private final LeagueRepository leagueRepository;
  private final LeagueExternalApiClient leagueExternalApiClient;

  public void teamApiPull() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_IDS);
    for (League league : leagues) {
      leagueExternalApiClient.pullTeam(league);
    }
    System.out.println();
  }
}
