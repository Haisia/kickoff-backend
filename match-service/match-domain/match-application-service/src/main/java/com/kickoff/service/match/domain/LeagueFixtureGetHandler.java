package com.kickoff.service.match.domain;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesQuery;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.Season;
import com.kickoff.service.match.domain.exception.LeagueDomainException;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LeagueFixtureGetHandler {

  private final LeagueRepository leagueRepository;

  public ResponseContainer<GetLeagueSeasonFixturesResponse> getLeagueSeasonFixtures(GetLeagueSeasonFixturesQuery query) {
    League league = leagueRepository.findById(LeagueId.of(query.getLeagueId()))
      .orElseThrow(() -> new LeagueDomainException(String.format("[*] league를 찾을 수 없습니다. : leagueId=%s", query.getLeagueId()), CustomHttpStatus.BAD_REQUEST));

    Season season = league.getSeasonByYear(query.getYear())
      .orElseThrow(() -> new LeagueDomainException(String.format("[*] season을 찾을 수 없습니다. : leagueId=%s, seasonId=%s", query.getLeagueId(), query.getYear()), CustomHttpStatus.BAD_REQUEST));

    List<GetLeagueSeasonFixturesResponse> responses = season.getFixtures()
      .stream()
      .map(GetLeagueSeasonFixturesResponse::from)
      .toList();

    return new ResponseContainer<>(query, responses);
  }
}
