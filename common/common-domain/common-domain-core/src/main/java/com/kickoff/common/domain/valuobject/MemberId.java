package com.kickoff.common.domain.valuobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class MemberId extends BaseId<UUID> {
  @Column(name = "member_id")
  private UUID id;

  protected MemberId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static MemberId of(UUID value) {
    return new MemberId(value);
  }

  public static MemberId generate() {
    return of(UUID.randomUUID());
  }
}
