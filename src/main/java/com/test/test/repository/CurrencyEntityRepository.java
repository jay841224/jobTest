package com.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.test.entity.CurrencyEntity;

/**
 * table TB_TRANSLATE repository
 * @author jay
 *
 */
@Repository
public interface CurrencyEntityRepository extends JpaRepository<CurrencyEntity, String> {

}
