package com.kickoff.service.match.domain.mapper;

import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.mapper.helper.LeagueToLeagueTeamsResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;

@RequiredArgsConstructor
@Component
public class LeagueDataMapper {

  private final LeagueToLeagueTeamsResponseHelper leagueToLeagueTeamsResponseHelper;

  public LeagueTeamsResponse leagueToLeagueTeamsResponse(League league, Year year) {
    return leagueToLeagueTeamsResponseHelper.leagueToLeagueTeamsResponse(league, year);
  }
}
