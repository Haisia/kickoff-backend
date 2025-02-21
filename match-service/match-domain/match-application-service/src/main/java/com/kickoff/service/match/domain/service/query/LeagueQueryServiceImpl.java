package com.kickoff.service.match.domain.service.query;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.exception.LeagueDomainException;
import com.kickoff.service.match.domain.mapper.LeagueDataMapper;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LeagueQueryServiceImpl implements LeagueQueryService {

  private final LeagueRepository leagueRepository;
  private final LeagueDataMapper leagueDataMapper;

  @Override
  public ResponseContainer<LeagueTeamsResponse> leagueRankList(LeagueSeasonQuery query) {
    LeagueId leagueId = LeagueId.of(query.getLeagueId());
    League league = leagueRepository.findById(leagueId)
      .orElseThrow(() -> new LeagueDomainException(String.format("[*] league를 찾을 수 없습니다. : leagueId=%s", leagueId), CustomHttpStatus.BAD_REQUEST));

    LeagueTeamsResponse leagueSeasonRanking = leagueDataMapper.leagueToLeagueTeamsResponse(league, query.getYear());
    return new ResponseContainer<>(query, List.of(leagueSeasonRanking));
  }

  @Override
  public ResponseContainer<LeagueTeamsResponse> leagueRankMainList() {
    List<LeagueTeamsResponse> leagueTeamsResponses = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS)
      .stream()
      .map(league -> leagueDataMapper.leagueToLeagueTeamsResponse(league, league.getLatestSeasonYear()))
      .toList();

    return new ResponseContainer<>("", leagueTeamsResponses);
  }
}
