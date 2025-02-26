package com.kickoff.service.chat.application.rest;

import com.kickoff.common.application.annotation.LoginMember;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.dto.FixtureLiveChatCommand;
import com.kickoff.service.chat.domain.service.command.FixtureLiveChatCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/chat/fixture")
@RestController
public class FixtureLiveChatController {

  private final FixtureLiveChatCommandService fixtureLiveChatCommandService;

  @PostMapping("/live/create")
  public void fixtureLiveChatCreate(@LoginMember MemberId sender, @Valid @RequestBody FixtureLiveChatCommand command) {
    fixtureLiveChatCommandService.fixtureLiveChatCreate(command, sender);
  }
}
