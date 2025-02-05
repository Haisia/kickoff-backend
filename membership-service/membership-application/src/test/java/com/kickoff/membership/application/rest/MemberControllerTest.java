package com.kickoff.membership.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kickoff.membership.container.MembershipServiceApplication;
import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;
import com.kickoff.membership.service.dto.login.LoginMemberRequest;
import com.kickoff.membership.service.dto.login.LoginMemberResponse;
import com.kickoff.membership.service.exception.LoginFailureException;
import com.kickoff.membership.service.port.input.MemberCreateUseCase;
import com.kickoff.membership.service.port.input.MemberLoginUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = MembershipServiceApplication.class)
class MemberControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private MemberCreateUseCase memberCreateUseCase;
  @MockBean private MemberLoginUseCase memberLoginUseCase;

  private CreateMemberRequest validCreateRequest;
  private CreateMemberResponse validCreateResponse;
  private LoginMemberRequest validLoginRequest;
  private LoginMemberResponse validLoginResponse;

  private String email = "test@test.com";
  private String password = "securePassword123";
  private String loginFailMessage = String.format("이메일 또는 패스워드가 다릅니다. : email=%s", email);

  @BeforeEach
  void setUp() {
    validCreateRequest = new CreateMemberRequest(email, password);
    validLoginRequest = new LoginMemberRequest(email, password);

    validCreateResponse = CreateMemberResponse.builder()
      .email(email)
      .responseMessage("회원가입에 성공하였습니다.")
      .build();

    validLoginResponse = LoginMemberResponse.builder()
      .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
      .responseMessage("로그인에 성공하였습니다.")
      .build();
  }

  @Test
  void createMemberAndLogin_회원가입후_로그인_성공시_200_OK와_토큰응답을_반환해야한다() throws Exception {
    // given
    Mockito.when(memberCreateUseCase.createMember(Mockito.any(CreateMemberRequest.class)))
      .thenReturn(validCreateResponse);

    Mockito.when(memberLoginUseCase.loginMember(Mockito.any(LoginMemberRequest.class)))
      .thenReturn(validLoginResponse);

    mockMvc.perform(post("/members/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validCreateRequest)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.email").value(email))
      .andExpect(jsonPath("$.responseMessage").value("회원가입에 성공하였습니다."));

    mockMvc.perform(post("/members/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validLoginRequest)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.token").value(validLoginResponse.getToken()))
      .andExpect(jsonPath("$.responseMessage").value("로그인에 성공하였습니다."));
  }

  @Test
  void loginMember_회원가입되지않은_계정으로_로그인시도시_401_Unauthorized를_반환해야한다() throws Exception {
    Mockito.when(memberLoginUseCase.loginMember(Mockito.any(LoginMemberRequest.class)))
      .thenThrow(new LoginFailureException(email));

    mockMvc.perform(post("/members/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validLoginRequest)))
      .andExpect(status().isUnauthorized());
  }

  @Test
  void loginMember_잘못된비밀번호로_로그인시_401_Unauthorized를_반환해야한다() throws Exception {
    // given
    Mockito.when(memberLoginUseCase.loginMember(Mockito.any(LoginMemberRequest.class)))
      .thenThrow(new LoginFailureException(email));

    // when & then
    mockMvc.perform(post("/members/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validLoginRequest)))
      .andExpect(status().isUnauthorized());
  }
}