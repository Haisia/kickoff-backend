package com.kickoff.membership.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.membership.domain.exception.MemberDomainException;
import com.kickoff.membership.domain.valueobject.Email;
import com.kickoff.membership.domain.valueobject.Password;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.common.domain.valuobject.Point;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Member extends AggregateRoot<MemberId> {
  private Email email;
  private Password password;
  private Point point;

  private List<AttendanceRecord> attendanceRecords;

  public void checkAttendance() {
    LocalDate today = LocalDate.now();
    boolean alreadyChecked = attendanceRecords.stream()
      .anyMatch(record -> record.getAttendanceDate().equals(today));
    if (alreadyChecked) {
      throw new MemberDomainException(
        String.format("오늘은 이미 출석 체크를 완료했습니다. : memberId=%s", getId().getValue())
        , CustomHttpStatus.BAD_REQUEST
      );
    }
    attendanceRecords.add(new AttendanceRecord(getId(), today));
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

  private Member(Builder builder) {
    id = builder.id;
    email = builder.email;
    password = builder.password;
    point = builder.point;
    attendanceRecords = builder.attendanceRecords;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private MemberId id;
    private Email email;
    private Password password;
    private Point point = Point.ZERO;
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    private Builder() {
    }

    public Builder id(MemberId id) {
      this.id = id;
      return this;
    }

    public Builder email(Email email) {
      this.email = email;
      return this;
    }

    public Builder email(String email) {
      this.email = Email.of(email);
      return this;
    }

    public Builder password(Password password) {
      this.password = password;
      return this;
    }

    public Builder password(String password, boolean isHashed) {
      this.password = Password.of(password, isHashed);
      return this;
    }

    public Builder point(BigDecimal point) {
      this.point = Point.of(point);
      return this;
    }

    public Builder attendanceRecords(List<AttendanceRecord> attendanceRecords) {
      this.attendanceRecords = attendanceRecords;
      return this;
    }

    public Member build() {
      return new Member(this);
    }
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
