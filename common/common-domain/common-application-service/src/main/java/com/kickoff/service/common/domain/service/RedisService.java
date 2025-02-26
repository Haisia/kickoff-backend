package com.kickoff.service.common.domain.service;

import com.kickoff.common.domain.valuobject.FixtureId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RedisService {

  private final RedisTemplate<String, String> redisTemplate;

  /**
   * 오늘의 Live Fixtures를 Redis에 저장하고, isStarted와 isEnded 상태를 관리한다.
   */
  public void saveTodayLiveFixture(FixtureId fixtureId, LocalDateTime fixtureDateTime) {
    if (fixtureId == null) return;

    String today = LocalDate.now().toString();

    HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

    String redisKey = "live_fixtures:" + today + ":" + fixtureId.getId().toString();

    hashOperations.put(redisKey, "isStarted", "false");
    hashOperations.put(redisKey, "isEnded", "false");
    hashOperations.put(redisKey, "startTime", fixtureDateTime.toString());

    redisTemplate.expire(redisKey, Duration.ofDays(1));
  }

  public Set<FixtureId> getCurrentLiveFixtures() {
    updateTodayLiveFixturesIsStarted();

    String redisKeyPattern = "live_fixtures:" + LocalDate.now() + ":*";
    HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

    Set<String> keys = redisTemplate.keys(redisKeyPattern);
    if (keys.isEmpty()) return Set.of();

    return keys.stream()
      .filter(key -> {
        String isStarted = (String) hashOperations.get(key, "isStarted");
        String isEnded = (String) hashOperations.get(key, "isEnded");

        return "true".equals(isStarted) && "false".equals(isEnded);
      })
      .map(key -> FixtureId.of(UUID.fromString(key.split(":")[2])))
      .collect(Collectors.toSet());
  }

  private void updateTodayLiveFixturesIsStarted() {
    String redisKeyPattern = "live_fixtures:" + LocalDate.now() + ":*";

    HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

    Set<String> keys = redisTemplate.keys(redisKeyPattern);
    if (keys.isEmpty()) return;

    keys.forEach(key -> {
      String startTimeValue = (String) hashOperations.get(key, "startTime");
      if (startTimeValue != null) {
        LocalDateTime startTime = LocalDateTime.parse(startTimeValue);
        if (startTime.isBefore(LocalDateTime.now())) {
          hashOperations.put(key, "isStarted", "true");
        }
      }
    });
  }

  public void updateIsEnded(String fixtureId, boolean isEnded) {
    String redisKey = "live_fixtures:" + LocalDate.now() + ":" + fixtureId;

    HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
    hashOperations.put(redisKey, "isEnded", String.valueOf(isEnded));
  }
}
