package domain.entity;

import com.kickoff.domain.entity.AggregateRoot;
import domain.valueobject.Email;
import domain.valueobject.Password;
import com.kickoff.domain.valuobject.MembershipId;
import com.kickoff.domain.valuobject.Point;

public class Membership extends AggregateRoot<MembershipId> {

  private Email email;
  private Password password;
  private Point point;

}
