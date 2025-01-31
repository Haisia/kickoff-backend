package com.kickoff.membership.service.domain.valuobject;

public abstract class BaseId<T> extends BaseVo<T> {
  protected BaseId(T value) {
    super(value);
  }
}
