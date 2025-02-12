package com.kickoff.service.match.externalapi.dto.rapidapi.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class TeamsResponse {
  public TeamDto team;
  public VenueDto venue;
}
