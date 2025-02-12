package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TeamVenue extends BaseEntity<Long> {
  private String name;
  private String address;
  private String city;
  private Long capacity;
  private String surface;
  private String image;

  @Builder
  public TeamVenue(Long id, String name, String address, String city, Long capacity, String surface, String image) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.city = city;
    this.capacity = capacity;
    this.surface = surface;
    this.image = image;
  }
}
