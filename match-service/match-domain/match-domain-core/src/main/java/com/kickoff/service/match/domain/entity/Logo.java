package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.UrlType;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Logo extends BaseEntity<Long> {
  private String url;
  private UrlType urlType;

  @Builder
  public Logo(Long id, String url, UrlType urlType) {
    this.id = id;
    this.url = url;
    this.urlType = urlType;
  }
}
