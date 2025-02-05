package com.kickoff.membership.dataaccess.repository;

import com.kickoff.membership.dataaccess.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
  Optional<MemberEntity> findByEmail(String email);
}
