package com.kickoff.service.match.domain.service.command;

import com.kickoff.common.constant.Constant;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Player;
import com.kickoff.service.match.domain.port.input.PlayerApiPullUseCase;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.match.domain.port.output.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PlayerApiPullHandler implements PlayerApiPullUseCase {

  private final LeagueExternalApiService leagueExternalApiService;
  private final LeagueRepository leagueRepository;
  private final PlayerRepository playerRepository;

  public void playerApiPull() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    Map<Long, Player> allPlayers = playerRepository.findAll()
      .stream()
      .collect(Collectors.toMap(Player::getApiFootballPlayerId, player -> player));

    leagueExternalApiService.initPlayers(leagues, allPlayers);
  }
}
