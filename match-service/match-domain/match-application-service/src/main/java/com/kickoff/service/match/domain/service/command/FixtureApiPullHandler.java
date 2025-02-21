package com.kickoff.service.match.domain.service.command;

import com.kickoff.common.constant.Constant;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.input.FixtureApiPullUseCase;
import com.kickoff.service.match.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FixtureApiPullHandler implements FixtureApiPullUseCase {

  private final LeagueExternalApiService leagueExternalApiService;
  private final LeagueRepository leagueRepository;

  public void initFixtures() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    leagueExternalApiService.initFixture(leagues);
  }
}
