package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.service.match.dataaccess.repository.FixtureJpaRepository;
import com.kickoff.service.match.dataaccess.repository.LeagueJpaRepository;
import com.kickoff.service.match.dataaccess.repository.TeamJpaRepository;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LeagueRepositoryImpl implements LeagueRepository {

  private final LeagueJpaRepository leagueJpaRepository;
  private final TeamJpaRepository teamJpaRepository;
  private final FixtureJpaRepository fixtureJpaRepository;

  @Override
  public League save(League league) {
    return leagueJpaRepository.save(league);
  }

  @Override
  public List<League> saveAll(List<League> leagues) {
    return leagueJpaRepository.saveAll(leagues);
  }

  @Override
  public Optional<League> findById(LeagueId id) {
    return leagueJpaRepository.findById(id);
  }

  @Override
  public List<League> findByApiFootballLeagueIdIn(List<Long> apiFootballLeagueIds) {
    return leagueJpaRepository.findByApiFootballLeagueIdIn(apiFootballLeagueIds);
  }

  @Override
  public List<League> findAll() {
    return leagueJpaRepository.findAll();
  }
}
