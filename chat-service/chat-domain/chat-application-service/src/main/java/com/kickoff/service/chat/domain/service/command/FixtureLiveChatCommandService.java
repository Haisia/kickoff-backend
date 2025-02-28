package com.kickoff.service.chat.domain.service.command;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.dto.FixtureLiveChatCommand;
import com.kickoff.service.chat.domain.dto.PublishFixtureLiveChatCommand;
import com.kickoff.service.chat.domain.port.output.messaging.FixtureLiveChatPublisher;
import com.kickoff.service.common.domain.dto.MemberRedisDto;
import com.kickoff.service.common.domain.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class FixtureLiveChatCommandService {

  private final RedisService redisService;
  private final FixtureLiveChatPublisher fixtureLiveChatPublisher;

  public void fixtureLiveChatCreate(FixtureLiveChatCommand command, MemberId memberId) {
    boolean isCurrentLive = redisService.getCurrentLiveFixtures()
      .stream()
      .anyMatch(fixtureId -> fixtureId.getId().equals(command.getFixtureId()));

    if(!isCurrentLive) throw new IllegalArgumentException("Fixture not found");

    LocalDateTime now = LocalDateTime.now();
    redisService.saveLiveFixtureChat(FixtureId.of(command.getFixtureId()), command.getMessage(), memberId, now);

    MemberRedisDto member = redisService.getLoginMember(memberId).orElseThrow(() -> new IllegalArgumentException("Sender is not logged in"));

    PublishFixtureLiveChatCommand publishFixtureLiveChatCommand = PublishFixtureLiveChatCommand.builder()
      .fixtureId(command.getFixtureId())
      .memberId(memberId.getId())
      .nickname(member.getNickname())
      .message(command.getMessage())
      .timestamp(now)
      .build();

    fixtureLiveChatPublisher.publishFixtureLiveChat(publishFixtureLiveChatCommand);
  }
}
