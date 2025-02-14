package com.kickoff.common.domain.valuobject;

import java.io.Serializable;

public abstract class BaseVo implements Serializable {

  // 리플렉션용. 일반목적으로 사용금지
  protected BaseVo() {
  }

  public abstract void validate();

}
