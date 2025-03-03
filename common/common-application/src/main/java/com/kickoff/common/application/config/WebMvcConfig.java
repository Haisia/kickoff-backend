package com.kickoff.common.application.config;

import com.kickoff.common.application.resolver.LoginMemberArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  private final LoginMemberArgumentResolver loginMemberArgumentResolver;

  public WebMvcConfig(LoginMemberArgumentResolver loginMemberArgumentResolver) {
    this.loginMemberArgumentResolver = loginMemberArgumentResolver;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginMemberArgumentResolver);
  }
}
