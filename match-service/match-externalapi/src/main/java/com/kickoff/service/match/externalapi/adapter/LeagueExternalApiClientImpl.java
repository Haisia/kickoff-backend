package com.kickoff.service.match.externalapi.adapter;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.entity.SeasonMapTeam;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiClient;
import com.kickoff.service.match.externalapi.dto.rapidapi.RapidApiResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.mapper.LeagueExternalApiMapper;
import com.kickoff.service.match.externalapi.mapper.TeamsExternalApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiClientImpl implements LeagueExternalApiClient {

  private final WebClient webClient;
  private final LeagueExternalApiMapper leagueExternalApiMapper;
  private final TeamsExternalApiMapper teamsExternalApiMapper;

  @Override
  public List<League> pullLeagues() {
    ParameterizedTypeReference<RapidApiResponse<LeaguesResponse>> responseType = new ParameterizedTypeReference<>() {};

    List<LeaguesResponse> response = Objects.requireNonNull(
        webClient.get()
          .uri("/leagues")
          .retrieve()
          .bodyToMono(responseType)
          .block()
      ).getResponse();

    return leagueExternalApiMapper.leaguesResponsesToLeagues(response);
  }

  @Override
  public List<Team> pullTeam(League league) {
    ParameterizedTypeReference<RapidApiResponse<TeamsResponse>> responseType = new ParameterizedTypeReference<>() {};

    List<Team> result = new ArrayList<>();
    for (SeasonMapTeam seasonMapTeam : league.getSeasonMapTeams()) {
      Season season = seasonMapTeam.getSeason();

      List<TeamsResponse> response = Objects.requireNonNull(
        webClient.get()
          .uri(uriBuilder -> uriBuilder
            .path("/teams")
            .queryParam("league", league.getApiFootballLeagueId())
            .queryParam("season", season.getYear())
            .build())
          .retrieve()
          .bodyToMono(responseType)
          .block()
      ).getResponse();

      List<Team> teams = teamsExternalApiMapper.teamsResponsesToTeams(response);





      result.addAll(teams);
    }
    return result;
  }

  @Override
  public List<League> mappingSeasonWithTeams(List<League> leagues) {



    return List.of();
  }
}
