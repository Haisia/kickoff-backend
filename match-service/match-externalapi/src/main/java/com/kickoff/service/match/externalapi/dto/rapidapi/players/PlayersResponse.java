package com.kickoff.service.match.externalapi.dto.rapidapi.players;

import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
public class PlayersResponse {
  public TeamDto team;
  public List<PlayerDto> players;
}
