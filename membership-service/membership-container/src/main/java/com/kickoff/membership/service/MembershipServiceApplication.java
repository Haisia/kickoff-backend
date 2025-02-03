package com.kickoff.membership.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.kickoff.membership.service.dataaccess"})
@EntityScan(basePackages = {"com.kickoff.membership.service.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.kickoff")
public class MembershipServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MembershipServiceApplication.class, args);
  }
}