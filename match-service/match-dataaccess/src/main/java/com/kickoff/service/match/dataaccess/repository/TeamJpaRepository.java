package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamJpaRepository extends JpaRepository<Team, UUID> {
  Optional<Team> findByApiFootballTeamId(Long apiFootballTeamId);
}
