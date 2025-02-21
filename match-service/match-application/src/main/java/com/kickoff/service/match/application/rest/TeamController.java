package com.kickoff.service.match.application.rest;

import com.kickoff.service.match.domain.port.input.GetHeadToHeadUseCase;
import com.kickoff.service.match.domain.port.input.TeamApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/team", produces = "application/json")
public class TeamController {

  private final TeamApiPullUseCase teamApiPullUseCase;
  private final GetHeadToHeadUseCase getHeadToHeadUseCase;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllTeams() {
    teamApiPullUseCase.teamApiPullAndMappingSeason();
    return ResponseEntity.ok().build();
  }
}
