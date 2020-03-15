package com.game.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entities.KalahGameDO;

/**
 * This is kalah game repository which enables database interaction
 * using JPA provided implementation {@link JpaRepository}
 * 
*/
public interface KalahGameRepository extends JpaRepository<KalahGameDO, Long> {

}
