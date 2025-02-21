package com.kickoff.service.match.application.rest;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.port.input.FixtureApiPullUseCase;
import com.kickoff.service.match.domain.service.query.FixtureQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/fixture", produces = "application/json")
public class FixtureController {

  private final FixtureApiPullUseCase fixtureApiPullUseCase;

  private final FixtureQueryService fixtureQueryService;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllFixtures() {
    fixtureApiPullUseCase.initFixtures();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/get")
  public ResponseEntity<ResponseContainer<FixtureResponse>> fixtureGet(@RequestBody @Valid LeagueFixtureQuery query) {
    return ResponseEntity.ok(fixtureQueryService.fixtureGet(query));
  }

  @PostMapping("/list")
  public ResponseEntity<ResponseContainer<FixtureResponse>> fixtureList(@RequestBody LeagueSeasonQuery query) {
    return ResponseEntity.ok(fixtureQueryService.fixtureList(query));
  }

  @PostMapping("/main/list")
  public ResponseEntity<ResponseContainer<LeagueFixtureResponse>> fixtureMainList() {
    return ResponseEntity.ok(fixtureQueryService.fixtureMainList());
  }

  @PostMapping("/in-play/list")
  public ResponseEntity<ResponseContainer<LeagueFixtureResponse>> fixtureInPlayList() {
    return ResponseEntity.ok(fixtureQueryService.fixtureInPlayList());
  }

  @PostMapping("/head-to-head/simple/list")
  public ResponseEntity<ResponseContainer<FixtureResponse>> fixtureH2HSimpleList(@RequestBody @Valid LeagueFixtureQuery query) {
    return ResponseEntity.ok(fixtureQueryService.fixtureH2HSimpleList(query));
  }
}
