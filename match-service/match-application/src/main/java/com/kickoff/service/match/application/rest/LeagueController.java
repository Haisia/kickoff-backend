package com.kickoff.service.match.application.rest;

import com.kickoff.service.match.domain.port.input.LeagueApiPullUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/league", produces = "application/json")
public class LeagueController {

  private final LeagueApiPullUseCase leagueApiPullUseCase;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllLeagues() {
    leagueApiPullUseCase.leagueApiPull();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/rank/init")
  public ResponseEntity<?> initRanking() {
    leagueApiPullUseCase.initRanking();
    return ResponseEntity.ok().build();
  }

}
