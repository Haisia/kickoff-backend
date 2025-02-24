package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.match.domain.valueobject.FixtureCommentId;
import com.kickoff.service.match.domain.valueobject.UserComment;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "fixture_comments")
@Entity
public class FixtureComment extends BaseEntity {

  @EmbeddedId
  private FixtureCommentId id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fixture_id")
  private Fixture fixture;

  @Embedded
  private UserComment userComment;

  @ElementCollection
  @CollectionTable(
    name = "fixture_comment_likes",
    joinColumns = @JoinColumn(name = "fixture_comment_id"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"fixture_comment_id", "member_id"})
  )
  private Set<MemberId> likedUsers = new HashSet<>();

  @Builder
  public FixtureComment(FixtureCommentId id, Fixture fixture, String comment, MemberId createdBy) {
    if (id == null) id = FixtureCommentId.generate();
    this.id = id;
    this.fixture = fixture;
    this.userComment = UserComment.of(comment, createdBy);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureComment that = (FixtureComment) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
