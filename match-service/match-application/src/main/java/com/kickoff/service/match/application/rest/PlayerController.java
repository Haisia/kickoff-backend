package com.kickoff.service.match.application.rest;

import com.kickoff.service.match.domain.port.input.PlayerApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/player", produces = "application/json")
public class PlayerController {

  private final PlayerApiPullUseCase playerApiPullUseCase;

  @PostMapping("/init")
  public ResponseEntity<?> pullAllTeams() {
    playerApiPullUseCase.playerApiPull();
    return ResponseEntity.ok().build();
  }
}
