package com.kickoff.log.service.port.input;

import com.kickoff.log.service.dto.persist.PersistLogEntryCommand;

public interface LogEntryPersistUseCase {
  void persistLogEntry(PersistLogEntryCommand command);
}
