package com.kickoff.membership.dataaccess.mapper;

import com.kickoff.membership.dataaccess.entity.AttendanceRecordEntity;
import com.kickoff.membership.dataaccess.entity.MemberEntity;
import com.kickoff.membership.domain.entity.AttendanceRecord;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.common.domain.valuobject.MemberId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipDataAccessMapper {

  public Member memberEntityToMember(MemberEntity memberEntity) {
    return Member.builder()
      .id(MemberId.of(memberEntity.getId()))
      .email(memberEntity.getEmail())
      .password(memberEntity.getPassword(), true)
      .point(memberEntity.getPoint())
      .attendanceRecords(attendanceRecordEntitiesToAttendanceRecords(memberEntity.getAttendanceRecords()))
      .build();
  }

  public MemberEntity memberToMemberEntity(Member member) {
    MemberEntity memberEntity = MemberEntity.builder()
      .id(member.getId().getValue())
      .email(member.getEmail().getValue())
      .password(member.getPassword().getValue())
      .point(member.getPoint().getValue())
      .build();
    memberEntity.setAttendanceRecords(attendanceRecordsToAttendanceRecordEntities(member.getAttendanceRecords(), memberEntity));
    return memberEntity;
  }

  private AttendanceRecord attendanceRecordEntityToAttendanceRecord(AttendanceRecordEntity attendanceRecordEntity) {
    return AttendanceRecord.builder()
      .id(attendanceRecordEntity.getId())
      .memberId(MemberId.of(attendanceRecordEntity.getMember().getId()))
      .attendanceDate(attendanceRecordEntity.getAttendanceDate())
      .build();
  }

  private List<AttendanceRecord> attendanceRecordEntitiesToAttendanceRecords(List<AttendanceRecordEntity> attendanceRecordEntities) {
    return attendanceRecordEntities.stream()
      .map(this::attendanceRecordEntityToAttendanceRecord)
      .collect(Collectors.toList()); // 기존 toList()를 Collectors.toList()로 교체
  }

  private AttendanceRecordEntity attendanceRecordToAttendanceRecordEntity(AttendanceRecord attendanceRecord, MemberEntity memberEntity) {
    return AttendanceRecordEntity.builder()
      .id(attendanceRecord.getId())
      .member(memberEntity)
      .attendanceDate(attendanceRecord.getAttendanceDate())
      .build();
  }

  private List<AttendanceRecordEntity> attendanceRecordsToAttendanceRecordEntities(List<AttendanceRecord> attendanceRecords, MemberEntity memberEntity) {
    return attendanceRecords.stream()
      .map(attendanceRecord -> attendanceRecordToAttendanceRecordEntity(attendanceRecord, memberEntity))
      .collect(Collectors.toList()); // 기존 toList()를 Collectors.toList()로 교체
  }
}
