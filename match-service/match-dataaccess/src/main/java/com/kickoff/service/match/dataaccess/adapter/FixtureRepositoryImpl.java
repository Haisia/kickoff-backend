package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.dataaccess.repository.FixtureJpaRepository;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FixtureRepositoryImpl implements FixtureRepository {

  private final FixtureJpaRepository fixtureJpaRepository;

  @Override
  public Optional<Fixture> findById(FixtureId fixtureId) {
    return fixtureJpaRepository.findById(fixtureId);
  }
}
