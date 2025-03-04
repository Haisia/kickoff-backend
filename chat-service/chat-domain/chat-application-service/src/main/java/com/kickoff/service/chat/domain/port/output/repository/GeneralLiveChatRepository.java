package com.kickoff.service.chat.domain.port.output.repository;

import com.kickoff.service.chat.domain.entity.GeneralLiveChat;

public interface GeneralLiveChatRepository {
  GeneralLiveChat save(GeneralLiveChat generalLiveChat);
}
