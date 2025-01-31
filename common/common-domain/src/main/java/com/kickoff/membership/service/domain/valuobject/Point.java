package com.kickoff.membership.service.domain.valuobject;

import java.math.BigDecimal;

public class Point extends BaseVo<BigDecimal>{
  protected Point(BigDecimal point) {
    super(point);
  }

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
