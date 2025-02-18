package com.kickoff.service.match.externalapi.dto.rapidapi.standings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class ScoreStatsDto {
  public Integer played;
  public Integer win;
  public Integer draw;
  public Integer lose;
  public Goals goals;

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class Goals {
    @JsonProperty("for")
    public Integer goalsFor;
    public Integer against;
  }
}
