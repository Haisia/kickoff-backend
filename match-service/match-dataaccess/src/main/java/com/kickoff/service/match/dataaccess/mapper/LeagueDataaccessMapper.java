package com.kickoff.service.match.dataaccess.mapper;

import com.kickoff.service.match.dataaccess.entity.LeagueEntity;
import com.kickoff.service.match.dataaccess.mapper.helper.LeagueEntityToLeagueHelper;
import com.kickoff.service.match.dataaccess.mapper.helper.LeagueToLeagueEntityHelper;
import com.kickoff.service.match.domain.entity.League;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LeagueDataaccessMapper {

  private final LeagueToLeagueEntityHelper leagueToLeagueEntityHelper;
  private final LeagueEntityToLeagueHelper leagueEntityToLeagueHelper;

  public LeagueEntity leagueToLeagueEntity(League league) {
    return leagueToLeagueEntityHelper.leagueToLeagueEntity(league);
  }

  public List<LeagueEntity> leaguesToLeagueEntities(List<League> leagues) {
    return leagues.stream()
      .map(this::leagueToLeagueEntity)
      .collect(Collectors.toList())
      ;
  }

  public League leagueEntityToLeague(LeagueEntity leagueEntity) {
    return leagueEntityToLeagueHelper.leagueEntityToLeague(leagueEntity);
  }

  public List<League> leagueEntitiesToLeagues(List<LeagueEntity> leagueEntityList) {
    return leagueEntityList.stream()
      .map(this::leagueEntityToLeague)
      .collect(Collectors.toList())
      ;
  }
}
