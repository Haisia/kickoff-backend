package com.kickoff.service.match.externalapi.dto.rapidapi.fixtures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kickoff.service.match.externalapi.dto.rapidapi.teams.VenueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor @AllArgsConstructor
@Data
public class FixtureDto {
  public Long id;
  public String referee;
  public String timezone;
  public OffsetDateTime date;
  public Long timestamp;

  public PeriodsDto periods;
  public VenueDto venue;
  public StatusDto status;

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class PeriodsDto {
    private Long first;
    private Long second;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class StatusDto {
    @JsonProperty("long")
    public String longName;
    @JsonProperty("short")
    public String shortName;
    public Integer elapsed;
    public String extra;
  }
}
