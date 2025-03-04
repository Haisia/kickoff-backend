package com.kickoff.service.chat.application.batch;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.entity.GeneralLiveChat;
import com.kickoff.service.chat.domain.service.command.GeneralLiveChatCommandService;
import com.kickoff.service.common.domain.service.RedisService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class GeneralLiveChatBatch {

  private final RedisService redisService;
  private final GeneralLiveChatCommandService generalLiveChatCommandService;

  @Scheduled(cron = "0 0 0 * * *", zone = "UTC")
  public void saveTodayLiveFixturesDaily() {
    log.info("[*] 매일 자정: 채팅기록을 redis 에서 database 로 persist 합니다.");
    persistGeneralLiveChatHistoryAndClear();
  }

  @PostConstruct
  private void persistGeneralLiveChatHistoryAndClear() {
    redisService.getAllGeneralLiveChatMessages()
      .stream()
      .map(dto->new GeneralLiveChat(null, dto.getMessage(), MemberId.of(dto.getMemberId()), dto.getTimestamp()))
      .forEach(generalLiveChatCommandService::persistGeneralLiveChat);

    redisService.clearGeneralLiveChatMessages();
  }

}
