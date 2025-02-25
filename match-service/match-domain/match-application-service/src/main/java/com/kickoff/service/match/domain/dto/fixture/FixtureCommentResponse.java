package com.kickoff.service.match.domain.dto.fixture;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.match.domain.entity.FixtureComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class FixtureCommentResponse {

  UUID fixtureCommentId;
  String comment;
  UUID createdBy;
  LocalDateTime createdAt;
  String createdByEmail;
  Set<UUID> likedUsers = new HashSet<>();

  public static FixtureCommentResponse from(FixtureComment fixtureComment) {
    Set<UUID> mappedLikedUsers = fixtureComment.getLikedUsers()
      .stream()
      .map(MemberId::getId)
      .collect(Collectors.toSet());

    return FixtureCommentResponse.builder()
      .fixtureCommentId(fixtureComment.getId().getId())
      .comment(fixtureComment.getUserComment().getComment())
      .createdBy(fixtureComment.getUserComment().getCreatedBy().getId())
      .createdAt(fixtureComment.getCreatedAt())
      .likedUsers(mappedLikedUsers)
      .build();
  }
}
