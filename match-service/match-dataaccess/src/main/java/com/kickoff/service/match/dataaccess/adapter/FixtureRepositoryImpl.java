package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.service.match.dataaccess.repository.FixtureJpaRepository;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FixtureRepositoryImpl implements FixtureRepository {

  private final FixtureJpaRepository fixtureJpaRepository;

  @Override
  public Optional<Fixture> findById(FixtureId fixtureId) {
    return fixtureJpaRepository.findById(fixtureId);
  }

  @Override
  public List<Fixture> findByFixtureDateTime_DateBetween(LocalDateTime startDate, LocalDateTime endDate) {
    return fixtureJpaRepository.findByFixtureDateTime_DateBetween(startDate, endDate);
  }

  @Override
  public List<Fixture> findByIdIsIn(Collection<FixtureId> ids) {
    return fixtureJpaRepository.findByIdIsIn(ids);
  }
}
