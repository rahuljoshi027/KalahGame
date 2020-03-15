package com.game.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entities.KalahBoardDO;

/**
 * This is kalah board repository which enables database interaction
 * using JPA provided implementation {@link JpaRepository}
 * 
*/
public interface KalahBoardRepository extends JpaRepository<KalahBoardDO, Long> {

}
