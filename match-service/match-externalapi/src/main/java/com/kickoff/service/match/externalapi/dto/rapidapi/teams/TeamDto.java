package com.kickoff.service.match.externalapi.dto.rapidapi.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class TeamDto {
  public Long id;
  public String name;
  public String code;
  public String country;
  public Integer founded;
  public Boolean national;
  public String logo;
}
