package com.kickoff.membership.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.common.domain.valuobject.MemberId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class AttendanceRecord extends BaseEntity<Long> {

  public static BigDecimal attendancePoint = BigDecimal.valueOf(100);

  private MemberId memberId;
  private LocalDate attendanceDate;

  public AttendanceRecord(MemberId memberId, LocalDate attendanceDate) {
    this.memberId = memberId;
    this.attendanceDate = attendanceDate;
  }

  @Builder
  public AttendanceRecord(Long id, MemberId memberId, LocalDate attendanceDate) {
    this.id = id;
    this.memberId = memberId;
    this.attendanceDate = attendanceDate;
  }
}
