package com.kickoff.service.match.domain.port.output.externalapi;

import com.kickoff.service.match.domain.entity.League;

import java.util.List;

public interface LeagueExternalApiClient {
  List<League> pullLeagues();
  League pullTeam(League league);
}
