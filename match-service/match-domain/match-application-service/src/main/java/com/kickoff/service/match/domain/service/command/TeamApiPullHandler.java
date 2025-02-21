package com.kickoff.service.match.domain.service.command;

import com.kickoff.common.constant.Constant;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.input.TeamApiPullUseCase;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.match.domain.port.output.repository.SeasonRepository;
import com.kickoff.service.match.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TeamApiPullHandler implements TeamApiPullUseCase {

  private final LeagueRepository leagueRepository;
  private final LeagueExternalApiService leagueExternalApiService;
  private final TeamRepository teamRepository;
  private final SeasonRepository seasonRepository;

  public void teamApiPullAndMappingSeason() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    for (League league : leagues) {
      leagueExternalApiService.initTeam(league);
    }
  }
}



