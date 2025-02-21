package com.kickoff.service.match.domain.service.query;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;

public interface FixtureQueryService {
  ResponseContainer<FixtureResponse> fixtureGet(LeagueFixtureQuery query);
  ResponseContainer<FixtureResponse> fixtureList(LeagueSeasonQuery query);
  ResponseContainer<LeagueFixtureResponse> fixtureMainList();
  ResponseContainer<LeagueFixtureResponse> fixtureInPlayList();
  ResponseContainer<FixtureResponse> fixtureH2HSimpleList(LeagueFixtureQuery query);
}
