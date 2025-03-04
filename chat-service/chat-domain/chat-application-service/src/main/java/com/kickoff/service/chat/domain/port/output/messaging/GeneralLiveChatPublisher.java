package com.kickoff.service.chat.domain.port.output.messaging;

import com.kickoff.service.chat.domain.dto.PublishGeneralLiveChatCommand;

public interface GeneralLiveChatPublisher {
  void publishGeneralLiveChat(PublishGeneralLiveChatCommand command);
}
