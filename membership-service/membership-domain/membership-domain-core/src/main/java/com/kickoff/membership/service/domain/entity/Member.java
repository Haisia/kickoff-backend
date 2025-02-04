package com.kickoff.membership.service.domain.entity;

import com.kickoff.membership.service.domain.exception.MemberDomainException;
import com.kickoff.membership.service.domain.valueobject.Email;
import com.kickoff.membership.service.domain.valueobject.Password;
import com.kickoff.membership.service.domain.valuobject.MemberId;
import com.kickoff.membership.service.domain.valuobject.Point;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Member extends AggregateRoot<MemberId> {
  private Email email;
  private Password password;
  private Point point;

  public void validateMember() {
    validateEmail();
    validatePassword();
  }

  public void initializeMember() {
    setId(MemberId.of(UUID.randomUUID()));
  }

  private Member(Builder builder) {
    id = builder.id;
    email = builder.email;
    password = builder.password;
    point = builder.point;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private MemberId id;
    private Email email;
    private Password password;
    private Point point = Point.ZERO;

    private Builder() {
    }

    public Builder id(MemberId id) {
      this.id = id;
      return this;
    }

    public Builder email(Email email) {
      this.email = email;
      return this;
    }

    public Builder email(String email) {
      this.email = Email.of(email);
      return this;
    }

    public Builder password(Password password) {
      this.password = password;
      return this;
    }

    public Builder password(String password) {
      this.password = Password.of(password);
      return this;
    }

    public Member build() {
      return new Member(this);
    }
  }

  private void validateEmail() {
    if (email == null) throw new MemberDomainException("이메일은 null 일 수 없습니다.");
  }

  private void validatePassword() {
    if (password == null) throw new MemberDomainException("비밀번호는 null 일 수 없습니다.");
  }

}
