package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Country extends BaseVo {
  @Column(name = "country_name")
  private String name;
  @Column(name = "country_code")
  private String code;
  @Embedded
  private Flag flag;

  @Override
  public void validate() {
  }
}
