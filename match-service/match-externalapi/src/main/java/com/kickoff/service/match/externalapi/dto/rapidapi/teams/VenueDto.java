package com.kickoff.service.match.externalapi.dto.rapidapi.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VenueDto {
  public Long id;
  public String name;
  public String address;
  public String city;
  public Long capacity;
  public String surface;
  public String image;
}
