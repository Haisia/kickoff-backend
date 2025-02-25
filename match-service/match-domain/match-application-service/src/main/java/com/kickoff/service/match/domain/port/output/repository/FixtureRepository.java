package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.valueobject.FixtureId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FixtureRepository {
  Optional<Fixture> findById(FixtureId fixtureId);
  List<Fixture> findByFixtureDateAfterAndFixtureDateBefore(LocalDateTime startDate, LocalDateTime endDate);
}
