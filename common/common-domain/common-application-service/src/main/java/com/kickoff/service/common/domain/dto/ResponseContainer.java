package com.kickoff.service.common.domain.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseContainer<T> {
  public String requestDescription;
  public Integer results;
  public List<T> response;

  public ResponseContainer(Object requestDescription, List<T> response) {
    this.requestDescription = requestDescription.toString();
    this.results = response.size();
    this.response = response;
  }
}
