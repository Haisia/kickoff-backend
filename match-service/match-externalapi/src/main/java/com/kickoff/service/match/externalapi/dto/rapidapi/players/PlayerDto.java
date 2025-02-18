package com.kickoff.service.match.externalapi.dto.rapidapi.players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class PlayerDto {
  public Long id;
  public String name;
  public Integer age;
  public Integer number;
  public String position;
  public String photo;
}
