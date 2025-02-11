package com.kickoff.service.match.temp;

import com.kickoff.service.match.temp.entity.*;
import com.kickoff.service.match.temp.repository.CountryRepository;
import com.kickoff.service.match.temp.repository.LeagueRepository;
import com.kickoff.service.match.temp.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@RestController
public class TempController {

  @Autowired private WebClient webClient;
  @Autowired private LeagueRepository leagueRepository;
  @Autowired private SeasonRepository seasonRepository;
  @Autowired private CountryRepository countryRepository;

  @Transactional
  @PostMapping("/temp/persist/league")
  public String fetchLeagues() {
    ApiResponse result = webClient.get()
      .uri("/leagues")
      .retrieve()
      .bodyToMono(ApiResponse.class)
      .block();

    for (Response response : result.response) {
      Optional<Country> findCountry = countryRepository.findByName(response.country.name);
      Country savedCountry = findCountry.orElseGet(() -> countryRepository.save(response.country));

      League league = response.league;
      league.country = savedCountry;
      leagueRepository.save(league);

      List<Season> seasons = response.seasons;
      seasons.forEach(season -> season.league = league);
      seasonRepository.saveAll(seasons);
    }

    return null;
  }
}