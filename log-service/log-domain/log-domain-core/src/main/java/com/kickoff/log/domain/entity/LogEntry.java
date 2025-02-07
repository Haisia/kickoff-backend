package com.kickoff.log.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryId;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class LogEntry extends AggregateRoot<LogEntryId> {

  private static final LogEntryLevel PERSIST_LOG_LEVEL = LogEntryLevel.ERROR;

  private LogEntryId id;
  private LogEntryLevel level;
  private String message;
  private LocalDateTime timestamp;
  private KickoffApplicationName source;
  private Map<String, Object> metadata;

  public String format() {
    return "[" + timestamp + "] " + level + " - " + source + ": " + message;
  }

  public void addMetadata(String key, Object value) {
    if (metadata == null) {
      metadata = new HashMap<>();
    }
    metadata.put(key, value);
  }

  public boolean isPersistableLevel() {
    return level.compareTo(PERSIST_LOG_LEVEL) <= 0;
  }

  private LogEntry(LogEntryLevel level, String message, KickoffApplicationName source, Map<String, Object> metadata) {
    this.id = LogEntryId.of(UUID.randomUUID());
    this.level = level;
    this.message = message;
    this.source = source;
    this.timestamp = LocalDateTime.now();
    this.metadata = metadata;
  }

  private LogEntry(Builder builder) {
    if (builder.id == null) builder.id = LogEntryId.of(UUID.randomUUID());
    setId(builder.id);
    setLevel(builder.level);
    setMessage(builder.message);
    setTimestamp(builder.timestamp);
    setSource(builder.source);
    setMetadata(builder.metadata);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private LogEntryId id;
    private LogEntryLevel level;
    private String message;
    private LocalDateTime timestamp;
    private KickoffApplicationName source;
    private Map<String, Object> metadata = new HashMap<>();

    public Builder id(UUID val) {
      id = LogEntryId.of(val);
      return this;
    }

    public Builder id(LogEntryId val) {
      id = val;
      return this;
    }

    public Builder level(LogEntryLevel val) {
      level = val;
      return this;
    }

    public Builder message(String val) {
      message = val;
      return this;
    }

    public Builder timestamp(LocalDateTime val) {
      timestamp = val;
      return this;
    }

    public Builder source(KickoffApplicationName val) {
      source = val;
      return this;
    }

    public Builder metadata(Map<String, Object> val) {
      if (val != null) metadata = val;
      return this;
    }

    public LogEntry build() {
      return new LogEntry(this);
    }

    private Builder() {
    }

  }

}
