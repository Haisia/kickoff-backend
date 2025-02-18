package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.dataaccess.repository.PlayerJpaRepository;
import com.kickoff.service.match.domain.entity.Player;
import com.kickoff.service.match.domain.port.output.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

  private final PlayerJpaRepository playerJpaRepository;

  @Override
  public List<Player> findAll() {
    return playerJpaRepository.findAll();
  }
}
