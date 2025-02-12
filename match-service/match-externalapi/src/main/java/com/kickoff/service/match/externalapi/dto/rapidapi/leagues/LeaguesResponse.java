package com.kickoff.service.match.externalapi.dto.rapidapi.leagues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeaguesResponse {
  public League league;
  public Country country;
  public List<Season> seasons;
}
