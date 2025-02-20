package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.valueobject.FixtureId;

import java.util.Optional;

public interface FixtureRepository {
  Optional<Fixture> findById(FixtureId fixtureId);
}
