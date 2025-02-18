package com.kickoff.service.match.externalapi.adapter;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Player;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.match.externalapi.client.LeagueExternalApiClient;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.players.PlayerDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.players.PlayersResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.standings.StandingResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.mapper.LeagueExternalApiMapper;
import com.kickoff.service.match.externalapi.mapper.PlayerExternalApiMapper;
import com.kickoff.service.match.externalapi.mapper.StandingsExternalApiMapper;
import com.kickoff.service.match.externalapi.mapper.TeamsExternalApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiServiceImpl implements LeagueExternalApiService {

  private final WebClient webClient;
  private final LeagueExternalApiMapper leagueExternalApiMapper;
  private final TeamsExternalApiMapper teamsExternalApiMapper;
  private final PlayerExternalApiMapper playerExternalApiMapper;
  private final LeagueExternalApiClient leagueExternalApiClient;
  private final StandingsExternalApiMapper standingsExternalApiMapper;

  @Override
  public List<League> initLeagues() {
    List<LeaguesResponse> response = leagueExternalApiClient.requestLeagues();
    return leagueExternalApiMapper.leaguesResponsesToLeagues(response);
  }

  @Override
  public League initTeam(League league) {
    for (Season season : new ArrayList<>(league.getAllSeasonsInLeague())) {
      if (league.getSeasonMapTeams()
        .stream()
        .anyMatch(seasonMapTeam -> seasonMapTeam.getSeason().equals(season))
      ) continue;

      List<TeamsResponse> response = leagueExternalApiClient.requestTeams(league.getApiFootballLeagueId(), season.getYear());

      teamsExternalApiMapper.teamsResponsesToTeams(response)
        .forEach(team -> league.addTeamWithSeason(season, team));
    }

    return league;
  }

  // allPlayers<apiFootballPlayerId, player>
  @Override
  public List<League> initPlayers(List<League> leagues, Map<Long, Player> allPlayers) {
    for (League league : leagues) {
      for (Team team : league.getAllTeamsInLeague()) {
        List<PlayersResponse> playersResponses = leagueExternalApiClient.requestPlayersSquads(team.getApiFootballTeamId());
        if (playersResponses.isEmpty()) continue;
        List<Player> players = playersResponses.getFirst()
          .getPlayers()
          .stream()
          .map(playerDto -> allPlayers.getOrDefault(playerDto.getId(), notExistsPlayerHandler(allPlayers, playerDto)))
          .toList();

        team.addPlayers(players);
      }
    }
    return leagues;
  }

  @Override
  public List<League> initRanking(List<League> leagues) {
    for (League league : leagues) {
      for (Season season : league.getAllSeasonsInLeague()) {
        Year year = season.getYear();
        List<StandingResponse> standingResponses = leagueExternalApiClient.requestStandings(league.getApiFootballLeagueId(), year);
        leagueExternalApiClient.requestStandings(league.getApiFootballLeagueId(), year).getFirst().getLeague()
          .getStandings().getFirst()
          .forEach(standingDto ->
            standingsExternalApiMapper.StandingDtoToSeasonMapTeam(standingDto, league.getSeasonMapTeam(year, standingDto.getTeam().getId())
              .orElse(null)
            )
          );
      }
    }
    return leagues;
  }

  private Player notExistsPlayerHandler(Map<Long, Player> allPlayers, PlayerDto playerDto) {
    Player player = playerExternalApiMapper.playerDtoToPlayer(playerDto);
    allPlayers.putIfAbsent(playerDto.getId(), player);
    return player;
  }
}
