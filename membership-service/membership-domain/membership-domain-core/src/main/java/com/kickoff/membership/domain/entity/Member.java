package com.kickoff.membership.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.membership.domain.exception.MemberDomainException;
import com.kickoff.membership.domain.valueobject.Email;
import com.kickoff.membership.domain.valueobject.Password;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.common.domain.valuobject.Point;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "members")
@Entity
public class Member extends AggregateRoot {
  @EmbeddedId
  private MemberId id;
  @Embedded
  private Email email;
  @Embedded
  private Password password;
  @Embedded
  private Point point;

  @ElementCollection
  @CollectionTable(name = "member_attendance_record", joinColumns = @JoinColumn(name = "member_id"))
  private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

  public void checkAttendance() {
    LocalDate today = LocalDate.now();
    boolean alreadyChecked = attendanceRecords.stream()
      .anyMatch(record -> record.getAttendanceDate().equals(today));
    if (alreadyChecked) {
      throw new MemberDomainException(
        String.format("오늘은 이미 출석 체크를 완료했습니다. : memberId=%s", getId().getId())
        , CustomHttpStatus.BAD_REQUEST
      );
    }

    attendanceRecords.add(AttendanceRecord.generateToday());
  }

  public void addPoint(BigDecimal point) {
    this.point = this.point.add(Point.of(point));
  }

  public void validateMember() {
    List<String> errors = new ArrayList<>();
    validateEmail(errors);
    validatePassword(errors);

    if (!errors.isEmpty()) {
      throw new DomainException(String.join(" ", errors), CustomHttpStatus.BAD_REQUEST);
    }
  }

  public void initializeMember() {
    setId(MemberId.of(UUID.randomUUID()));
  }

  @Builder
  public Member(MemberId id, Email email, Password password, Point point) {
    if (id == null) id = MemberId.generate();
    this.id = id;
    this.email = email;
    this.password = password;
    if (point == null) point = Point.of(BigDecimal.ZERO);
    this.point = point;
  }

  private void validateEmail(List<String> errors) {
    try {
      email.validate();
    } catch (Exception e) {
      errors.add(e.getMessage());
    }
  }

  private void validatePassword(List<String> errors) {
    try {
      password.validate();
    } catch (Exception e) {
      errors.add(e.getMessage());
    }
  }
}
