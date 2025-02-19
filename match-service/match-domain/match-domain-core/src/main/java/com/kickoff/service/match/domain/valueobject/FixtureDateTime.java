package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class FixtureDateTime extends BaseVo {
  @Column(name = "fixture_date_time_zone")
  private ZoneId dateTimeZone;
  @Column(name = "fixture_date")
  private LocalDateTime date;
  @Column(name = "fixture_timestamp")
  private Long timestamp;
  // 전반 시작 타임스탬프
  @Column(name = "fixture_first_period")
  private Long firstPeriod;
  // 후반 시작 다임스탬프
  @Column(name = "fixture_second_period")
  private Long secondPeriod;

  @Override
  public void validate() {

  }
}
