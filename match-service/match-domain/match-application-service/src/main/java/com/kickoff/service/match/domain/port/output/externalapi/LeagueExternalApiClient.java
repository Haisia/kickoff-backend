package com.kickoff.service.match.domain.port.output.externalapi;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Team;

import java.util.List;

public interface LeagueExternalApiClient {
  List<League> pullLeagues();
  List<Team> pullTeam(League league);
  List<League> mappingSeasonWithTeams(List<League> leagues);
}
