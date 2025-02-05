package com.kickoff.common.enums;

public enum CustomHttpStatus {

  // 1xx: Informational
  CONTINUE(100, "계속"),
  SWITCHING_PROTOCOLS(101, "프로토콜 전환"),
  PROCESSING(102, "처리 중"),

  // 2xx: Success
  OK(200, "성공"),
  CREATED(201, "생성됨"),
  ACCEPTED(202, "허용됨"),
  NON_AUTHORITATIVE_INFORMATION(203, "권한 없는 정보"),
  NO_CONTENT(204, "콘텐츠 없음"),
  RESET_CONTENT(205, "콘텐츠 재설정"),
  PARTIAL_CONTENT(206, "부분 콘텐츠"),
  MULTI_STATUS(207, "멀티 상태"),
  ALREADY_REPORTED(208, "이미 보고됨"),
  IM_USED(226, "IM 사용됨"),

  // 3xx: Redirection
  MULTIPLE_CHOICES(300, "다중 선택"),
  MOVED_PERMANENTLY(301, "영구 이동"),
  FOUND(302, "이동됨"),
  SEE_OTHER(303, "다른 곳을 참조하시오"),
  NOT_MODIFIED(304, "수정되지 않음"),
  TEMPORARY_REDIRECT(307, "임시 이동"),
  PERMANENT_REDIRECT(308, "영구 이동"),

  // 4xx: Client Error
  BAD_REQUEST(400, "잘못된 요청"),
  UNAUTHORIZED(401, "인증되지 않음"),
  PAYMENT_REQUIRED(402, "결제가 필요함"),
  FORBIDDEN(403, "금지됨"),
  NOT_FOUND(404, "찾을 수 없음"),
  METHOD_NOT_ALLOWED(405, "허용되지 않은 메서드"),
  NOT_ACCEPTABLE(406, "허용되지 않음"),
  PROXY_AUTHENTICATION_REQUIRED(407, "프록시 인증 필요"),
  REQUEST_TIMEOUT(408, "요청 시간 초과"),
  CONFLICT(409, "충돌"),
  GONE(410, "사라짐"),
  LENGTH_REQUIRED(411, "길이 필요"),
  PRECONDITION_FAILED(412, "전제 조건 실패"),
  PAYLOAD_TOO_LARGE(413, "페이로드가 너무 큼"),
  URI_TOO_LONG(414, "URI가 너무 긺"),
  UNSUPPORTED_MEDIA_TYPE(415, "지원하지 않는 미디어 타입"),
  RANGE_NOT_SATISFIABLE(416, "처리할 수 없는 범위"),
  EXPECTATION_FAILED(417, "기대 실패"),
  UNPROCESSABLE_ENTITY(422, "처리할 수 없는 엔티티"),
  LOCKED(423, "잠김"),
  FAILED_DEPENDENCY(424, "종속성 실패"),
  TOO_EARLY(425, "너무 이른 요청"),
  UPGRADE_REQUIRED(426, "업그레이드 필요"),
  PRECONDITION_REQUIRED(428, "전제 조건 필요"),
  TOO_MANY_REQUESTS(429, "너무 많은 요청"),
  REQUEST_HEADER_FIELDS_TOO_LARGE(431, "요청 헤더 필드가 너무 큼"),
  UNAVAILABLE_FOR_LEGAL_REASONS(451, "법적 사유로 사용 불가"),

  // 5xx: Server Error
  INTERNAL_SERVER_ERROR(500, "서버 내부 오류"),
  NOT_IMPLEMENTED(501, "구현되지 않음"),
  BAD_GATEWAY(502, "잘못된 게이트웨이"),
  SERVICE_UNAVAILABLE(503, "서비스를 사용할 수 없음"),
  GATEWAY_TIMEOUT(504, "게이트웨이 시간 초과"),
  HTTP_VERSION_NOT_SUPPORTED(505, "HTTP 버전이 지원되지 않음"),
  VARIANT_ALSO_NEGOTIATES(506, "변형도 협상 대상"),
  INSUFFICIENT_STORAGE(507, "저장소 부족"),
  LOOP_DETECTED(508, "루프 감지됨"),
  NOT_EXTENDED(510, "확장되지 않음"),
  NETWORK_AUTHENTICATION_REQUIRED(511, "네트워크 인증 필요");

  private final int code;
  private final String reasonPhrase;

  CustomHttpStatus(int code, String reasonPhrase) {
    this.code = code;
    this.reasonPhrase = reasonPhrase;
  }

  public int getCode() {
    return code;
  }

  public String getReasonPhrase() {
    return reasonPhrase;
  }

  @Override
  public String toString() {
    return code + " " + reasonPhrase;
  }
}