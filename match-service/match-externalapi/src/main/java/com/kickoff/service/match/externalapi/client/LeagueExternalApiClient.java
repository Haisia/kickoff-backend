package com.kickoff.service.match.externalapi.client;

import com.kickoff.service.match.externalapi.dto.rapidapi.RapidApiResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.fixtures.FixturesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.fixtures.FixturesStatisticsResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.players.PlayersResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.standings.StandingResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiClient {

  private final WebClient webClient;

  public List<LeaguesResponse> requestLeagues() {
    ParameterizedTypeReference<RapidApiResponse<LeaguesResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri("/leagues")
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }

  public List<TeamsResponse> requestTeams(Long leagueId, Year season) {
    ParameterizedTypeReference<RapidApiResponse<TeamsResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(uriBuilder -> uriBuilder
          .path("/teams")
          .queryParam("league", leagueId)
          .queryParam("season", season)
          .build())
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }

  public List<PlayersResponse> requestPlayersSquads(Long teamId) {
    ParameterizedTypeReference<RapidApiResponse<PlayersResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(uriBuilder -> uriBuilder
          .path("/players/squads")
          .queryParam("team", teamId)
          .build())
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }

  public List<StandingResponse> requestStandings(Long leagueId, Year season) {
    ParameterizedTypeReference<RapidApiResponse<StandingResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(uriBuilder -> uriBuilder
          .path("/standings")
          .queryParam("league", leagueId)
          .queryParam("season", season)
          .build())
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }

  public List<FixturesResponse> requestFixtures(Long leagueId, Year season) {
    ParameterizedTypeReference<RapidApiResponse<FixturesResponse>> responseType = new ParameterizedTypeReference<>() {};
    return Objects.requireNonNull(
      webClient.get()
        .uri(uriBuilder -> uriBuilder
          .path("/fixtures")
          .queryParam("league", leagueId)
          .queryParam("season", season)
          .build())
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }

  public List<FixturesStatisticsResponse> requestFixturesStatistics(Long fixtureId) {
    ParameterizedTypeReference<RapidApiResponse<FixturesStatisticsResponse>> responseType = new ParameterizedTypeReference<>() {};
    return Objects.requireNonNull(
      webClient.get()
        .uri(uriBuilder -> uriBuilder
          .path("/fixtures/statistics")
          .queryParam("fixture", fixtureId)
          .build())
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }
}
