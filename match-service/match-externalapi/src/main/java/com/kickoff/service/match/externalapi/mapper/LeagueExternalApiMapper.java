package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.common.domain.valuobject.LeagueType;
import com.kickoff.service.match.domain.entity.Country;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.entity.Flag;
import com.kickoff.service.match.domain.entity.Logo;
import com.kickoff.service.match.domain.valueobject.UrlType;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeagueExternalApiMapper {

  public League leaguesResponseToLeague(LeaguesResponse leaguesResponse) {
    com.kickoff.service.match.externalapi.dto.rapidapi.leagues.League leagueResponse = leaguesResponse.getLeague();
    com.kickoff.service.match.externalapi.dto.rapidapi.leagues.Country countryResponse = leaguesResponse.getCountry();
    List<com.kickoff.service.match.externalapi.dto.rapidapi.leagues.Season> seasonsResponse = leaguesResponse.getSeasons();

    Country country = Country.builder()
      .name(countryResponse.getName())
      .code(countryResponse.getCode())
      .flag(countryResponse.getFlag() != null ? new Flag(null, countryResponse.getFlag(), UrlType.EXTERNAL) : null)
      .build();

    List<Season> seasons = new ArrayList<>();
    for (com.kickoff.service.match.externalapi.dto.rapidapi.leagues.Season seasonResponse : seasonsResponse) {
      Season mappedSeason = Season.builder()
        .year(Year.of(seasonResponse.getYear()))
        .startDate(seasonResponse.getStart())
        .endDate(seasonResponse.getEnd())
        .build();
      seasons.add(mappedSeason);
    }

    return League.builder()
      .apiFootballLeagueId(leagueResponse.getId())
      .name(leagueResponse.getName())
      .type(LeagueType.fromValue(leagueResponse.getType()))
      .logo(new Logo(null, leagueResponse.getLogo(), UrlType.EXTERNAL))
      .country(country)
      .seasons(seasons)
      .build();
  }

  public List<League> leaguesResponsesToLeagues(List<LeaguesResponse> leaguesResponses) {
    return leaguesResponses.stream()
      .map(this::leaguesResponseToLeague)
      .collect(Collectors.toList());
  }
}
