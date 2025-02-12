package com.kickoff.service.match.externalapi.dto.rapidapi.leagues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeagueDto {
  public Long id;
  public String name;
  public String type;
  public String logo;
}
