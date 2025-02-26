package com.kickoff.service.chat.dataaccess.repository;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.chat.domain.entity.FixtureLiveChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureLiveChatJpaRepository extends JpaRepository<FixtureLiveChat, FixtureId> {
}
