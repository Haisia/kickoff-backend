package com.kickoff.service.chat.dataaccess.adapter;

import com.kickoff.service.chat.dataaccess.repository.GeneralLiveChatJpaRepository;
import com.kickoff.service.chat.domain.entity.GeneralLiveChat;
import com.kickoff.service.chat.domain.port.output.repository.GeneralLiveChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GeneralLiveChatRepositoryImpl implements GeneralLiveChatRepository {

  private final GeneralLiveChatJpaRepository generalLiveChatJpaRepository;

  public GeneralLiveChat save(GeneralLiveChat generalLiveChat) {
    return generalLiveChatJpaRepository.save(generalLiveChat);
  }
}
