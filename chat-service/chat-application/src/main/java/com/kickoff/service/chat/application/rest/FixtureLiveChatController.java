package com.kickoff.service.chat.application.rest;

import com.kickoff.service.chat.domain.dto.FixtureLiveChatQuery;
import com.kickoff.service.chat.domain.service.query.FixtureLiveChatQueryService;
import com.kickoff.service.common.domain.dto.ChatMessageRedisDto;
import com.kickoff.service.common.domain.dto.ResponseContainer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/chat/fixture")
@RestController
public class FixtureLiveChatController {

  private final FixtureLiveChatQueryService fixtureLiveChatQueryService;

  @PostMapping("/live/message/list")
  public ResponseEntity<ResponseContainer<ChatMessageRedisDto>> fixtureLiveMessageList(@Valid @RequestBody FixtureLiveChatQuery query) {
    return ResponseEntity.ok(fixtureLiveChatQueryService.fixtureLiveMessageList(query));
  }
}
