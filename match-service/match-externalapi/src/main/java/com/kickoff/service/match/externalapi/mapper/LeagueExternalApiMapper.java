package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.common.domain.valuobject.LeagueType;
import com.kickoff.service.match.domain.entity.*;
import com.kickoff.service.match.domain.valueobject.UrlType;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.CountryDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeagueDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.SeasonDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.VenueDto;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeagueExternalApiMapper {

  public League leaguesResponseToLeague(LeaguesResponse leaguesResponse) {
    LeagueDto leagueDtoResponse = leaguesResponse.getLeague();
    CountryDto countryDtoResponse = leaguesResponse.getCountry();
    List<SeasonDto> seasonsResponse = leaguesResponse.getSeasons();

    Country country = Country.builder()
      .name(countryDtoResponse.getName())
      .code(countryDtoResponse.getCode())
      .flag(countryDtoResponse.getFlag() != null ? new Flag(null, countryDtoResponse.getFlag(), UrlType.EXTERNAL) : null)
      .build();

    List<Season> seasons = new ArrayList<>();
    for (SeasonDto seasonDtoResponse : seasonsResponse) {
      Season mappedSeason = Season.builder()
        .year(Year.of(seasonDtoResponse.getYear()))
        .startDate(seasonDtoResponse.getStart())
        .endDate(seasonDtoResponse.getEnd())
        .build();
      seasons.add(mappedSeason);
    }

    return League.builder()
      .apiFootballLeagueId(leagueDtoResponse.getId())
      .name(leagueDtoResponse.getName())
      .type(LeagueType.fromValue(leagueDtoResponse.getType()))
      .logo(new Logo(null, leagueDtoResponse.getLogo(), UrlType.EXTERNAL))
      .country(country)
      .seasons(seasons)
      .build();
  }

  public List<League> leaguesResponsesToLeagues(List<LeaguesResponse> leaguesResponses) {
    return leaguesResponses.stream()
      .map(this::leaguesResponseToLeague)
      .collect(Collectors.toList());
  }

  public Team teamsResponseToTeam(TeamsResponse teamsResponse) {
    TeamDto teamDto = teamsResponse.getTeam();
    return Team.builder()
      .id(teamDto.getId())
      .name(teamDto.getName())
      .code(teamDto.getCode())
      .country(teamDto.getCountry())
      .founded(teamDto.getFounded())
      .national(teamDto.getNational())
      .logo(teamDto.getLogo() != null ? new Logo(null, teamDto.getLogo(), UrlType.EXTERNAL) : null)
      .venue(teamsResponseToTeamVenue(teamsResponse))
      .build();
  }

  public List<Team> teamsResponsesToTeams(List<TeamsResponse> teamsResponses) {
    return teamsResponses.stream()
      .map(this::teamsResponseToTeam)
      .collect(Collectors.toList());
  }

  public TeamVenue teamsResponseToTeamVenue(TeamsResponse teamsResponse) {
    VenueDto venueDto = teamsResponse.getVenue();
    return TeamVenue.builder()
      .id(venueDto.getId())
      .name(venueDto.getName())
      .address(venueDto.getAddress())
      .city(venueDto.getCity())
      .capacity(venueDto.getCapacity())
      .surface(venueDto.getSurface())
      .image(venueDto.getImage())
      .build();
  }
}
