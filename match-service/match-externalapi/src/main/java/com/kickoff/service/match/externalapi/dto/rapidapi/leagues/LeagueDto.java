package com.kickoff.service.match.externalapi.dto.rapidapi.leagues;

import com.kickoff.service.match.externalapi.dto.rapidapi.standings.StandingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeagueDto {
  public Long id;
  public String name;
  public String type;
  public String logo;
  List<List<StandingDto>> standings;
}
