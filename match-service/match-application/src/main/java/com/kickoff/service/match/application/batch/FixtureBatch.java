package com.kickoff.service.match.application.batch;


import com.kickoff.common.constant.Constant;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.match.domain.service.command.FixtureCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class FixtureBatch {

  private final LeagueRepository leagueRepository;
  private final FixtureCommandService fixtureCommandService;

  @Scheduled(fixedRate = 60 * 1000)
  public void liveFixtureStatsUpdater() {
    List<League> leagues = leagueRepository.findByApiFootballLeagueIdIn(Constant.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS);
    boolean isLiveFixturesExist = false;
    for (League league : leagues) {
      List<Fixture> liveFixtures = league.getLiveFixtures();
      if (!liveFixtures.isEmpty()) {
        isLiveFixturesExist = true;
        liveFixtures.forEach(fixture -> log.info("[*] 실시간 진행중인 경기정보를 업데이트합니다. : fixtureId={}", fixture.getId().getId()));
        fixtureCommandService.fixtureStatisticsUpdate(league, liveFixtures);
      }
    }

    if (!isLiveFixturesExist) {
      log.info("[*] 진행중인 경기가 없습니다.");
    }
  }
}
