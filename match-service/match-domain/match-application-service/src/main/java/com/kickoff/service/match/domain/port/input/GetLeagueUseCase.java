package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;

public interface GetLeagueUseCase {
  ResponseContainer<LeagueTeamsResponse> getLeagueSeasonRanking(LeagueSeasonQuery query);
  ResponseContainer<LeagueTeamsResponse> getLeagueSeasonRankingForMainPage();
}
