package com.kickoff.service.match.application.batch;


import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import com.kickoff.service.common.domain.service.RedisService;
import com.kickoff.service.match.domain.service.command.FixtureCommandService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class FixtureBatch {

  private final FixtureCommandService fixtureCommandService;
  private final FixtureRepository fixtureRepository;
  private final RedisService redisService;

  @Scheduled(fixedRate = 60 * 1000)
  public void liveFixtureStatsUpdater() {
    Set<FixtureId> currentLiveFixtureIds = redisService.getCurrentLiveFixtures();
    if (currentLiveFixtureIds.isEmpty()) {
      log.info("[*] 진행중인 경기가 없습니다.");
      return;
    }
    List<Fixture> currentLiveFixtures = fixtureRepository.findByIdIsIn(currentLiveFixtureIds);

    for (Fixture fixture : currentLiveFixtures) {
      fixtureCommandService.fixtureStatisticsUpdate(fixture);
      log.info("[*] 진행중인 경기정보를 업데이트했습니다. : fixtureId={}", fixture.getId().getId());
    }
  }

  @PostConstruct
  public void saveTodayLiveFixturesOnStartup() {
    log.info("[*] 서버 기동: 오늘의 라이브 경기 정보를 Redis에 저장합니다.");
    saveTodayLiveFixturesToRedis();
  }

  @Scheduled(cron = "0 0 0 * * *", zone = "UTC")
  public void saveTodayLiveFixturesDaily() {
    log.info("[*] 매일 자정: 오늘의 라이브 경기 정보를 Redis에 저장합니다.");
    saveTodayLiveFixturesToRedis();
  }

  private void saveTodayLiveFixturesToRedis() {
    List<Fixture> fixtures = fixtureRepository.findByFixtureDateTime_DateBetween(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59));
    for (Fixture fixture : fixtures) {
      redisService.saveTodayLiveFixture(fixture.getId(), fixture.getFixtureDateTime().getDate());
      log.info("[*] 오늘의 경기 일정을 Redis 에 save 합니다. : fixtureId={}", fixture.getId().getId());
    }
  }
}
