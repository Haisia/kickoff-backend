package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.service.match.domain.valueobject.VenueId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "venues")
@Entity
public class Venue extends BaseEntity {
  @EmbeddedId
  private VenueId id;
  private Long apiFootballVenueId;
  private String name;
  private String address;
  private String city;
  private Long capacity;
  private String surface;
  private String image;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  @Builder
  public Venue(VenueId id, Long apiFootballVenueId, String name, String address, String city, Long capacity, String surface, String image, Team team) {
    if (id == null) id = VenueId.generate();
    this.id = id;
    this.apiFootballVenueId = apiFootballVenueId;
    this.name = name;
    this.address = address;
    this.city = city;
    this.capacity = capacity;
    this.surface = surface;
    this.image = image;
    this.team = team;
  }
}
