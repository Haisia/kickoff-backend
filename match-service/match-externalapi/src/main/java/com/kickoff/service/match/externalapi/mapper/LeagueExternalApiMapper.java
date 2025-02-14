package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.valueobject.Venue;
import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeaguesResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.VenueDto;
import com.kickoff.service.match.externalapi.mapper.helper.LeaguesResponseToLeagueHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiMapper {

  private final LeaguesResponseToLeagueHelper leaguesResponseToLeagueHelper;

  public League leaguesResponseToLeague(LeaguesResponse leaguesResponse) {
    return leaguesResponseToLeagueHelper.leaguesResponseToLeague(leaguesResponse);
  }

  public List<League> leaguesResponsesToLeagues(List<LeaguesResponse> leaguesResponses) {
    return leaguesResponseToLeagueHelper.leaguesResponsesToLeagues(leaguesResponses);
  }

  public Team teamsResponseToTeam(TeamsResponse teamsResponse) {
//    TeamDto teamDto = teamsResponse.getTeam();
////      .id(teamDto.getId())
//      .name(teamDto.getName())
//      .code(teamDto.getCode())
//      .country(teamDto.getCountry())
//      .founded(teamDto.getFounded())
//      .national(teamDto.getNational())
////      .logo(teamDto.getLogo() != null ? new Logo(null, teamDto.getLogo(), UrlType.EXTERNAL) : null)
//      .venue(teamsResponseToTeamVenue(teamsResponse))
//      .build();
    return null;
  }

  public List<Team> teamsResponsesToTeams(List<TeamsResponse> teamsResponses) {
    return teamsResponses.stream()
      .map(this::teamsResponseToTeam)
      .collect(Collectors.toList());
  }

  public Venue teamsResponseToTeamVenue(TeamsResponse teamsResponse) {
    VenueDto venueDto = teamsResponse.getVenue();
    return Venue.builder()
//      .id(venueDto.getId())
      .name(venueDto.getName())
      .address(venueDto.getAddress())
      .city(venueDto.getCity())
      .capacity(venueDto.getCapacity())
      .surface(venueDto.getSurface())
      .image(venueDto.getImage())
      .build();
  }
}
