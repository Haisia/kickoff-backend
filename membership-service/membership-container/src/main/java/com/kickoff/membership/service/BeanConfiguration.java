package com.kickoff.membership.service;

import com.kickoff.membership.service.domain.MemberDomainService;
import com.kickoff.membership.service.domain.MemberDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public MemberDomainService memberDomainService() {
    return new MemberDomainServiceImpl();
  }
}
