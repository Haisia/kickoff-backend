package com.kickoff.service.match.application.rest;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.FixtureResponse;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.fixture.LeagueFixtureQuery;
import com.kickoff.service.match.domain.port.input.FixtureApiPullUseCase;
import com.kickoff.service.match.domain.port.input.GetFixtureUseCase;
import com.kickoff.service.match.domain.port.input.GetHeadToHeadUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("/list")
  public ResponseEntity<ResponseContainer<FixtureResponse>> fixtureList(@RequestBody LeagueSeasonQuery query) {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonFixtures(query));
  }

  @PostMapping("/main/list")
  public ResponseEntity<ResponseContainer<LeagueFixtureResponse>> fixtureMainList() {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonFixturesForMainPage());
  }

  @PostMapping("/in-play/list")
  public ResponseEntity<ResponseContainer<LeagueFixtureResponse>> fixtureInPlayList() {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonInPlayFixtures());
  }

  @PostMapping("/head-to-head/simple/list")
  public ResponseEntity<?> fixtureH2HSimpleList(@RequestBody @Valid LeagueFixtureQuery query) {
    return ResponseEntity.ok(getHeadToHeadUseCase.getHeadToHeadSimple(query));
  }

  @PostMapping("/get")
  public ResponseEntity<?> fixtureGet(@RequestBody @Valid LeagueFixtureQuery query) {
    return ResponseEntity.ok(getFixtureUseCase.getLeagueSeasonFixture(query));
  }
}
