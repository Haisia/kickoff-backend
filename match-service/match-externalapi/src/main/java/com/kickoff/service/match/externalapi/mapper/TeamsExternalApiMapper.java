package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamsResponse;
import com.kickoff.service.match.externalapi.mapper.helper.TeamsResponseToTeamHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TeamsExternalApiMapper {

  private final TeamsResponseToTeamHelper teamsResponseToTeamHelper;

  public Team teamsResponseToTeam(TeamsResponse teamsResponse) {
    return teamsResponseToTeamHelper.teamsResponseToTeam(teamsResponse);
  }

  public List<Team> teamsResponsesToTeams(List<TeamsResponse> teamsResponses) {
    return teamsResponseToTeamHelper.teamsResponsesToTeams(teamsResponses);
  }
}
