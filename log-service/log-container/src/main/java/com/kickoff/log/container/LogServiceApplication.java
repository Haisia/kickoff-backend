package com.kickoff.log.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff.log.dataaccess.repository"})
@EntityScan(basePackages = {"com.kickoff.log.dataaccess.entity"})
@SpringBootApplication(scanBasePackages = "com.kickoff")
public class LogServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(LogServiceApplication.class, args);
  }
}
