package com.kickoff.common.constant;

import java.util.List;

public class Constant {

  public static final String DIRECT_EXCHANGE = "direct_exchange";

  public static final List<Long> AVAILABLE_LEAGUE_IDS = List.of(
//    2L  // UEFA
//    , 39L  // EPL
//    , 140L  // LaLiga
//    , 135L  // Serie A
//    , 78L // Bundesliga
//    , 61L // France Ligue 1
    292L // K League 1
    );

  // 정규식: 올바른 이메일 형식을 검증 (예: user@domain.com)
  public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
  public static final String EMAIL_FAULT_MESSAGE = "잘못된 이메일 형식입니다.";

  // 정규식: 비밀번호가 최소 6자리 이상인지 검증
  public static final String PASSWORD_REGEX = "^.{6,}$";
  public static final String PASSWORD_FAULT_MESSAGE = "비밀번호는 6자리 이상이어야 합니다.";
  public static final String PASSWORD_ALL_MASKED = "****";

  private Constant() {
  }
}