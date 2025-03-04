package com.kickoff.service.chat.dataaccess.repository;

import com.kickoff.common.domain.valuobject.GeneralLiveChatId;
import com.kickoff.service.chat.domain.entity.GeneralLiveChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralLiveChatJpaRepository extends JpaRepository<GeneralLiveChat, GeneralLiveChatId> {
}
