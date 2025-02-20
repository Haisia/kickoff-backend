package com.kickoff.service.match.application.rest;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesForMainPageResponse;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesInPlayResponse;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesQuery;
import com.kickoff.service.match.domain.dto.fixture.GetLeagueSeasonFixturesResponse;
import com.kickoff.service.match.domain.port.input.FixtureApiPullUseCase;
import com.kickoff.service.match.domain.port.input.GetFixtureUseCase;
import com.kickoff.service.match.domain.port.input.GetHeadToHeadUseCase;
import com.kickoff.service.match.domain.valueobject.FixtureId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/fixture", produces = "application/json")
public class FixtureController {

  private final FixtureApiPullUseCase fixtureApiPullUseCase;
  private final GetFixtureUseCase getFixtureUseCase;
  private final GetHeadToHeadUseCase getHeadToHeadUseCase;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllFixtures() {
    fixtureApiPullUseCase.initFixtures();
    return ResponseEntity.ok().build();
  }

  @PostMapping
  public ResponseEntity<ResponseContainer<GetLeagueSeasonFixturesResponse>> getLeagueSeasonFixtures(@RequestBody GetLeagueSeasonFixturesQuery query) {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonFixtures(query));
  }

  @PostMapping("/main")
  public ResponseEntity<ResponseContainer<GetLeagueSeasonFixturesForMainPageResponse>> getLeagueSeasonFixturesForMainPage() {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonFixturesForMainPage());
  }

  @PostMapping("/in-play")
  public ResponseEntity<ResponseContainer<GetLeagueSeasonFixturesInPlayResponse>> getLeagueSeasonInPlayFixtures() {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonInPlayFixtures());
  }

  @PostMapping("/head-to-head/simple/{fixtureId}")
  public ResponseEntity<?> getHeadToHeadSimple(@PathVariable("fixtureId") UUID fixtureId) {
    return ResponseEntity.ok(getHeadToHeadUseCase.getHeadToHeadSimple(FixtureId.of(fixtureId)));
  }
}
