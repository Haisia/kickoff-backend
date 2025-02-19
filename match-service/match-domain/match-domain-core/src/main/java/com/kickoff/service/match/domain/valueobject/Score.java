package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Score extends BaseVo {
  @Column(name = "home_score")
  private Integer home;
  @Column(name = "away_score")
  private Integer away;

  @Override
  public void validate() {

  }
}
