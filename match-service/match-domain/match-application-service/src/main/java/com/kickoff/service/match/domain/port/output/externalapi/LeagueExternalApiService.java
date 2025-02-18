package com.kickoff.service.match.domain.port.output.externalapi;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Player;

import java.util.List;
import java.util.Map;

public interface LeagueExternalApiService {
  List<League> initLeagues();
  League initTeam(League league);

  // allPlayers<apiFootballPlayerId, player>
  List<League> initPlayers(List<League> league, Map<Long, Player> allPlayers);
  List<League> initRanking(List<League> league);
}
