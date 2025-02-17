package com.kickoff.common.domain.valuobject;

import com.kickoff.common.domain.exception.VoException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Point extends BaseVo {

  @Column(nullable = false)
  private BigDecimal point;

  protected Point(BigDecimal point) {
    this.point = point == null ? BigDecimal.ZERO : point;
  }

  public static Point of(BigDecimal point) {
    Point createdPoint = new Point(point);
    createdPoint.validate();
    return createdPoint;
  }

  @Override
  public void validate() {
    if (point.compareTo(BigDecimal.ZERO) < 0) throw new VoException("point 는 0보다 작을 수 없습니다.");
  }

  public static final Point ZERO = new Point(BigDecimal.ZERO);

  public Point add(Point Point) {
    return new Point(this.point.add(Point.getPoint()));
  }

  public Point subtract(Point Point) {
    return new Point(this.point.subtract(Point.getPoint()));
  }

  public Point multiply(int multiplier) {
    return new Point(this.point.multiply(new BigDecimal(multiplier)));
  }
}
