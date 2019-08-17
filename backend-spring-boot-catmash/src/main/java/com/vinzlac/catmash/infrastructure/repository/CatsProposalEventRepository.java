package com.vinzlac.catmash.infrastructure.repository;

import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatsProposalEventRepository extends JpaRepository<CatsProposalEvent, String> {
}
