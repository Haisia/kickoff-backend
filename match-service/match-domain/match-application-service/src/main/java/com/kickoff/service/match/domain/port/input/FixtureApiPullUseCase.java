package com.kickoff.service.match.domain.port.input;

import com.kickoff.service.match.domain.entity.Fixture;

public interface FixtureApiPullUseCase {
  void initFixtures();
  void updateFixturesStatistics(Fixture fixture);
}
