package com.kickoff.service.match.application.batch;


import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import com.kickoff.service.match.domain.service.RedisService;
import com.kickoff.service.match.domain.service.command.FixtureCommandService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    List<Fixture> fixtures = fixtureRepository.findByFixtureDateTime_DateBetween(LocalDateTime.now().minusMinutes(180), LocalDateTime.now());
    if (fixtures.isEmpty()) {
      log.info("[*] 진행중인 경기가 없습니다.");
      return;
    }

    for (Fixture fixture : fixtures) {
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
    redisService.saveTodayLiveFixtures(fixtures);
  }
}
