package com.kickoff.service.match.temp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@IdClass(SeasonId.class)
@Entity
public class Season {

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "league_id")
  @Id
  public League league;

  @Id
  public String year;
  @Column(name = "start_date")
  public LocalDate start;
  @Column(name = "end_date")
  public LocalDate end;
}
