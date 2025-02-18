package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.entity.Player;

import java.util.List;

public interface PlayerRepository {
  List<Player> findAll();
}
