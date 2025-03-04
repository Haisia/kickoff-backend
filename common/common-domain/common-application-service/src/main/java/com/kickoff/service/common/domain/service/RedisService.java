package com.kickoff.service.common.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.common.domain.dto.ChatMessageRedisDto;
import com.kickoff.service.common.domain.dto.MemberRedisDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RedisService {

  private final RedisTemplate<String, String> redisTemplate;

  private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

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

  public void saveLiveFixtureChat(FixtureId fixtureId, String message, MemberId memberId, LocalDateTime timestamp) {
    if (fixtureId == null || message == null || memberId == null) return;

    String redisKey = "live_fixture_chats:" + fixtureId.getId().toString();
    saveLiveChat(message, memberId, timestamp, redisKey);
  }

  public void saveLiveGeneralChat(String message, MemberId memberId, LocalDateTime timestamp) {
    if ( message == null || memberId == null) return;

    String redisKey = "live_general_chats";
    saveLiveChat(message, memberId, timestamp, redisKey);
  }

  private void saveLiveChat(String message, MemberId memberId, LocalDateTime timestamp, String redisKey) {
    ListOperations<String, String> listOperations = redisTemplate.opsForList();
    MemberRedisDto member = getLoginMember(memberId).orElseThrow(() -> new IllegalArgumentException("Sender is not logged in"));

    try {
      ChatMessageRedisDto chatMessage = new ChatMessageRedisDto(memberId.getId(), member.getNickname(), message, timestamp);
      String chatMessageJson = objectMapper.writeValueAsString(chatMessage);

      listOperations.leftPush(redisKey, chatMessageJson);

      redisTemplate.expire(redisKey, Duration.ofDays(1));
    } catch (Exception e) {
      throw new RuntimeException("Failed to save chat message to Redis", e);
    }
  }

  public List<ChatMessageRedisDto> getGeneralLiveChatMessages(FixtureId fixtureId) {
    if (fixtureId == null) {
      throw new IllegalArgumentException("FixtureId cannot be null");
    }

    String redisKey = "live_fixture_chats:" + fixtureId.getId().toString();
    return getChatMessageRedisDtos(redisKey, 0, 49);
  }

  public List<ChatMessageRedisDto> getGeneralLiveChatMessages() {
    String redisKey = "live_general_chats";
    return getChatMessageRedisDtos(redisKey, 0, 49);
  }

  public List<ChatMessageRedisDto> getAllGeneralLiveChatMessages() {
    String redisKey = "live_general_chats";
    return getChatMessageRedisDtos(redisKey, 0, -1);
  }

  private List<ChatMessageRedisDto> getChatMessageRedisDtos(String redisKey, int startIdx, int endIdx) {
    ListOperations<String, String> listOperations = redisTemplate.opsForList();

    try {
      List<String> messagesJson = listOperations.range(redisKey, startIdx, endIdx);

      if (messagesJson == null || messagesJson.isEmpty()) {
        return List.of();
      }

      return messagesJson.stream()
        .map(json -> {
          try {
            return objectMapper.readValue(json, ChatMessageRedisDto.class);
          } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize chat message", e);
          }
        })
        .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("Failed to retrieve chat messages from Redis", e);
    }
  }

  public void saveLoginMember(MemberId memberId, MemberRedisDto memberRedisDto) {
    if (memberId == null || memberRedisDto == null) {
      throw new IllegalArgumentException("MemberId and MemberRedisDto cannot be null");
    }

    String redisKey = "member:info:" + memberId.getId();
    try {
      String memberJson = objectMapper.writeValueAsString(memberRedisDto);

      redisTemplate.opsForValue().set(redisKey, memberJson);

      redisTemplate.expire(redisKey, Duration.ofHours(1));
    } catch (Exception e) {
      throw new RuntimeException("Failed to save member info to Redis", e);
    }
  }

  public Optional<MemberRedisDto> getLoginMember(MemberId memberId) {
    if (memberId == null) {
      throw new IllegalArgumentException("MemberId cannot be null");
    }

    String redisKey = "member:info:" + memberId.getId();
    try {
      String memberJson = redisTemplate.opsForValue().get(redisKey);

      if (memberJson == null) {
        return Optional.empty();
      }

      return Optional.of(objectMapper.readValue(memberJson, MemberRedisDto.class));
    } catch (Exception e) {
      throw new RuntimeException("Failed to retrieve member info from Redis", e);
    }
  }

  public void clearGeneralLiveChatMessages() {
    String redisKey = "live_general_chats";
    redisTemplate.delete(redisKey);
  }
}
