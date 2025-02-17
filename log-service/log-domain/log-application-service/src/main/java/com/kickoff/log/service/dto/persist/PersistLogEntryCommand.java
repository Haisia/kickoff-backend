package com.kickoff.log.service.dto.persist;

import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersistLogEntryCommand {
  public LogEntryLevel level;
  public String message;
  public LocalDateTime timestamp;
  public KickoffApplicationName source;
}
