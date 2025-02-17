package com.kickoff.log.dataaccess.repository;

import com.kickoff.common.domain.valuobject.LogEntryId;
import com.kickoff.log.domain.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryJpaRepository extends JpaRepository<LogEntry, LogEntryId> {
}
