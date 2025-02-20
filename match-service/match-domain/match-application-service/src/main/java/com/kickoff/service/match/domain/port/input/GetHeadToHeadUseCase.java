package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.GetFixtureResponse;
import com.kickoff.service.match.domain.dto.headtohead.GetHeadToHeadSimpleQuery;
import com.kickoff.service.match.domain.valueobject.FixtureId;

public interface GetHeadToHeadUseCase {
  ResponseContainer<GetFixtureResponse> getHeadToHeadSimple(GetHeadToHeadSimpleQuery query);
  ResponseContainer<GetFixtureResponse> getHeadToHeadSimple(FixtureId fixtureId);
}
