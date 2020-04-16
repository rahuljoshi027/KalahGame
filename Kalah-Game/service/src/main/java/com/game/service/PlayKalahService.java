package com.game.service;

import com.game.domain.entities.KalahGameDO;

/**
 * This interface provides a skeleton for services needed 
 * to play kalah game 
 * 
 * @see com.game.service.impl.PlayKalahServiceImpl
 * 
 * @author Rahul
 * 
 * */
public interface PlayKalahService {

	/**
	 * Creates and adds kalah game instance to database
	 * 
	 * @return {@link KalahGameDO}
	 * */
	public KalahGameDO createAndAddKalahInstance();
	
	/**
	 * executes a kalah move and persits the state to database
	 * 
	 * @param gameId
	 * @param pit
	 * @return {@link KalahGameDO}
	 * */
	public KalahGameDO executeMove(long gameId,int pit);
}
