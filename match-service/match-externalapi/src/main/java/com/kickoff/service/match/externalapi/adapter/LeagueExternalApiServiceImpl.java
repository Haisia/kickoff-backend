package com.kickoff.service.match.externalapi.adapter;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.match.externalapi.client.LeagueExternalApiClient;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.mapper.LeagueExternalApiMapper;
import com.kickoff.service.match.externalapi.mapper.TeamsExternalApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiServiceImpl implements LeagueExternalApiService {

  private final WebClient webClient;
  private final LeagueExternalApiMapper leagueExternalApiMapper;
  private final TeamsExternalApiMapper teamsExternalApiMapper;
  private final LeagueExternalApiClient leagueExternalApiClient;

  @Override
  public List<League> pullLeagues() {
    List<LeaguesResponse> response = leagueExternalApiClient.requestLeagues();
    return leagueExternalApiMapper.leaguesResponsesToLeagues(response);
  }

  @Override
  public League pullTeam(League league) {
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
}
