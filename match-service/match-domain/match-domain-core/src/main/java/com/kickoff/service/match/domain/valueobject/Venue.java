package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Venue extends BaseVo {
  private Long apiFootballVenueId;
  private String name;
  private String address;
  private String city;
  private Long capacity;
  private String surface;
  private String image;

  @Override
  public void validate() {
  }
}
