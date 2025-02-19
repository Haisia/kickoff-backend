package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingQuery;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingResponse;

public interface GetLeagueUseCase {
  ResponseContainer<GetLeagueSeasonRankingResponse> getLeagueSeasonRanking(GetLeagueSeasonRankingQuery query);
  ResponseContainer<GetLeagueSeasonRankingResponse> getLeagueSeasonRankingForMainPage();
}
