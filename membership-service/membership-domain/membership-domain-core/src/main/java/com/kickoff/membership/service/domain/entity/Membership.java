package com.kickoff.membership.service.domain.entity;

import com.kickoff.membership.service.domain.valueobject.Email;
import com.kickoff.membership.service.domain.valueobject.Password;
import com.kickoff.membership.service.domain.valuobject.MembershipId;
import com.kickoff.membership.service.domain.valuobject.Point;

public class Membership extends AggregateRoot<MembershipId> {

  private Email email;
  private Password password;
  private Point point;

}
