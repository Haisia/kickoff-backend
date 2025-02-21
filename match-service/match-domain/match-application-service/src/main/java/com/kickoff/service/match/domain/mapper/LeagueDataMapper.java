package com.kickoff.service.match.domain.mapper;

import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.mapper.helper.LeagueToGetLeagueSeasonRankingResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;

@RequiredArgsConstructor
@Component
public class LeagueDataMapper {

  private final LeagueToGetLeagueSeasonRankingResponseHelper leagueToGetLeagueSeasonRankingResponseHelper;

  public LeagueTeamsResponse leagueToGetLeagueSeasonRankingResponse(League league, Year year) {
    return leagueToGetLeagueSeasonRankingResponseHelper.leagueToGetLeagueSeasonRankingResponse(league, year);
  }


}
