package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;

public interface GetHeadToHeadUseCase {
  ResponseContainer<FixtureResponse> getHeadToHeadSimple(LeagueFixtureQuery query);
}
