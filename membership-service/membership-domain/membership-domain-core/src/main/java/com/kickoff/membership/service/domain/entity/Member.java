package com.kickoff.membership.service.domain.entity;

import com.kickoff.membership.service.domain.valueobject.Email;
import com.kickoff.membership.service.domain.valueobject.Password;
import com.kickoff.membership.service.domain.valuobject.MemberId;
import com.kickoff.membership.service.domain.valuobject.Point;

public class Member extends AggregateRoot<MemberId> {
  private Email email;
  private Password password;
  private Point point;

  private Member(Builder builder) {
    email = builder.email;
    password = builder.password;
    point = builder.point;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Email email;
    private Password password;
    private Point point = Point.ZERO;

    private Builder() {
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
}
