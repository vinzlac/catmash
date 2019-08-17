package com.vinzlac.catmash.infrastructure.repository;

import com.vinzlac.catmash.infrastructure.entity.VoteEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteEventRepository extends JpaRepository<VoteEvent, Long> {
}
