package com.kickoff.service.match.domain;

import com.kickoff.common.constant.Constant;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiClient;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.match.domain.port.output.repository.SeasonRepository;
import com.kickoff.service.match.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TeamApiPullHandler {

  private final LeagueRepository leagueRepository;
  private final LeagueExternalApiClient leagueExternalApiClient;
  private final TeamRepository teamRepository;
  private final SeasonRepository seasonRepository;

  @Transactional
  public void teamApiPull() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_IDS);
    List<Team> teams = new ArrayList<>();
    for (League league : leagues) {
      teams.addAll(leagueExternalApiClient.pullTeam(league));
    }

    teamRepository.saveAllFromApiFootball(teams);
    leagueExternalApiClient.mappingSeasonWithTeams(leagues);
  }
}



