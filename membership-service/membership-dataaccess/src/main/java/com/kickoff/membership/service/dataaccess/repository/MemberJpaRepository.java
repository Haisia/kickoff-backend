package com.kickoff.membership.service.dataaccess.repository;

import com.kickoff.membership.service.dataaccess.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
}
