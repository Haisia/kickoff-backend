package com.kickoff.chat.service.messaging.listener;

import com.kickoff.service.chat.domain.dto.PublishGeneralLiveChatCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GeneralLiveChatListener {

  private final SimpMessagingTemplate messagingTemplate;

  @RabbitListener(queues = "general_live_chat_queue")
  public void listenGeneralLiveChat(PublishGeneralLiveChatCommand command) {
    messagingTemplate.convertAndSend("/sub/general-live-chat", command);
  }
}
