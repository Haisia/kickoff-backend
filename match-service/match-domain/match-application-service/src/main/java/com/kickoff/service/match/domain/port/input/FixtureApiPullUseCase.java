package com.kickoff.service.match.domain.port.input;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;

import java.util.List;

public interface FixtureApiPullUseCase {
  void initFixtures();
  void updateFixturesStatistics(League league, List<Fixture> fixtures);
}
