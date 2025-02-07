package com.kickoff.log.dataaccess.mapper;

import com.kickoff.log.dataaccess.entity.LogEntryEntity;
import com.kickoff.log.dataaccess.entity.LogEntryMetadataEntity;
import com.kickoff.log.domain.entity.LogEntry;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LogEntryDataaccessMapper {

  public LogEntry logEntryEntityToLogEntry(LogEntryEntity logEntryEntity) {
    return LogEntry.builder()
      .id(logEntryEntity.getId())
      .level(logEntryEntity.getLevel())
      .message(logEntryEntity.getMessage())
      .timestamp(logEntryEntity.getTimestamp())
      .source(logEntryEntity.getSource())
      .metadata(logEntryMetadataEntityToMap(logEntryEntity.getMetadata()))
      .build();
  }

  public LogEntryEntity logEntryToLogEntryEntity(LogEntry logEntry) {
    return LogEntryEntity.builder()
      .id(logEntry.getId().getValue())
      .level(logEntry.getLevel())
      .message(logEntry.getMessage())
      .timestamp(logEntry.getTimestamp())
      .source(logEntry.getSource())
      .metadata(mapToLogEntryMetadataEntity(logEntry.getMetadata()))
      .build();
  }

  private Map<String, Object> logEntryMetadataEntityToMap(List<LogEntryMetadataEntity> metadata) {
    Map<String, Object> map = new HashMap<>();
    for (LogEntryMetadataEntity metadatum : metadata) {
      map.put(metadatum.getKey(), metadatum.getValue());
    }
    return map;
  }

  private List<LogEntryMetadataEntity> mapToLogEntryMetadataEntity(Map<String, Object> map) {
    List<LogEntryMetadataEntity> metadata = new ArrayList<>();
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      LogEntryMetadataEntity metadatum = LogEntryMetadataEntity.builder()
        .key(entry.getKey())
        .value((String) entry.getValue())
        .build();
      metadata.add(metadatum);
    }
    return metadata;
  }

}
