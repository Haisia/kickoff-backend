package com.kickoff.membership.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff.membership.dataaccess.repository"})
@EntityScan(basePackages = {"com.kickoff.membership.dataaccess.entity"})
@SpringBootApplication(scanBasePackages = "com.kickoff")
public class MembershipServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(MembershipServiceApplication.class, args);
  }
}