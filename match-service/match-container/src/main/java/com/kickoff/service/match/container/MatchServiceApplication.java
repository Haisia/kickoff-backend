package com.kickoff.service.match.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff.service.match"})
@EntityScan(basePackages = {"com.kickoff.service.match"})
@SpringBootApplication(scanBasePackages = "com.kickoff.service.match")
public class MatchServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(MatchServiceApplication.class, args);
  }

}
