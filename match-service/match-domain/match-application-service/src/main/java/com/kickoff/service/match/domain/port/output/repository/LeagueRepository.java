package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.service.match.domain.entity.League;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository {
  League save(League league);
  List<League> saveAll(List<League> leagues);

  Optional<League> findById(LeagueId id);
  List<League> findByApiFootballLeagueIdIn(List<Long> apiFootballLeagueIds);
  List<League> findAll();
}
