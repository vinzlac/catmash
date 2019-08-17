package com.vinzlac.catmash.infrastructure.repository;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, String> {
}
