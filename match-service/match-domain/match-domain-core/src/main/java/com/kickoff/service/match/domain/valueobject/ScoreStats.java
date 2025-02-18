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
public class ScoreStats extends BaseVo {

  private Integer played;
  private Integer win;
  private Integer draw;
  private Integer lose;
  private Integer goalsFor;
  private Integer goalsAgainst;

  @Override
  public void validate() {
  }
}
