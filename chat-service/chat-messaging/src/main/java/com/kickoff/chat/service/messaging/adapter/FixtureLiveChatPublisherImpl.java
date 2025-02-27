package com.kickoff.chat.service.messaging.adapter;

import com.kickoff.service.chat.domain.dto.PublishFixtureLiveChatCommand;
import com.kickoff.service.chat.domain.port.output.messaging.FixtureLiveChatPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureLiveChatPublisherImpl implements FixtureLiveChatPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void publishFixtureLiveChat(PublishFixtureLiveChatCommand command) {
    rabbitTemplate.convertAndSend("fixture_live_chat_exchange", "fixture_live_chat_queue", command);
  }
}
