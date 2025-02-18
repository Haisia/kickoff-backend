package com.kickoff.service.match.externalapi.dto.rapidapi.standings;

import com.kickoff.service.match.externalapi.dto.rapidapi.leagues.LeagueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class StandingResponse {
  public LeagueDto league;
}
