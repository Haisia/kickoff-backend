package com.kickoff.service.match.temp.repository;

import com.kickoff.service.match.temp.entity.Season;
import com.kickoff.service.match.temp.entity.SeasonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, SeasonId> {
}
