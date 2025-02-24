package com.kickoff.common.application.resolver;

import com.kickoff.common.application.annotation.LoginMember;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.common.service.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

  private static final String AUTHORIZATION_HEADER = "Authorization";

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    // @LoginMember 어노테이션이 붙어 있고, 파라미터 타입이 MemberId인 경우만 처리
    return parameter.getParameterAnnotation(LoginMember.class) != null &&
      parameter.getParameterType().equals(MemberId.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    // Authorization 헤더 가져오기
    String authHeader = request.getHeader(AUTHORIZATION_HEADER);
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return null; // or throw an exception
    }

    // Bearer 토큰에서 JWT 추출
    String token = authHeader.substring(7);

    // 토큰에서 MemberId 추출
    UUID memberId = jwtTokenProvider.parseToken(token);
    if (memberId == null) {
      throw new IllegalArgumentException("Invalid JWT token");
    }

    // MemberId 객체로 반환
    return MemberId.of(memberId);
  }
}