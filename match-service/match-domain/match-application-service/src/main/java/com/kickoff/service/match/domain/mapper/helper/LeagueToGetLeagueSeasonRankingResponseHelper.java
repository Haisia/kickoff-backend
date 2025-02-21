package com.kickoff.service.match.domain.mapper.helper;

import com.kickoff.service.match.domain.dto.rank.LeagueTeamsResponse;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.entity.SeasonMapTeam;
import com.kickoff.service.match.domain.entity.Team;
import com.kickoff.service.match.domain.valueobject.ScoreStats;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class LeagueToGetLeagueSeasonRankingResponseHelper {

  public LeagueTeamsResponse leagueToGetLeagueSeasonRankingResponse(League league, Year year) {
    if (league == null || year == null) return null;

    return LeagueTeamsResponse.builder()
      .league(toLeague(league))
      .season(year.getValue())
      .teams(toTeams(league.getSeasonMapTeams(year)))
      .build();
  }

  private LeagueTeamsResponse.League toLeague(League league) {
    return LeagueTeamsResponse.League.builder()
      .id(league.getId().getId())
      .name(league.getName())
      .logo(league.getLogoUrlAnything())
      .build();
  }

  private LeagueTeamsResponse.Team toTeam(SeasonMapTeam seasonMapTeam) {
    Team team = seasonMapTeam.getTeam();
    ScoreStats allScoreStats = seasonMapTeam.getAllScoreStats();

    return LeagueTeamsResponse.Team.builder()
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

  private List<LeagueTeamsResponse.Team> toTeams(List<SeasonMapTeam> seasonMapTeams) {
    return seasonMapTeams.stream()
      .map(this::toTeam)
      .toList();
  }
}
