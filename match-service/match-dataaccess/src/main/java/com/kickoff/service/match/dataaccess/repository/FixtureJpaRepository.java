package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.domain.valueobject.FixtureId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureJpaRepository extends JpaRepository<com.kickoff.service.match.domain.entity.Fixture, FixtureId> {
}
