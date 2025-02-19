package com.kickoff.service.match.application.rest;

import com.kickoff.service.match.domain.port.input.FixtureApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/fixture", produces = "application/json")
public class FixtureController {

  private final FixtureApiPullUseCase fixtureApiPullUseCase;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllFixtures() {
    fixtureApiPullUseCase.initFixtures();
    return ResponseEntity.ok().build();
  }
}
