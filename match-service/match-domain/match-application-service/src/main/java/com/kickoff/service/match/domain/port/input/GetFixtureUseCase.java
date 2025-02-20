package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesQuery;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesResponse;

public interface GetFixtureUseCase {
  ResponseContainer<GetLeagueSeasonFixturesResponse> getLeagueSeasonFixtures(GetLeagueSeasonFixturesQuery query);
}
