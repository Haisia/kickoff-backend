package com.kickoff.common.domain.valuobject;

import com.kickoff.common.domain.exception.VoException;

import java.math.BigDecimal;

public class Point extends BaseVo<BigDecimal>{
  protected Point(BigDecimal point) {
    super(point);
  }

  public static Point of(BigDecimal point) {
    Point createdPoint = new Point(point);
    createdPoint.validate();
    return createdPoint;
  }

  @Override
  public void validate() {
    if(value.compareTo(BigDecimal.ZERO) < 0) throw new VoException("point 는 0보다 작을 수 없습니다.");
  }

  public static final Point ZERO = new Point(BigDecimal.ZERO);

  public Point add(Point Point) {
    return new Point(this.value.add(Point.getValue()));
  }

  public Point subtract(Point Point) {
    return new Point(this.value.subtract(Point.getValue()));
  }

  public Point multiply(int multiplier) {
    return new Point(this.value.multiply(new BigDecimal(multiplier)));
  }
}
