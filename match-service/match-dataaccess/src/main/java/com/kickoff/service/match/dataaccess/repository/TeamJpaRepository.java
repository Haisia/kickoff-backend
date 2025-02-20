package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.valueobject.TeamId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamJpaRepository extends JpaRepository<Team, TeamId> {
  Optional<Team> findByApiFootballTeamId(Long apiFootballTeamId);
}
