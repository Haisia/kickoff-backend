package com.kickoff.common.domain.valuobject;

import java.util.Objects;

public abstract class BaseVo<T> {
  protected final T value;

  protected BaseVo(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public abstract void validate();

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BaseVo<?> baseVo = (BaseVo<?>) o;
    return Objects.equals(value, baseVo.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
