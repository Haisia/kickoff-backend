package com.kickoff.log.service.port.output;

import com.kickoff.log.domain.entity.LogEntry;

public interface LogEntryRepository {
  LogEntry save(LogEntry logEntry);
}
