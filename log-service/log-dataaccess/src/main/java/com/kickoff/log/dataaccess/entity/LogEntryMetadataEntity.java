package com.kickoff.log.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity(name = "log_entry_metadata")
public class LogEntryMetadataEntity extends BaseJpaEntity {
  @Id
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "log_entry_id")
  private LogEntryEntity logEntry;
  private String key;
  private String value;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LogEntryMetadataEntity that = (LogEntryMetadataEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
