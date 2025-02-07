package com.kickoff.membership.service;

import com.kickoff.common.domain.valuobject.LogEntryLevel;
import com.kickoff.common.service.logentry.LogEntryPersistPublisher;
import com.kickoff.membership.domain.entity.AttendanceRecord;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.service.dto.attend.AttendMemberCommand;
import com.kickoff.membership.service.exception.NotFoundMemberException;
import com.kickoff.membership.service.port.input.MemberAttendanceUseCase;
import com.kickoff.membership.service.port.output.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberAttendanceHandler implements MemberAttendanceUseCase {

  private final MemberRepository memberRepository;
  private final LogEntryPersistPublisher logEntryPersistPublisher;

  @Transactional
  @Override
  public void attendMember(AttendMemberCommand command) {
    Member member = memberRepository.findById(command.getMemberId())
      .orElseThrow(() -> new NotFoundMemberException("memberId", command.getMemberId().getValue().toString()));

    member.checkAttendance();
    member.addPoint(AttendanceRecord.attendancePoint);
    memberRepository.save(member);

    String logMessage = String.format("[*] 출석체크하여 %s 포인트를 적립하였습니다. : memberId=%s, currentPoint=%s"
      , AttendanceRecord.attendancePoint.toString()
      , member.getId()
      , member.getPoint().getValue());
    log.info(logMessage);

    logEntryPersistPublisher.publish(LogEntryLevel.INFO, logMessage);
  }
}
