package com.kickoff.log.service;

import com.kickoff.log.service.dto.persist.PersistLogEntryCommand;
import com.kickoff.log.service.port.input.LogEntryPersistUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RequiredArgsConstructor
@Component
public class LogEntryApplicationServiceImpl implements LogEntryPersistUseCase {

  private final LogEntryPersistHandler logEntryPersistHandler;

  @Override
  public void persistLogEntry(PersistLogEntryCommand command) {
    logEntryPersistHandler.persist(command);
  }
}
