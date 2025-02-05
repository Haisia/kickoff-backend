package com.kickoff.common.service.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseResponse {
  public String message;

  protected BaseResponse(String message) {
    this.message = message;
  }
}
