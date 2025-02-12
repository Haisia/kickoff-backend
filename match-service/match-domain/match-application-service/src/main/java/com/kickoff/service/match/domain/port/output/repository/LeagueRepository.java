package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.entity.League;

import java.util.List;

public interface LeagueRepository {
  League save(League league);
  List<League> saveAll(List<League> leagues);
}
