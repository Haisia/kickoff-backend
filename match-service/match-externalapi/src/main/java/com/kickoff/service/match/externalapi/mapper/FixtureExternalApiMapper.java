package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.externalapi.dto.rapidapi.fixtures.FixturesResponse;
import com.kickoff.service.match.externalapi.mapper.helper.FixturesResponseToFixtureHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureExternalApiMapper {

  private final FixturesResponseToFixtureHelper fixturesResponseToFixtureHelper;

  public Fixture fixturesResponseToFixture(FixturesResponse fixturesResponse, League league) {
    return fixturesResponseToFixtureHelper.fixturesResponseToFixture(fixturesResponse, league);
  }
}
