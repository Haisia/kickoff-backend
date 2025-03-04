package com.kickoff.service.chat.application.rest;

import com.kickoff.service.chat.domain.service.query.GeneralLiveChatQueryService;
import com.kickoff.service.common.domain.dto.ChatMessageRedisDto;
import com.kickoff.service.common.domain.dto.ResponseContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/chat/general")
@RestController
public class GeneralLiveChatController {

  private final GeneralLiveChatQueryService generalLiveChatQueryService;

  @PostMapping("/live/message/list")
  public ResponseEntity<ResponseContainer<ChatMessageRedisDto>> generalLiveMessageList() {
    return ResponseEntity.ok(generalLiveChatQueryService.generalLiveMessageList());
  }
}
