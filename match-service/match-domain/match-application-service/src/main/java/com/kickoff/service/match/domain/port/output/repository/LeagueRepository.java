package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import com.kickoff.service.match.domain.valueobject.TeamId;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository {
  League save(League league);
  List<League> saveAll(List<League> leagues);
  Optional<League> findById(LeagueId id);
  List<League> findAll();
  List<League> findByApiFootballLeagueIdIn(List<Long> apiFootballLeagueIds);
  Optional<League> findByTeamId(TeamId teamId);
  Optional<League> findByFixtureId(FixtureId fixtureId);
}
