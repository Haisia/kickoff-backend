package com.kickoff.service.match.domain.dto.headtohead;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetHeadToHeadSimpleQuery {
  @Size(min = 2, max = 2, message = "teamIds는 반드시 2개의 팀 ID를 포함해야 합니다.")
  List<UUID> teamIds;
}
