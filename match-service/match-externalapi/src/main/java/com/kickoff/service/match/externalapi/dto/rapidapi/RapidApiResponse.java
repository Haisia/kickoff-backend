package com.kickoff.service.match.externalapi.dto.rapidapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class RapidApiResponse<T> {
  public String get;
//  public Map<String, String> errors;
  public Long results;
  public Paging paging;
  public List<T> response;

  @Data
  @NoArgsConstructor @AllArgsConstructor
  public static class Paging {
    public Long current;
    public Long total;
  }
}
