package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.service.match.domain.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LeagueJpaRepository extends JpaRepository<League, LeagueId> {
  List<League> findByApiFootballLeagueIdIn(Collection<Long> apiFootballLeagueIds);
}
