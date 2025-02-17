package com.kickoff.membership.domain.entity;

import com.kickoff.common.domain.valuobject.BaseVo;
import com.kickoff.common.domain.valuobject.MemberId;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class AttendanceRecord extends BaseVo {

  public static BigDecimal attendancePoint = BigDecimal.valueOf(100);

  private LocalDate attendanceDate;

  public static AttendanceRecord generateToday() {
    return new AttendanceRecord(LocalDate.now());
  }

  @Override
  public void validate() {

  }
}
