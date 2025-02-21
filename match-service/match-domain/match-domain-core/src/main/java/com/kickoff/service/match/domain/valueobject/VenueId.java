package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class VenueId extends BaseId<UUID> {
  @Column(name = "venue_id")
  private UUID id;

  protected VenueId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static VenueId of(UUID value) {
    return new VenueId(value);
  }

  public static VenueId generate() {
    return new VenueId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    VenueId venueId = (VenueId) o;
    return Objects.equals(id, venueId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
