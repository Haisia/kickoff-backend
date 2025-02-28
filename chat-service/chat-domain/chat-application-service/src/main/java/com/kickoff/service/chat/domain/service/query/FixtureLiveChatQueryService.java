package com.kickoff.service.chat.domain.service.query;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.chat.domain.dto.FixtureLiveChatQuery;
import com.kickoff.service.common.domain.dto.ChatMessage;
import com.kickoff.service.common.domain.dto.ResponseContainer;
import com.kickoff.service.common.domain.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class FixtureLiveChatQueryService {

  private final RedisService redisService;

  public ResponseContainer<ChatMessage> fixtureLiveMessageList(FixtureLiveChatQuery query) {
    List<ChatMessage> messageHistories = redisService.getFixtureLiveChatMessages(FixtureId.of(query.getFixtureId()));
    return new ResponseContainer<>(query, messageHistories);
  }
}
