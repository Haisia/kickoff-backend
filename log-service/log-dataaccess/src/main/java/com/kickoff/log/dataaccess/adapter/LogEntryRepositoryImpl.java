package com.kickoff.log.dataaccess.adapter;

import com.kickoff.log.dataaccess.entity.LogEntryEntity;
import com.kickoff.log.dataaccess.mapper.LogEntryDataaccessMapper;
import com.kickoff.log.dataaccess.repository.LogEntryJpaRepository;
import com.kickoff.log.domain.entity.LogEntry;
import com.kickoff.log.service.port.output.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogEntryRepositoryImpl implements LogEntryRepository {

  private final LogEntryJpaRepository logEntryJpaRepository;
  private final LogEntryDataaccessMapper logEntryDataaccessMapper;

  @Override
  public LogEntry save(LogEntry logEntry) {
    LogEntryEntity entity = logEntryDataaccessMapper.logEntryToLogEntryEntity(logEntry);
    return logEntryDataaccessMapper.logEntryEntityToLogEntry(logEntryJpaRepository.save(entity));
  }
}
