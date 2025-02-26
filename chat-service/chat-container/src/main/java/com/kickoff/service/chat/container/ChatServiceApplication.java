package com.kickoff.service.chat.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff.service.chat.dataaccess"})
@EntityScan(basePackages = {"com.kickoff.service.chat.domain.entity"})
@SpringBootApplication(scanBasePackages = "com.kickoff")
public class ChatServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChatServiceApplication.class, args);
  }
}
