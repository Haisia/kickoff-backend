package com.kickoff.log.service;

import com.kickoff.log.domain.entity.LogEntry;
import com.kickoff.log.service.dto.persist.PersistLogEntryCommand;
import com.kickoff.log.service.port.output.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogEntryPersistHandler {

  private final LogEntryRepository logEntryRepository;

  public void persist(PersistLogEntryCommand command) {
    LogEntry logEntry = LogEntry.builder()
      .level(command.getLevel())
      .message(command.getMessage())
      .timestamp(command.getTimestamp())
      .source(command.getSource())
      .build();

    logEntryRepository.save(logEntry);
  }
}
