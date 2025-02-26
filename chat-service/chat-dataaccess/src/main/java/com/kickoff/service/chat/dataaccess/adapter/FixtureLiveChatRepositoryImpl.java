package com.kickoff.service.chat.dataaccess.adapter;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.chat.domain.entity.FixtureLiveChat;
import com.kickoff.service.chat.domain.port.output.repository.FixtureLiveChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FixtureLiveChatRepositoryImpl implements FixtureLiveChatRepository {
  @Override
  public Optional<FixtureLiveChat> findByFixtureId(FixtureId fixtureId) {
    return Optional.empty();
  }

  @Override
  public FixtureLiveChat save(FixtureLiveChat fixtureLiveChat) {
    return null;
  }
}
