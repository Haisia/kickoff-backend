package com.kickoff.membership.service;

import com.kickoff.common.domain.MemberDomainService;
import com.kickoff.common.domain.MemberDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public MemberDomainService memberDomainService() {
    return new MemberDomainServiceImpl();
  }
}
