package com.kickoff.common.service.dto;

import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class PublishLogEntryCommand {
  public LogEntryLevel level;
  public String message;
  public LocalDateTime timestamp;
  public KickoffApplicationName source;
  public Map<String, Object> metadata;

  @Builder
  public PublishLogEntryCommand(LogEntryLevel level, String message, KickoffApplicationName source, Map<String, Object> metadata) {
    this.level = level;
    this.message = message;
    this.timestamp = LocalDateTime.now();
    this.source = source;
    this.metadata = metadata;
  }
}
