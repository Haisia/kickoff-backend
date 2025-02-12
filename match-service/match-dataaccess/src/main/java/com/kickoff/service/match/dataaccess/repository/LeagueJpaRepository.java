package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.dataaccess.entity.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeagueJpaRepository extends JpaRepository<LeagueEntity, UUID> {

}
