package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Team extends BaseEntity<Long> {
  private String name;
  private String code;
  private String country;
  private Integer founded;
  private Boolean national;
  private Logo logo;
  private TeamVenue venue;

  @Builder
  public Team(Long id, String name, String code, String country, Integer founded, Boolean national, Logo logo, TeamVenue venue) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.country = country;
    this.founded = founded;
    this.national = national;
    this.logo = logo;
    this.venue = venue;
  }
}
