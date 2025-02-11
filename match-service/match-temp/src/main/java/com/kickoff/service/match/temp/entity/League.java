package com.kickoff.service.match.temp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class League {
  @Id
  public Long id;
  public String name;
  public String type;
  public String logo;

  @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
  public List<Season> seasons;

  @ManyToOne
  @JoinColumn(name = "country_name")
  public Country country;
}
