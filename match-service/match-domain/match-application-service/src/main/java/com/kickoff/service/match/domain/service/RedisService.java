package com.kickoff.service.match.domain.service;

import com.kickoff.service.match.domain.entity.Fixture;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RedisService {

  private final RedisTemplate<String, String> redisTemplate;

  public void saveTodayLiveFixtures(List<Fixture> fixtures) {
    if (fixtures.isEmpty()) return;

    String redisKey = "live_fixtures:" + LocalDate.now();

    SetOperations<String, String> setOperations = redisTemplate.opsForSet();

    fixtures.stream()
      .map(fixture -> fixture.getId().getId().toString())
      .forEach(id -> setOperations.add(redisKey, id));

    redisTemplate.expire(redisKey, java.time.Duration.ofDays(1));
  }
}
