package com.kickoff.service.match.application.rest;

import com.kickoff.service.match.domain.service.command.LeagueCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/team", produces = "application/json")
public class TeamController {

  private final LeagueCommandService leagueCommandService;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllTeams() {
    leagueCommandService.teamApiPullAndMappingSeason();
    return ResponseEntity.ok().build();
  }
}
