package com.kickoff.membership.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kickoff")
public class MembershipServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(MembershipServiceApplication.class, args);
  }
}