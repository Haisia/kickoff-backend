package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.dataaccess.repository.TeamJpaRepository;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TeamRepositoryImpl implements TeamRepository {

  private final TeamJpaRepository teamJpaRepository;

  @Override
  public List<Team> saveAll(Collection<Team> teams) {
    return teamJpaRepository.saveAll(teams);
  }

  @Override
  public List<Team> saveAllFromApiFootball(List<Team> teams) {
    return teams.stream()
      .filter(team -> team.getApiFootballTeamId() != null)
      .filter(team -> teamJpaRepository.findByApiFootballTeamId(team.getApiFootballTeamId()).isEmpty())
      .map(teamJpaRepository::save)
      .collect(Collectors.toList())
      ;
  }

  @Override
  public Optional<Team> findByApiFootballTeamId(Long apiFootballTeamId) {
    return teamJpaRepository.findByApiFootballTeamId(apiFootballTeamId);
  }

  @Override
  public List<Team> findAll() {
    return teamJpaRepository.findAll();
  }
}
