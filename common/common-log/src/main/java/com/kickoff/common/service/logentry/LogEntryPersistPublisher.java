package com.kickoff.common.service.logentry;

import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import com.kickoff.common.service.dto.PublishLogEntryCommand;

public interface LogEntryPersistPublisher {
  void publish(PublishLogEntryCommand command);
  void publish(LogEntryLevel level, KickoffApplicationName source, String message);
}
