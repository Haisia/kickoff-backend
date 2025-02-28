package com.kickoff.service.chat.application.websocket;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.dto.FixtureLiveChatCommand;
import com.kickoff.service.chat.domain.service.command.FixtureLiveChatCommandService;
import com.kickoff.service.common.domain.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class FixtureLiveChatWebSocketController {

  private final JwtTokenProvider jwtTokenProvider;
  private final FixtureLiveChatCommandService fixtureLiveChatCommandService;

  @MessageMapping("/chat/fixture/live/message/send")
  public void fixtureLiveChatSend(@Valid FixtureLiveChatCommand command) {
    UUID uuid = jwtTokenProvider.parseToken(command.getJwtToken());
    if (uuid == null) throw new IllegalArgumentException("Invalid JWT token");

    fixtureLiveChatCommandService.fixtureLiveChatCreate(command, MemberId.of(uuid));
  }
}
