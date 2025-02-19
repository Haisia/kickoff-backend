package com.kickoff.service.match.domain.mapper.helper;

import com.kickoff.service.match.domain.dto.rank.GetLeagueSeasonRankingResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.SeasonMapTeam;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.valueobject.ScoreStats;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class LeagueToGetLeagueSeasonRankingResponseHelper {

  public GetLeagueSeasonRankingResponse leagueToGetLeagueSeasonRankingResponse(League league, Year year) {
    if (league == null || year == null) return null;

    return GetLeagueSeasonRankingResponse.builder()
      .league(toLeague(league))
      .season(year.getValue())
      .teams(toTeams(league.getSeasonMapTeams(year)))
      .build();
  }

  private GetLeagueSeasonRankingResponse.League toLeague(League league) {
    return GetLeagueSeasonRankingResponse.League.builder()
      .id(league.getId().getId())
      .name(league.getName())
      .logo(league.getLogoUrlAnything())
      .build();
  }

  private GetLeagueSeasonRankingResponse.Team toTeam(SeasonMapTeam seasonMapTeam) {
    Team team = seasonMapTeam.getTeam();
    ScoreStats allScoreStats = seasonMapTeam.getAllScoreStats();

    return GetLeagueSeasonRankingResponse.Team.builder()
      .id(team.getId().getId())
      .name(team.getName())
      .code(team.getCode())
      .logo(team.getLogoUrlAnything())
      .rank(seasonMapTeam.getRank())
      .rankStatus(seasonMapTeam.getRankStatus().toString())
      .points(seasonMapTeam.getPoints())
      .goalsDiff(seasonMapTeam.getGoalsDiff())
      .form(seasonMapTeam.getForm())
      .played(allScoreStats.getPlayed())
      .win(allScoreStats.getWin())
      .draw(allScoreStats.getDraw())
      .lose(allScoreStats.getLose())
      .goalsFor(allScoreStats.getGoalsFor())
      .goalsAgainst(allScoreStats.getGoalsAgainst())
      .build();
  }

  private List<GetLeagueSeasonRankingResponse.Team> toTeams(List<SeasonMapTeam> seasonMapTeams) {
    return seasonMapTeams.stream()
      .map(this::toTeam)
      .toList();
  }
}
