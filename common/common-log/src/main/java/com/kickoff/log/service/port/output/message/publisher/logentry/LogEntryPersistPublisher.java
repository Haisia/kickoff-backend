package com.kickoff.log.service.port.output.message.publisher.logentry;

import com.kickoff.log.service.dto.PublishLogEntryCommand;

public interface LogEntryPersistPublisher {
  void publish(PublishLogEntryCommand command);
}
