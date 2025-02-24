package com.kickoff.service.match.domain.valueobject;


import com.kickoff.common.domain.valuobject.BaseVo;
import com.kickoff.common.domain.valuobject.MemberId;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class UserComment extends BaseVo {

  private String comment;
  private MemberId createdBy;

  public static UserComment of(String comment, MemberId createdBy) {
    return new UserComment(comment, createdBy);
  }

  @Override
  public void validate() {

  }
}
