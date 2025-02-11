package com.kickoff.service.match.temp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SeasonId implements Serializable {
  public Long league;
  public String year;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonId seasonId = (SeasonId) o;
    return Objects.equals(league, seasonId.league) && Objects.equals(year, seasonId.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(league, year);
  }
}
