package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.match.domain.entity.Fixture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FixtureRepository {
  Optional<Fixture> findById(FixtureId fixtureId);
  List<Fixture> findByFixtureDateTime_DateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
