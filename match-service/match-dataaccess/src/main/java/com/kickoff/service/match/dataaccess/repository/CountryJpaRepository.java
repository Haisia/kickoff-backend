package com.kickoff.service.match.dataaccess.repository;

import com.kickoff.service.match.dataaccess.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryJpaRepository extends JpaRepository<CountryEntity, Long> {
  Optional<CountryEntity> findByName(String name);
}
