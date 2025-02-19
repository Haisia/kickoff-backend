package com.kickoff.service.match.domain.dto.rank;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Year;
import java.util.UUID;

@ToString
@AllArgsConstructor @NoArgsConstructor
@Data
public class GetLeagueSeasonRankingQuery {
  @NotNull(message = "League ID는 반드시 입력해야 합니다.")
  public UUID leagueId;
  @NotNull(message = "Year는 반드시 입력해야 합니다.")
  public Year year;
}
