package com.kickoff.membership.container;

import com.kickoff.membership.domain.MemberDomainService;
import com.kickoff.membership.domain.MemberDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public MemberDomainService memberDomainService() {
    return new MemberDomainServiceImpl();
  }
}
