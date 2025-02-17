package com.kickoff.log.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryId;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "log_entries")
@Entity
public class LogEntry extends AggregateRoot {

  private static final LogEntryLevel PERSIST_LOG_LEVEL = LogEntryLevel.ERROR;

  @EmbeddedId
  private LogEntryId id;
  @Enumerated(EnumType.STRING)
  private LogEntryLevel level;
  private String message;
  private LocalDateTime timestamp;
  @Enumerated(EnumType.STRING)
  private KickoffApplicationName source;

  public String format() {
    return "[" + timestamp + "] " + level + " - " + source + ": " + message;
  }

  public boolean isPersistableLevel() {
    return level.compareTo(PERSIST_LOG_LEVEL) <= 0;
  }

  @Builder
  private LogEntry(LogEntryLevel level, String message, LocalDateTime timestamp, KickoffApplicationName source) {
    this.id = LogEntryId.generate();
    this.level = level;
    this.message = message;
    if (this.message == null) timestamp = LocalDateTime.now();
    this.timestamp = timestamp;
    this.source = source;
  }
}
