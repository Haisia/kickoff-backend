package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.entity.Team;

import java.util.Collection;
import java.util.List;

public interface TeamRepository {
  List<Team> saveAll(Collection<Team> teams);
  List<Team> saveAllFromApiFootball(List<Team> teams);
}
