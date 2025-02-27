package com.kickoff.chat.service.messaging.listener;

import com.kickoff.service.chat.domain.dto.FixtureLiveChatCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureLiveChatListener {

  private final SimpMessagingTemplate messagingTemplate;

  @RabbitListener(queues = "fixture_live_chat_queue")
  public void listenFixtureLiveChat(FixtureLiveChatCommand command) {
    messagingTemplate.convertAndSend("/sub/some-topic", command);
  }
}
