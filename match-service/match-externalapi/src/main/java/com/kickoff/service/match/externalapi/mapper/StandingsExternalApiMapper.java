package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.service.match.domain.entity.SeasonMapTeam;
import com.kickoff.service.match.domain.valueobject.RankStatus;
import com.kickoff.service.match.domain.valueobject.ScoreStats;
import com.kickoff.service.match.externalapi.dto.rapidapi.standings.ScoreStatsDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.standings.StandingDto;
import org.springframework.stereotype.Component;

@Component
public class StandingsExternalApiMapper {

  public SeasonMapTeam StandingDtoToSeasonMapTeam(StandingDto standingDto, SeasonMapTeam seasonMapTeam) {
    if (seasonMapTeam == null) return null;

    seasonMapTeam.setRank(standingDto.getRank());
    seasonMapTeam.setRankStatus(RankStatus.parseIgnoreCase(standingDto.getStatus()));
    seasonMapTeam.setPoints(standingDto.getPoints());
    seasonMapTeam.setGoalsDiff(standingDto.getGoalsDiff());
    seasonMapTeam.setForm(standingDto.getForm());
    seasonMapTeam.setAllScoreStats(scoreStatsDtoToScoreStats(standingDto.getAll()));
    seasonMapTeam.setHomeScoreStats(scoreStatsDtoToScoreStats(standingDto.getHome()));
    seasonMapTeam.setAwayScoreStats(scoreStatsDtoToScoreStats(standingDto.getAway()));
    return seasonMapTeam;
  }

  private ScoreStats scoreStatsDtoToScoreStats(ScoreStatsDto scoreStatsDto) {
    return ScoreStats.builder()
      .played(scoreStatsDto.getPlayed())
      .win(scoreStatsDto.getWin())
      .draw(scoreStatsDto.getDraw())
      .lose(scoreStatsDto.getLose())
      .goalsFor(scoreStatsDto.getGoals().getGoalsFor())
      .goalsAgainst(scoreStatsDto.getGoals().getAgainst())
      .build();
  }

}
