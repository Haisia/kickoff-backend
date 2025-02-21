package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FixtureCommentId extends BaseId<UUID> {
  @Column(name = "fixture_comment_id")
  private UUID id;

  protected FixtureCommentId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static FixtureCommentId of(UUID value) {
    return new FixtureCommentId(value);
  }

  public static FixtureCommentId generate() {
    return new FixtureCommentId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureCommentId that = (FixtureCommentId) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
