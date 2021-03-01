package com.modoodesigner.domain.model.part.chain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChainRepository extends JpaRepository<Chain,Long> {
    Optional<Chain> findByName(String name);
    boolean existsByCode(String code);
}
