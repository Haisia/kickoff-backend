package com.kickoff.service.match.domain;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.exception.LeagueDomainException;
import com.kickoff.service.match.domain.mapper.LeagueDataMapper;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;

@RequiredArgsConstructor
@Component
public class LeagueRankingGetHandler {

  private final LeagueRepository leagueRepository;
  private final LeagueDataMapper leagueDataMapper;

  public GetLeagueSeasonRankingResponse getLeagueSeasonRanking(LeagueId leagueId, Year year) {
    League league = leagueRepository.findById(leagueId)
      .orElseThrow(() -> new LeagueDomainException(String.format("[*] league를 찾을 수 없습니다. : leagueId=%s", leagueId.getId()), CustomHttpStatus.BAD_REQUEST));

    return leagueDataMapper.leagueToGetLeagueSeasonRankingResponse(league, year);
  }
}
