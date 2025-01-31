package com.kickoff.membership.service.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Member {
  @Id
  private UUID id;
}
