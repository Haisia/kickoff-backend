package com.kickoff.service.chat.domain.service.command;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.dto.GeneralLiveChatCommand;
import com.kickoff.service.chat.domain.dto.PublishGeneralLiveChatCommand;
import com.kickoff.service.chat.domain.port.output.messaging.GeneralLiveChatPublisher;
import com.kickoff.service.common.domain.dto.MemberRedisDto;
import com.kickoff.service.common.domain.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class GeneralLiveChatCommandService {

  private final RedisService redisService;
  private final GeneralLiveChatPublisher generalLiveChatPublisher;

  public void generalLiveChatCreate(GeneralLiveChatCommand command, MemberId memberId) {
    LocalDateTime now = LocalDateTime.now();
    redisService.saveLiveGeneralChat(command.getMessage(), memberId, now);

    MemberRedisDto member = redisService.getLoginMember(memberId).orElseThrow(() -> new IllegalArgumentException("Sender is not logged in"));

    PublishGeneralLiveChatCommand publishGeneralLiveChatCommand = PublishGeneralLiveChatCommand.builder()
      .memberId(memberId.getId())
      .nickname(member.getNickname())
      .message(command.getMessage())
      .timestamp(now)
      .build();

    generalLiveChatPublisher.publishGeneralLiveChat(publishGeneralLiveChatCommand);
  }
}
