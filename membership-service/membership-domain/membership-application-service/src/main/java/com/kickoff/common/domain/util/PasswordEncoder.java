package com.kickoff.common.domain.util;

public interface PasswordEncoder {

  String encode(String rawPassword);

  boolean matches(String rawPassword, String encodedPassword);
}
