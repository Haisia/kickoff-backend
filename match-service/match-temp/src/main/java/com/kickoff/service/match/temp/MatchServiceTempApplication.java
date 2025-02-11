package com.kickoff.service.match.temp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff"})
@EntityScan(basePackages = {"com.kickoff"})
@SpringBootApplication(scanBasePackages = "com.kickoff")
public class MatchServiceTempApplication {
  public static void main(String[] args) {
    SpringApplication.run(MatchServiceTempApplication.class, args);
  }
}