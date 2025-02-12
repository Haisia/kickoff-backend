package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.dataaccess.entity.LeagueEntity;
import com.kickoff.service.match.dataaccess.mapper.LeagueDataaccessMapper;
import com.kickoff.service.match.dataaccess.repository.CountryJpaRepository;
import com.kickoff.service.match.dataaccess.repository.LeagueJpaRepository;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LeagueRepositoryImpl implements LeagueRepository {

  private final LeagueJpaRepository leagueJpaRepository;
  private final LeagueDataaccessMapper leagueDataaccessMapper;
  private final CountryJpaRepository countryJpaRepository;

  @Override
  public League save(League league) {
    LeagueEntity entity = leagueDataaccessMapper.leagueToLeagueEntity(league);
    countryJpaRepository.findByName(entity.getCountry().getName()).ifPresent(entity::setCountry);
    return leagueDataaccessMapper.leagueEntityToLeague(leagueJpaRepository.save(entity));
  }

  @Override
  public List<League> saveAll(List<League> leagues) {
    return leagues.stream()
      .map(this::save)
      .collect(Collectors.toList())
      ;
  }
}
