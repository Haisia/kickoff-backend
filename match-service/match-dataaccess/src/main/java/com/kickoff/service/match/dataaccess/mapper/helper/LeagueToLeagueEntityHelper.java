package com.kickoff.service.match.dataaccess.mapper.helper;

import com.kickoff.service.match.dataaccess.entity.*;
import com.kickoff.service.match.domain.entity.Country;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.entity.Flag;
import com.kickoff.service.match.domain.entity.Logo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LeagueToLeagueEntityHelper {
  public LeagueEntity leagueToLeagueEntity(League league) {
    LeagueEntity leagueEntity = LeagueEntity.builder()
      .id((UUID)league.getIdValue())
      .apiFootballLeagueId(league.getApiFootballLeagueId())
      .name(league.getName())
      .leagueType(league.getType())
      .logo(logoToLogoEntity(league.getLogo()))
      .country(countryToCountryEntity(league.getCountry()))
      .build();

    leagueEntity.setSeasons(seasonsToSeasonEntities(league.getSeasons(), leagueEntity));
    return leagueEntity;
  }

  private LogoEntity logoToLogoEntity(Logo logo) {
    return LogoEntity.builder()
      .url(logo.getUrl())
      .urlType(logo.getUrlType())
      .build();
  }

  private CountryEntity countryToCountryEntity(Country country) {
    return CountryEntity.builder()
      .name(country.getName())
      .code(country.getCode())
      .flag(flagToFlagEntity(country.getFlag()))
      .build();
  }

  private FlagEntity flagToFlagEntity(Flag flag) {
    if (flag == null) return null;
    return FlagEntity.builder()
      .url(flag.getUrl())
      .urlType(flag.getUrlType())
      .build();
  }

  private SeasonEntity seasonToSeasonEntity(Season season, LeagueEntity leagueEntity) {
    return SeasonEntity.builder()
      .league(leagueEntity)
      .year(season.getYear().getValue())
      .startDate(season.getStartDate())
      .endDate(season.getEndDate())
      .build()
      ;
  }

  private List<SeasonEntity> seasonsToSeasonEntities(List<Season> seasons, LeagueEntity leagueEntity) {
    return seasons.stream()
      .map(season -> seasonToSeasonEntity(season, leagueEntity))
      .collect(Collectors.toList())
      ;
  }
}
