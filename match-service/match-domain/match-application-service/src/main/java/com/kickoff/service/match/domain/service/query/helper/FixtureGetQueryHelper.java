package com.kickoff.service.match.domain.service.query.helper;

import com.kickoff.service.match.domain.exception.FixtureNotFoundException;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureGetQueryHelper {

  private final FixtureRepository fixtureRepository;

  public com.kickoff.service.match.domain.entity.Fixture fixtureGet(FixtureId fixtureId) {
    return fixtureRepository.findById(fixtureId)
      .orElseThrow(() -> new FixtureNotFoundException(fixtureId));
  }

}
