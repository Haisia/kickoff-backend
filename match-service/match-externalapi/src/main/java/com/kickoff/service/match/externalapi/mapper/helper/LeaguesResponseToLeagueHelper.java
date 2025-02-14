package com.kickoff.service.match.externalapi.mapper.helper;

import com.kickoff.common.domain.valuobject.LeagueType;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.valueobject.Country;
import com.kickoff.service.match.domain.valueobject.Flag;
import com.kickoff.service.match.domain.valueobject.Logo;
import com.kickoff.service.match.domain.valueobject.UrlType;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.CountryDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeagueDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.SeasonDto;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeaguesResponseToLeagueHelper {

  public League leaguesResponseToLeague(LeaguesResponse leaguesResponse) {
    LeagueDto leagueDto = leaguesResponse.getLeague();
    CountryDto countryDto = leaguesResponse.getCountry();
    List<SeasonDto> seasonDtos = leaguesResponse.getSeasons();

    League league = leagueDtoToLeague(leagueDto);
    league.addLogo(new Logo(leagueDto.getLogo(), UrlType.EXTERNAL));
    league.setCountry(countryDtoToCountry(countryDto));
    seasonDtosToSeasons(seasonDtos).forEach(league::addSeason);

    return league;
  }

  public List<League> leaguesResponsesToLeagues(List<LeaguesResponse> leaguesResponses) {
    return leaguesResponses.stream()
      .map(this::leaguesResponseToLeague)
      .collect(Collectors.toList());
  }

  private League leagueDtoToLeague(LeagueDto leagueDto) {
    return League.builder()
      .apiFootballLeagueId(leagueDto.getId())
      .name(leagueDto.getName())
      .type(LeagueType.fromValue(leagueDto.getType()))
      .build();
  }

  private Country countryDtoToCountry(CountryDto countryDto) {
    return Country.builder()
      .name(countryDto.getName())
      .code(countryDto.getCode())
      .flag(new Flag(countryDto.getFlag(), UrlType.EXTERNAL))
      .build();
  }

  private Season seasonDtoToSeason(SeasonDto seasonDto) {
    return Season.builder()
      .year(Year.of(seasonDto.getYear()))
      .startDate(seasonDto.getStart())
      .endDate(seasonDto.getEnd())
      .build();
  }

  private List<Season> seasonDtosToSeasons(List<SeasonDto> seasonDtos) {
    return seasonDtos.stream()
      .map(this::seasonDtoToSeason)
      .toList()
      ;
  }

}
