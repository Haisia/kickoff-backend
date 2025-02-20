package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesResponse;
import com.kickoff.service.match.domain.dto.headtohead.GetHeadToHeadSimpleQuery;
import com.kickoff.service.match.domain.valueobject.FixtureId;

public interface GetHeadToHeadUseCase {
  ResponseContainer<GetLeagueSeasonFixturesResponse> getHeadToHeadSimple(GetHeadToHeadSimpleQuery query);
  ResponseContainer<GetLeagueSeasonFixturesResponse> getHeadToHeadSimple(FixtureId fixtureId);
}
