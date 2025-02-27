package com.kickoff.service.chat.domain.port.output.messaging;

import com.kickoff.service.chat.domain.dto.PublishFixtureLiveChatCommand;

public interface FixtureLiveChatPublisher {
  void publishFixtureLiveChat(PublishFixtureLiveChatCommand command);
}
