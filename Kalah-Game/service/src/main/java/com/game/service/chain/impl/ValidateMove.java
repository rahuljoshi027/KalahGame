package com.game.service.chain.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.game.domain.entities.KalahGameDO;
import com.game.service.chain.ResponsibilityChain;
import com.game.utilities.exceptions.BadRequestException;
import com.game.utilities.lambdas.FunctionUtilityHelper;
import com.game.utilities.lambdas.PredicateUtilityHelper;

/**
 * This is the first class while applying game rules.
 * It is responsible to validate all the values which
 * have logical impact on game rules. Like current and  
 * previous player cannot be same unless the player has
 * scored an extra move.
 * 
 * @author Rahul
 * 
 * */
@Service
public class ValidateMove implements ResponsibilityChain {
	Logger logger = LoggerFactory.getLogger(ValidateMove.class);
	
	/**
	 * This method implements various checks in order to keep
	 * logical integrity of game stable.
	 * 
	 * @param kGDO {@link KalahGameDO}
	 * @param pit
	 * */
	@Override
	public void applyGameRules(KalahGameDO kGDO, int pit) {

		String currentPlayer = FunctionUtilityHelper.calculateCurrentPlayer.apply(pit); 
		kGDO.setCurrentPlayer(currentPlayer);
		logger.debug("validating move for game "+kGDO.getId());
		if(kGDO.isGameOver()) {
			//throw exception game over
			logger.error("Game over "+kGDO.getId());
			throw new BadRequestException("Game Over");
		}
		
		if(kGDO.isOneMoreMove()&& !currentPlayer.equals(kGDO.getPreviousPlayer())) {
			//throw exception wrong move
			logger.error("Wrong move. Player selection incorrect "+kGDO.getId());
			throw new BadRequestException("Wrong move. Player selection incorrect");
		}
		
		if(!kGDO.isOneMoreMove()&& currentPlayer.equals(kGDO.getPreviousPlayer())) {
			//throw exception wrong move
			logger.error("Wrong move. Player selection incorrect "+kGDO.getId());
			throw new BadRequestException("Wrong move. Player selection incorrect");
		}
		
		if(PredicateUtilityHelper.isThisKalah.test(pit)) {
			//throw exception pit cannot be kalah
			logger.error("You have selected kalah. Please select valid pit "+kGDO.getId());
			throw new BadRequestException("You have selected kalah. Please select valid pit");
		}
		if(kGDO.getKalahBoardDO().getPits()[pit]==0) {
			//throw exception stones exausted
			logger.error("Stones exausted . Please select different pit "+kGDO.getId());
			throw new BadRequestException("Stones exausted in pit no :: "+pit+". Please select different pit");
		}
		
	}
	
	/**
	 * This method simply returns its order in the responsibility chain 
	 * {@link Ordered}
	 * 
	 * @return order
	 * */
	@Override
	public int getOrder() {
		return 0;
	}

}
