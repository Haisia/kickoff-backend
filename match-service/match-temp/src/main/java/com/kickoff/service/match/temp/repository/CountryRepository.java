package com.kickoff.service.match.temp.repository;

import com.kickoff.service.match.temp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
  Optional<Country> findByName(String name);
}
