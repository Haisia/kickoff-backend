package com.kickoff.service.chat.domain.port.output.repository;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.chat.domain.entity.FixtureLiveChat;

import java.util.Optional;

public interface FixtureLiveChatRepository {
  Optional<FixtureLiveChat> findByFixtureId(FixtureId fixtureId);
  FixtureLiveChat save(FixtureLiveChat fixtureLiveChat);
}
