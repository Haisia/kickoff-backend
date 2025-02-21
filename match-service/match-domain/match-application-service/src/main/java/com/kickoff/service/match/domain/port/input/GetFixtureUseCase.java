package com.kickoff.service.match.domain.port.input;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;

public interface GetFixtureUseCase {
  ResponseContainer<FixtureResponse> getLeagueSeasonFixture(LeagueFixtureQuery query);
  ResponseContainer<FixtureResponse> getLeagueSeasonFixtures(LeagueSeasonQuery query);
  ResponseContainer<LeagueFixtureResponse> getLeagueSeasonFixturesForMainPage();
  ResponseContainer<LeagueFixtureResponse> getLeagueSeasonInPlayFixtures();
}
