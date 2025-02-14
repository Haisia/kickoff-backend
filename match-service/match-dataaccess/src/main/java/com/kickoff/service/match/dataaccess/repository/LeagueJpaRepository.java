package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.domain.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface LeagueJpaRepository extends JpaRepository<League, UUID> {
  List<League> findByApiFootballLeagueIdIn(Collection<Long> apiFootballLeagueIds);
}
