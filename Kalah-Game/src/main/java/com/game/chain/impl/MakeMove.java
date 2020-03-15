package com.game.chain.impl;

import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.game.chain.ResponsibilityChain;
import com.game.constants.GameConstants;
import com.game.entities.KalahGameDO;
import com.game.utilities.FunctionUtilityHelper;
import com.game.utilities.PredicateUtilityHelper;


/**
 * This is class comes on second place while applying game rules.
 * It is responsible for sowing the stones in counter clockwise manner 
 * along the pits. It will also update the input param {@link KalahGameDO}
 * if player lands last stone in his kalah or if player captures the stone
 * of opposite player
 * 
 * @author Rahul
 * 
 * */
@Service
public class MakeMove implements ResponsibilityChain {
	Logger logger = LoggerFactory.getLogger(MakeMove.class);
	//Function<Integer, String> getKalahLocation = pit->(pit==7)?GameConstants.SOUTH_PLAYER:GameConstants.NORTH_PLAYER;
	//Predicate<Integer> isKalah = pit->(pit==GameConstants.SOUTHKALAHINDEX||pit==GameConstants.NORTHKALAHINDEX)?true:false;
	//Function<Integer, String> getPitLocation = pit->(pit>=1&&pit<=6)?GameConstants.SOUTH_PLAYER:GameConstants.NORTH_PLAYER;
	//Function<String,Integer> getKalahPitIndex = loc -> loc.equals(GameConstants.SOUTH_PLAYER)?GameConstants.SOUTHKALAHINDEX:GameConstants.NORTHKALAHINDEX;
	
	/**
	 * 
	 * Check and update if player gets another turn if he lands last stone in his own kalah
	 * @param kGDO {@link KalahGameDO} 
	 * @param lastPit
	 * */
	private void checkAndUpdateIfAnotherTurn(KalahGameDO kGDO,int lastPit) {
		logger.debug("checking for another turn for game id "+kGDO.getId());
		if(PredicateUtilityHelper.isKalah.test(lastPit)
				&& FunctionUtilityHelper.getKalahLocation.apply(lastPit).equals(kGDO.getCurrentPlayer())) {
			kGDO.setOneMoreMove(true);
		}else {
			kGDO.setOneMoreMove(false);
		}
	}
	
	/**
	 * 
	 * Check and update if player can capture opposite players stones if he lands
	 * last stone in his own empty pit
	 * @param kGDO {@link KalahGameDO} 
	 * @param lastPit
	 * */
	private void checkAndUpdateIfCapturedStones(KalahGameDO kGDO, int lastPit) {
		logger.debug("checking if stones captured for game id "+kGDO.getId());
		int[] pits = kGDO.getKalahBoardDO().getPits();
		if(!PredicateUtilityHelper.isKalah.test(lastPit) 
		   && (kGDO.getKalahBoardDO().getPits()[lastPit]==1)
		   && (kGDO.getCurrentPlayer().equals(FunctionUtilityHelper.getPitLocation.apply(lastPit)))) {
			pits[FunctionUtilityHelper.getKalahPitIndex.apply(kGDO.getCurrentPlayer())]=pits[GameConstants.TOTALPITS-lastPit]+1;
			pits[lastPit]=0;
		}
		kGDO.getKalahBoardDO().setPits(pits);
	}

	
	/**
	 * This method is mainly responsible to sow stones into pits in a counter
	 * clockwise manner.
	 * Also checks if player can get one more turn or if player can capture stones
	 * of opposite player
	 * 
	 * @param kGDO {@link KalahGameDO} 
	 * @param pit
	 * */
	@Override
	public void applyGameRules(KalahGameDO kGDO,int pit) {
		int pits[] = kGDO.getKalahBoardDO().getPits();
		int stoneCount = pits[pit];
		int i;
		pits[pit]=0;
		logger.info("Making a move");
		logger.debug("Making a move for "+kGDO.getCurrentPlayer()+" with pit id "+pit+" with game id "+kGDO.getId());
		for(i=pit+1;stoneCount>0;i++) {
			if(i==GameConstants.NORTHKALAHINDEX+1) {
				i=1;
			}
			if((kGDO.getCurrentPlayer().equalsIgnoreCase(GameConstants.SOUTH_PLAYER)&&(i!=GameConstants.NORTHKALAHINDEX))
				||((kGDO.getCurrentPlayer().equalsIgnoreCase(GameConstants.NORTH_PLAYER))&&(i!=GameConstants.SOUTHKALAHINDEX))) {
			pits[i]=pits[i]+1;
			stoneCount--;}
			if(stoneCount==0) {
				break;
			}
			
		}
		kGDO.getKalahBoardDO().setPits(pits);
		checkAndUpdateIfAnotherTurn(kGDO,i);
		checkAndUpdateIfCapturedStones(kGDO,i);
		logger.debug("Kalah pits status after move "+kGDO.getKalahBoardDO().getPits().toString()+" for game id "+kGDO.getId());

	}

	/**
	 * This method simply returns its order in the responsibility chain 
	 * {@link Ordered}
	 * 
	 * @return order
	 * */
	@Override
	public int getOrder() {
		return 1;
	}

}
