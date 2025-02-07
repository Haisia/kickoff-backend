package com.kickoff.common.service.messaging;

import com.kickoff.common.service.dto.PublishLogEntryCommand;
import com.kickoff.common.service.logentry.LogEntryPersistPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogEntryPersistPublisherImpl implements LogEntryPersistPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void publish(PublishLogEntryCommand command) {
    rabbitTemplate.convertAndSend("log_entry_exchange", "log_entry_persist_queue", command);
  }
}
