package com.kickoff.service.match.domain.service.query;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;

public interface LeagueQueryService {
  ResponseContainer<LeagueTeamsResponse> leagueRankList(LeagueSeasonQuery query);
  ResponseContainer<LeagueTeamsResponse> leagueRankMainList();
}
