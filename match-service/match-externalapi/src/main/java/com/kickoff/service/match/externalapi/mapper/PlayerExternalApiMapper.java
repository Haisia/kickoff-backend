package com.kickoff.service.match.externalapi.mapper;

import com.kickoff.service.match.domain.entity.Player;
import com.kickoff.service.match.domain.valueobject.Photo;
import com.kickoff.service.match.domain.valueobject.Position;
import com.kickoff.service.match.domain.valueobject.UrlType;
import com.kickoff.service.match.externalapi.dto.rapidapi.players.PlayerDto;
import com.kickoff.service.match.externalapi.dto.rapidapi.players.PlayersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PlayerExternalApiMapper {

  public List<Player> playerResponseToPlayer(PlayersResponse playerResponse) {
    return playerDtosToPlayers(playerResponse.getPlayers());
  }

  public Player playerDtoToPlayer(PlayerDto playerDto) {
    Player player = Player.builder()
      .apiFootballPlayerId(playerDto.getId())
      .name(playerDto.getName())
      .age(playerDto.getAge())
      .number(playerDto.getNumber())
      .position(Position.parseIgnoreCase(playerDto.getPosition()))
      .build();
    if (playerDto.getPhoto() != null) player.addPhoto(new Photo(playerDto.getPhoto(), UrlType.EXTERNAL));

    return player;
  }

  private List<Player> playerDtosToPlayers(List<PlayerDto> playerDtos) {
    return playerDtos.stream()
      .map(this::playerDtoToPlayer)
      .toList();
  }
}
