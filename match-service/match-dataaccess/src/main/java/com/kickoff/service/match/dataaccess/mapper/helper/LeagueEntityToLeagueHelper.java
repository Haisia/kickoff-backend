package com.kickoff.service.match.dataaccess.mapper.helper;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.service.match.dataaccess.entity.*;
import com.kickoff.service.match.domain.entity.*;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeagueEntityToLeagueHelper {
  public League leagueEntityToLeague(LeagueEntity leagueEntity) {
    return League.builder()
      .id(LeagueId.of(leagueEntity.getId()))
      .apiFootballLeagueId(leagueEntity.getApiFootballLeagueId())
      .name(leagueEntity.getName())
      .type(leagueEntity.getLeagueType())
      .logo(logoEntityToLogo(leagueEntity.getLogo()))
      .country(countryEntityToCountry(leagueEntity.getCountry()))
      .seasons(seasonEntitiesToSeasons(leagueEntity.getSeasons()))
      .build();
  }

  private Logo logoEntityToLogo(LogoEntity logoEntity) {
    return Logo.builder()
      .id(logoEntity.getId())
      .url(logoEntity.getUrl())
      .urlType(logoEntity.getUrlType())
      .build();
  }

  private Flag flagEntityToFlag(FlagEntity flagEntity) {
    if (flagEntity == null) return null;
    return Flag.builder()
      .id(flagEntity.getId())
      .url(flagEntity.getUrl())
      .urlType(flagEntity.getUrlType())
      .build();
  }

  private Country countryEntityToCountry(CountryEntity countryEntity) {
    return Country.builder()
      .id(countryEntity.getId())
      .name(countryEntity.getName())
      .code(countryEntity.getCode())
      .flag(flagEntityToFlag(countryEntity.getFlag()))
      .build();
  }

  private Season seasonEntityToSeason(SeasonEntity seasonEntity) {
    return Season.builder()
      .id(seasonEntity.getId())
      .leagueId(LeagueId.of(seasonEntity.getLeague().getId()))
      .year(Year.of(seasonEntity.getYear()))
      .startDate(seasonEntity.getStartDate())
      .endDate(seasonEntity.getEndDate())
      .build();
  }

  private List<Season> seasonEntitiesToSeasons(List<SeasonEntity> seasonEntities) {
    return seasonEntities.stream()
      .map(this::seasonEntityToSeason)
      .collect(Collectors.toList())
      ;
  }
}
