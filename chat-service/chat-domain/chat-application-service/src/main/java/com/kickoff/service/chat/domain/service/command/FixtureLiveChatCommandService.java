package com.kickoff.service.chat.domain.service.command;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.dto.FixtureLiveChatCommand;
import com.kickoff.service.chat.domain.dto.PublishFixtureLiveChatCommand;
import com.kickoff.service.chat.domain.port.output.messaging.FixtureLiveChatPublisher;
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

  public void fixtureLiveChatCreate(FixtureLiveChatCommand command, MemberId sender) {
    boolean isCurrentLive = redisService.getCurrentLiveFixtures()
      .stream()
      .anyMatch(fixtureId -> fixtureId.getId().equals(command.getFixtureId()));

    if(!isCurrentLive) throw new IllegalArgumentException("Fixture not found");

    LocalDateTime now = LocalDateTime.now();
    redisService.saveLiveFixtureChat(FixtureId.of(command.getFixtureId()), command.getMessage(), sender, now);
    fixtureLiveChatPublisher.publishFixtureLiveChat(new PublishFixtureLiveChatCommand(command.getFixtureId(), sender.getId(),command.getMessage(), now));
  }
}
