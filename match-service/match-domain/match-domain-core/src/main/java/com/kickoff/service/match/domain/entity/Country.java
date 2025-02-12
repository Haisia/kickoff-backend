package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Country extends BaseEntity<Long> {
  private String name;
  private String code;
  private Flag flag;

  @Builder
  public Country(Long id, String name, String code, Flag flag) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.flag = flag;
  }
}
