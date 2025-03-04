package com.kickoff.service.chat.domain.service.query;

import com.kickoff.service.common.domain.dto.ChatMessageRedisDto;
import com.kickoff.service.common.domain.dto.ResponseContainer;
import com.kickoff.service.common.domain.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GeneralLiveChatQueryService {

  private final RedisService redisService;

  public ResponseContainer<ChatMessageRedisDto> generalLiveMessageList() {
    List<ChatMessageRedisDto> messageHistories = redisService.getFixtureLiveChatMessages();
    return new ResponseContainer<>("", messageHistories);
  }
}
