package com.kickoff.service.match.application.rest;

import com.kickoff.common.service.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.LeagueSeasonQuery;
import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.port.input.LeagueApiPullUseCase;
import com.kickoff.service.match.domain.service.query.LeagueQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/matches/league", produces = "application/json")
public class LeagueController {

  private final LeagueApiPullUseCase leagueApiPullUseCase;
  private final LeagueQueryService leagueQueryService;

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

  @PostMapping("/rank/list")
  public ResponseEntity<ResponseContainer<LeagueTeamsResponse>> leagueRankList(@Valid @RequestBody LeagueSeasonQuery query) {
    return ResponseEntity.ok(leagueQueryService.leagueRankList(query));
  }

  @PostMapping("/rank/main/list")
  public ResponseEntity<ResponseContainer<LeagueTeamsResponse>> leagueRankMainList() {
    return ResponseEntity.ok(leagueQueryService.leagueRankMainList());
  }
}
