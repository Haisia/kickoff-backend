package com.kickoff.service.match.application.batch;


import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import com.kickoff.service.match.domain.service.command.FixtureCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class FixtureBatch {

  private final FixtureCommandService fixtureCommandService;
  private final FixtureRepository fixtureRepository;

  @Scheduled(fixedRate = 60 * 1000)
  public void liveFixtureStatsUpdater() {
    List<Fixture> fixtures = fixtureRepository.findByFixtureDateAfterAndFixtureDateBefore(LocalDateTime.now().minusMinutes(180), LocalDateTime.now());
    if (fixtures.isEmpty()) {
      log.info("[*] 진행중인 경기가 없습니다.");
      return;
    }

    for (Fixture fixture : fixtures) {
      fixtureCommandService.fixtureStatisticsUpdate(fixture);
      log.info("[*] 진행중인 경기정보를 업데이트했습니다. : fixtureId={}", fixture.getId().getId());
    }
  }
}
