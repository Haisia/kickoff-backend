package com.kickoff.membership.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kickoff.membership.service.MembershipServiceApplication;
import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;
import com.kickoff.membership.service.port.input.MemberCreateUseCase;
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
  @MockBean private MemberCreateUseCase memberCreateUseCase;
  @Autowired private ObjectMapper objectMapper;

  private CreateMemberRequest validRequest;
  private CreateMemberResponse response;

  @BeforeEach
  void setUp() {
    validRequest = new CreateMemberRequest("test@test.com", "securePassword123");

    response = CreateMemberResponse.builder()
      .email("test@test.com")
      .responseMessage("회원가입에 성공하였습니다.")
      .build();
  }

  @Test
  void createMember_유효한요청으로_회원이_생성되면_200_OK와_응답을_반환해야한다() throws Exception {
    // given
    Mockito.when(memberCreateUseCase.createMember(Mockito.any(CreateMemberRequest.class)))
      .thenReturn(response);

    // when & then
    mockMvc.perform(post("/members/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validRequest)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.email").value("test@test.com"))
      .andExpect(jsonPath("$.responseMessage").value("회원가입에 성공하였습니다."));

    Mockito.verify(memberCreateUseCase).createMember(Mockito.any(CreateMemberRequest.class));
  }

  @Test
  void createMember_잘못된요청으로_유효성검증에_실패하면_400_BadRequest를_반환해야한다() throws Exception {
    // given
    CreateMemberRequest invalidRequest = new CreateMemberRequest("", "");

    // when & then
    mockMvc.perform(post("/members/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(invalidRequest)))
      .andExpect(status().isBadRequest());
  }
}