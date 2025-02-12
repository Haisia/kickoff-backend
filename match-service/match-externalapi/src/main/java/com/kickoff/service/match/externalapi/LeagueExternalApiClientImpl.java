package com.kickoff.service.match.externalapi;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiClient;
import com.kickoff.service.match.externalapi.dto.rapidapi.RapidApiResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
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

    List<LeaguesResponse> response = Objects.requireNonNull(webClient.get()
        .uri("/leagues")
        .retrieve()
        .bodyToMono(responseType)
        .block())
      .getResponse();

//    List<Long> leagueFilter = List.of(
//      2L  // UEFA
//      , 39L  // EPL
//      , 140L  // LaLiga
//      , 135L  // Serie A
//      , 78L // Bundesliga
//      , 61L // Ligue 1
//      , 292L // K League 1
//    );
//    return leagueExternalApiMapper.leaguesResponsesToLeagues(response)
//      .stream()
//      .filter(l -> leagueFilter.contains(l.getApiFootballLeagueId()))
//      .collect(Collectors.toList())
//      ;

    return leagueExternalApiMapper.leaguesResponsesToLeagues(response);
  }
}
