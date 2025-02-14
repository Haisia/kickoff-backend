package com.kickoff.service.match.domain.port.output.repository;

import com.kickoff.service.match.domain.entity.Season;

import java.util.Collection;
import java.util.List;

public interface SeasonRepository {
  List<Season> saveAll(Collection<Season> seasons);
  Season save(Season season);
}
