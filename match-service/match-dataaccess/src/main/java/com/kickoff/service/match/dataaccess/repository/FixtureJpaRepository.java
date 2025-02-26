package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.match.domain.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FixtureJpaRepository extends JpaRepository<Fixture, FixtureId> {
  List<Fixture> findByFixtureDateTime_DateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
