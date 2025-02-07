package com.kickoff.log.dataaccess.repository;

import com.kickoff.log.dataaccess.entity.LogEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogEntryJpaRepository extends JpaRepository<LogEntryEntity, UUID> {
}
