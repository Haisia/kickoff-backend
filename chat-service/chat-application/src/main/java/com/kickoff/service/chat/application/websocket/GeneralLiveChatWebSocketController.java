package com.kickoff.service.chat.application.websocket;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.dto.GeneralLiveChatCommand;
import com.kickoff.service.chat.domain.service.command.GeneralLiveChatCommandService;
import com.kickoff.service.common.domain.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class GeneralLiveChatWebSocketController {

  private final JwtTokenProvider jwtTokenProvider;
  private final GeneralLiveChatCommandService generalLiveChatCommandService;

  @MessageMapping("/chat/general/live/message/send")
  public void generalLiveChatSend(@Valid GeneralLiveChatCommand command) {
    UUID uuid = jwtTokenProvider.parseToken(command.getJwtToken());
    if (uuid == null) throw new IllegalArgumentException("Invalid JWT token");

    generalLiveChatCommandService.generalLiveChatCreate(command, MemberId.of(uuid));
  }
}
