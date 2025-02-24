package com.kickoff.service.match.application.rest;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.service.command.LeagueCommandService;
import com.kickoff.service.match.domain.service.query.LeagueQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/league", produces = "application/json")
public class LeagueController {

  private final LeagueQueryService leagueQueryService;
  private final LeagueCommandService leagueCommandService;

  @PostMapping("/pull-all")
  public ResponseEntity<?> pullAllLeagues() {
    leagueCommandService.leagueApiPull();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/rank/init")
  public ResponseEntity<?> initRanking() {
    leagueCommandService.initRanking();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/rank/list")
  public ResponseEntity<ResponseContainer<LeagueTeamsResponse>> leagueRankList(@Valid @RequestBody LeagueSeasonQuery query) {
    return ResponseEntity.ok(leagueQueryService.leagueRankList(query));
  }

  @PostMapping("/rank/main/list")
  public ResponseEntity<ResponseContainer<LeagueTeamsResponse>> leagueRankMainList() {
    return ResponseEntity.ok(leagueQueryService.leagueRankMainList());
  }
}
