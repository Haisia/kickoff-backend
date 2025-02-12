package com.kickoff.service.match.externalapi;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiClient;
import com.kickoff.service.match.externalapi.dto.rapidapi.RapidApiResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.mapper.LeagueExternalApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiClientImpl implements LeagueExternalApiClient {

  private final WebClient webClient;
  private final LeagueExternalApiMapper leagueExternalApiMapper;

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
  public League pullTeam(League league) {
    ParameterizedTypeReference<RapidApiResponse<TeamsResponse>> responseType = new ParameterizedTypeReference<>() {};

    for (Season season : league.getSeasons()) {
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
      season.setTeams(leagueExternalApiMapper.teamsResponsesToTeams(response));
    }

    return league;
  }
}
