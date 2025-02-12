package com.kickoff.service.match.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import com.kickoff.common.domain.valuobject.LeagueType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Table(name = "leagues")
@Entity
public class LeagueEntity extends BaseJpaEntity {
  @Id
  private UUID id;

  private Long apiFootballLeagueId;
  private String name;

  @Enumerated(EnumType.STRING)
  private LeagueType leagueType;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "logo_id")
  private LogoEntity logo;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "country_id")
  private CountryEntity country;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "league")
  private List<SeasonEntity> seasons;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LeagueEntity that = (LeagueEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
