package com.game.service;

import com.game.entities.KalahBoardDO;

/**
 * This interface provides a skeleton for services needed 
 * by kalah board like creation and adding to database
 * 
 * @see com.game.service.impl.KalahBoardServiceImpl
 * 
 * @author Rahul
 * 
 * */
public interface KalahBoardService {

	/**
	 * Creates and returns kalah board
	 * 
	 * @return {@link KalahBoardDO}
	 * */
	public KalahBoardDO createKalahBoard();
	
	/**
	 * Persists kalah board instance {@link KalahBoardDO} to database
	 * 
	 * @param kBoard {@link KalahBoardDO}
	 * @return {@link KalahBoardDO}
	 * */
	public KalahBoardDO addKalahBoardDO(KalahBoardDO kBoardDO);
}
