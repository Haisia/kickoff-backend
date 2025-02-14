package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.dataaccess.repository.LeagueJpaRepository;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LeagueRepositoryImpl implements LeagueRepository {

  private final LeagueJpaRepository leagueJpaRepository;

  @Override
  public League save(League league) {
    return leagueJpaRepository.save(league);
  }

  @Override
  public List<League> saveAll(List<League> leagues) {
    return leagueJpaRepository.saveAll(leagues);
  }

  @Override
  public List<League> findByApiFootballLeagueIdIn(List<Long> apiFootballLeagueIds) {
    return leagueJpaRepository.findByApiFootballLeagueIdIn(apiFootballLeagueIds);
  }
}
