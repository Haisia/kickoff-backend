package com.kickoff.constant;

public class Constant {

  // 정규식: 올바른 이메일 형식을 검증 (예: user@domain.com)
  public static String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

  // 정규식: 비밀번호가 최소 6자리 이상인지 검증
  public static String PASSWORD_REGEX = "^.{6,}$";

  private Constant() {
  }
}