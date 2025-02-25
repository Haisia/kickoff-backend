package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.valueobject.FixtureId;

import java.util.Optional;

public interface FixtureRepository {
  Optional<com.kickoff.service.match.domain.entity.Fixture> findById(FixtureId fixtureId);
}
