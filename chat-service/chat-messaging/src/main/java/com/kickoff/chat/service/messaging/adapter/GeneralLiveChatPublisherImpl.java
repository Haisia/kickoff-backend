package com.kickoff.chat.service.messaging.adapter;

import com.kickoff.service.chat.domain.dto.PublishGeneralLiveChatCommand;
import com.kickoff.service.chat.domain.port.output.messaging.GeneralLiveChatPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GeneralLiveChatPublisherImpl implements GeneralLiveChatPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void publishGeneralLiveChat(PublishGeneralLiveChatCommand command) {
    rabbitTemplate.convertAndSend("general_live_chat_exchange", "general_live_chat_queue", command);
  }
}
