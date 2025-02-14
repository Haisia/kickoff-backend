package com.kickoff.service.match.dataaccess.adapter;

import com.kickoff.service.match.domain.port.output.repository.SeasonRepository;
import com.kickoff.service.match.domain.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SeasonRepositoryImpl implements SeasonRepository {

  @Override
  public Season save(Season season) {
    return null;

  }

  @Override
  public List<Season> saveAll(Collection<Season> seasons) {
    return seasons.stream()
      .map(this::save)
      .toList();
  }
}
