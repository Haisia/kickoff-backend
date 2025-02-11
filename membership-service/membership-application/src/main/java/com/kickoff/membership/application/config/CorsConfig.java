package com.kickoff.membership.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("http://localhost:3000"); // 허용할 출처 추가
    configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용 (GET, POST 등)
    configuration.addAllowedHeader("*"); // 모든 헤더 허용
    configuration.setAllowCredentials(true); // 인증정보(Cookie 등) 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}
