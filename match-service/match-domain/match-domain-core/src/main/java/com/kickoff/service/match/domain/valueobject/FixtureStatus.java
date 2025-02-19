package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class FixtureStatus extends BaseVo {
  private FixtureStatusType fixtureStatusType;
  // 현재 경기 진행도(분). 종료시 90
  private Integer elapsed;
  // 연장 정보
  private String extra;

  @Override
  public void validate() {
  }
}
