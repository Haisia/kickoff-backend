package com.kickoff.service.match.temp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {
  @Id
  public String name;
  public String code;
  public String flag;
}
