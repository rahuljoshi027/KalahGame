package com.game.service.chain.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.game.domain.entities.KalahGameDO;
import com.game.service.chain.ResponsibilityChain;
import com.game.utilities.constants.GameConstants;

/**
 * 
 * This is class comes on second place while applying game rules. 
 * It is mainly responsible for updating the changes so far done
 * in previous flow. Also checks if the game is over and updates it.
 * 
 * @author Rahul
 * 
 * */
@Service
public class UpdateMove implements ResponsibilityChain {
	Logger logger = LoggerFactory.getLogger(UpdateMove.class);
	
	/**
	 * Checks if any of the player has run out of stones.
	 * If yes than the game is over.
	 * 
	 * @param kGDO {@link KalahGameDO}
	 * 
	 * */
	private void checkAndUpdateIfGameIsOver(KalahGameDO kGDO) {
		int[] southPits = Arrays.copyOfRange(kGDO.getKalahBoardDO().getPits(), 1, GameConstants.SOUTHKALAHINDEX);
		int[] northPits = Arrays.copyOfRange(kGDO.getKalahBoardDO().getPits(), GameConstants.SOUTHKALAHINDEX+1, GameConstants.NORTHKALAHINDEX);
		
		if(Arrays.stream(southPits).sum()==0 
		||Arrays.stream(northPits).sum()==0) {
			kGDO.setGameOver(true);
		}
	}
	
	
	/**
	 * This method is responsible for updating input param kGDO {@link KalahGameDO}
	 * 
	 * @param kGDO {@link KalahGameDO}
	 * @param pit
	 * */
	@CachePut("kalahCache")
	@Override
	public void applyGameRules(KalahGameDO kGDO,int pit) {
		
		kGDO.setPreviousPlayer(kGDO.getCurrentPlayer());
		checkAndUpdateIfGameIsOver(kGDO);
		logger.debug("Updating move for game "+kGDO.getId()+" with current player "+kGDO.getCurrentPlayer());
	}

	/**
	 * This method simply returns its order in the responsibility chain 
	 * {@link Ordered}
	 * 
	 * @return order
	 * */
	@Override
	public int getOrder() {
		return 2;
	}

}
