package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FixtureJpaRepository extends JpaRepository<Fixture, FixtureId> {
  List<Fixture> findByFixtureDateTime_DateAfterAndFixtureDateTime_DateBefore(LocalDateTime startDate, LocalDateTime endDate);
}
