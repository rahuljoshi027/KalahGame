package com.game.service;

import com.game.domain.entities.KalahBoardDO;
import com.game.domain.entities.KalahGameDO;

/**
 * This interface provides a skeleton for services needed 
 * by kalah game like creation and adding to database
 * 
 * @see com.game.service.impl.KalahGameServiceImpl
 * 
 * @author Rahul
 * 
 * */
public interface KalahGameService {

	/**
	 * Creates and returns kalah game
	 * 
	 * @return {@link KalahGameDO}
	 * */
	public KalahGameDO addKalahGameInstance(KalahGameDO kGDO);
	

	/**
	 * Persists kalah game instance {@link KalahGameDO} to database
	 * 
	 * @param kBoard {@link KalahBoardDO}
	 * @return {@link KalahGameDO}
	 * */
	public KalahGameDO createKalahInstance(KalahBoardDO kBDO);
}
