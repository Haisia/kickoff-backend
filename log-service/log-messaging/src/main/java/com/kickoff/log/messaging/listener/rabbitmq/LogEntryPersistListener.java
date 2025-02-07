package com.kickoff.log.messaging.listener.rabbitmq;

import com.kickoff.log.messaging.config.RabbitMqConfig;
import com.kickoff.log.service.dto.persist.PersistLogEntryCommand;
import com.kickoff.log.service.port.input.LogEntryPersistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogEntryPersistListener {

  private final LogEntryPersistUseCase logEntryPersistUseCase;

  @RabbitListener(queues = RabbitMqConfig.LOG_ENTRY_PERSIST_QUEUE)
  public void persistLogEntry(PersistLogEntryCommand command) {
    logEntryPersistUseCase.persistLogEntry(command);
  }
}
