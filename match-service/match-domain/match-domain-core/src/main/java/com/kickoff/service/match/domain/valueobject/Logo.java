package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Logo extends BaseVo {
  private String url;
  @Enumerated(EnumType.STRING)
  private UrlType urlType;

  @Override
  public void validate() {
  }
}
