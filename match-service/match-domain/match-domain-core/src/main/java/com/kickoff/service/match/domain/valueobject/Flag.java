package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Column;
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
public class Flag extends BaseVo {
  @Column(name = "flag_url")
  private String url;
  @Enumerated(EnumType.STRING)
  @Column(name = "flag_url_type")
  private UrlType urlType;

  @Override
  public void validate() {
  }
}