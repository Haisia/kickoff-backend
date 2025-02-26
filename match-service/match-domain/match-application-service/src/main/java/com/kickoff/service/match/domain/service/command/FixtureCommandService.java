package com.kickoff.service.match.domain.service.command;

import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.common.domain.dto.ResponseContainer;
import com.kickoff.service.match.domain.dto.fixture.FixtureCommentCreateCommand;
import com.kickoff.service.match.domain.dto.fixture.FixtureCommentResponse;
import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.FixtureComment;
import com.kickoff.service.match.domain.entity.League;
import com.kickoff.service.match.domain.exception.LeagueNotFoundException;
import com.kickoff.service.match.domain.port.output.repository.LeagueRepository;
import com.kickoff.common.domain.valuobject.FixtureId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * ❗❗❗❗
 * `Command` Service 이므로 LeagueRepository 외의 repository 는 의존하지 말 것
 * ❗❗❗❗
 * */
@RequiredArgsConstructor
@Transactional
@Service
public class FixtureCommandService {

  private final LeagueRepository leagueRepository;
  private final FixtureApiPullHandler fixtureApiPullHandler;

  public ResponseContainer<FixtureCommentResponse> fixtureCommentCreate(MemberId memberId, FixtureCommentCreateCommand command) {
    LeagueId leagueId = LeagueId.of(command.getLeagueId());
    FixtureId fixtureId = FixtureId.of(command.getFixtureId());

    League league = leagueRepository.findById(leagueId).orElseThrow(() -> new LeagueNotFoundException(leagueId));
    FixtureComment fixtureComment = league.createFixtureComment(command.getYear(), fixtureId, command.getComment(), memberId);

    return new ResponseContainer<>(command, List.of(FixtureCommentResponse.from(fixtureComment)));
  }

  public void fixtureStatisticsUpdate(Fixture fixture) {
    fixtureApiPullHandler.updateFixturesStatistics(fixture);
  }
}
