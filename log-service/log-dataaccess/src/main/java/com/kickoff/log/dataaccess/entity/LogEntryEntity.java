package com.kickoff.log.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "log_entries")
@Entity
public class LogEntryEntity extends BaseJpaEntity {
  @Id
  private UUID id;
  @Enumerated(EnumType.STRING)
  private LogEntryLevel level;
  private String message;
  private LocalDateTime timestamp;
  @Enumerated(EnumType.STRING)
  private KickoffApplicationName source;

  @OneToMany(mappedBy = "logEntry", cascade = CascadeType.ALL)
  private List<LogEntryMetadataEntity> metadata;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LogEntryEntity entity = (LogEntryEntity) o;
    return Objects.equals(id, entity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
