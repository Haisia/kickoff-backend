package com.kickoff.service.match.application.rest;

import com.kickoff.common.application.annotation.LoginMember;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.*;
import com.kickoff.service.match.domain.service.command.FixtureCommandService;
import com.kickoff.service.match.domain.service.command.LeagueCommandService;
import com.kickoff.service.match.domain.service.query.FixtureQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/fixture", produces = "application/json")
public class FixtureController {

  private final LeagueCommandService leagueCommandService;
  private final FixtureQueryService fixtureQueryService;
  private final FixtureCommandService fixtureCommandService;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllFixtures() {
    leagueCommandService.initFixtures();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/get")
  public ResponseEntity<ResponseContainer<FixtureResponse>> fixtureGet(@RequestBody @Valid FixtureQuery query) {
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
  public ResponseEntity<ResponseContainer<FixtureResponse>> fixtureH2HSimpleList(@RequestBody @Valid FixtureQuery query) {
    return ResponseEntity.ok(fixtureQueryService.fixtureH2HSimpleList(query));
  }

  @PostMapping("/comment/create")
  public ResponseEntity<ResponseContainer<FixtureCommentResponse>> fixtureCommentCreate(@LoginMember MemberId memberId, @RequestBody @Valid FixtureCommentCreateCommand command) {
    return ResponseEntity.ok(fixtureCommandService.fixtureCommentCreate(memberId, command));
  }

  @PostMapping("/comment/list")
  public ResponseEntity<ResponseContainer<FixtureCommentResponse>> fixtureCommentList(@RequestBody @Valid FixtureQuery query) {
    return ResponseEntity.ok(fixtureQueryService.fixtureCommentList(query));
  }
}
