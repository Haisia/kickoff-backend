package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerJpaRepository extends JpaRepository<Player, UUID> {
}
