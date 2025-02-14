package com.kickoff.service.match.externalapi.mapper.helper;

import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.valueobject.Logo;
import com.kickoff.service.match.domain.valueobject.UrlType;
import com.kickoff.service.match.domain.valueobject.Venue;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.VenueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TeamsResponseToTeamHelper {

  public Team teamsResponseToTeam(TeamsResponse teamsResponse) {
    TeamDto teamDto = teamsResponse.getTeam();
    VenueDto venueDto = teamsResponse.getVenue();

    Team team = teamDtoToTeam(teamDto);
    team.addVenue(venueDtoToVenue(venueDto));

    return team;
  }

  public List<Team> teamsResponsesToTeams(List<TeamsResponse> teamsResponses) {
    return teamsResponses.stream()
      .map(this::teamsResponseToTeam)
      .toList();
  }

  private Team teamDtoToTeam(TeamDto teamDto) {
    Team team = Team.builder()
      .apiFootballTeamId(teamDto.getId())
      .name(teamDto.getName())
      .code(teamDto.getCode())
      .country(teamDto.getCountry())
      .founded(teamDto.getFounded())
      .national(teamDto.getNational())
      .build();
    team.addLogo(new Logo(teamDto.getLogo(), UrlType.EXTERNAL));

    return team;
  }

  private Venue venueDtoToVenue(VenueDto venueDto) {
    return Venue.builder()
      .apiFootballVenueId(venueDto.getId())
      .name(venueDto.getName())
      .address(venueDto.getAddress())
      .city(venueDto.getCity())
      .capacity(venueDto.getCapacity())
      .surface(venueDto.getSurface())
      .image(venueDto.getImage())
      .build();
  }

}
