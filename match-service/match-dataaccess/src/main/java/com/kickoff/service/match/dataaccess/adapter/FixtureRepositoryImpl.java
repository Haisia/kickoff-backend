package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.dataaccess.repository.FixtureJpaRepository;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.port.output.repository.FixtureRepository;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
  public List<Fixture> findByFixtureDateAfterAndFixtureDateBefore(LocalDateTime startDate, LocalDateTime endDate) {
    return fixtureJpaRepository.findByFixtureDateTime_DateAfterAndFixtureDateTime_DateBefore(startDate, endDate);
  }
}
