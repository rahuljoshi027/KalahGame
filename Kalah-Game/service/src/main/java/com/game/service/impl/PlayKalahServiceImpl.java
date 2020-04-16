package com.game.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.game.domain.entities.KalahGameDO;
import com.game.service.PlayKalahService;
import com.game.service.chain.ResponsibilityChain;
import com.game.utilities.exceptions.BadRequestException;



/**
 * This is an implementation of {@link PlayKalahService} 
 * 
 * @author Rahul
 * 
 * */
@Service
public class PlayKalahServiceImpl implements PlayKalahService{
	Logger logger = LoggerFactory.getLogger(PlayKalahServiceImpl.class);
	private KalahGameServiceImpl kalahGameServiceImpl;
	private KalahBoardServiceImpl kalahBoardServiceImpl;
	private List<ResponsibilityChain> responsibilityList; 
	
	@Autowired
	PlayKalahServiceImpl(KalahGameServiceImpl kalahGameServiceImpl,
						 KalahBoardServiceImpl kalahBoardServiceImpl,
						 List<ResponsibilityChain> responsibilityList){
		this.kalahGameServiceImpl = kalahGameServiceImpl;
		this.kalahBoardServiceImpl = kalahBoardServiceImpl;
		this.responsibilityList = responsibilityList;
	}
	
	/**
	 * Creates and adds kalah game instance to database
	 * 
	 * @return {@link KalahGameDO}
	 * */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public KalahGameDO createAndAddKalahInstance() {
		logger.debug("Creating and Adding Kalah instance");
		return
		kalahGameServiceImpl.addKalahGameInstance(
		kalahGameServiceImpl.createKalahInstance(
		kalahBoardServiceImpl.addKalahBoardDO(		
		kalahBoardServiceImpl.createKalahBoard())));
	}
	
	/**
	 * executes a kalah move and persits the state to database
	 * 
	 * @param gameId
	 * @param pit
	 * @return {@link KalahGameDO}
	 * */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public KalahGameDO executeMove(long gameId,int pit) {
		logger.debug("Executing Kalah move");
		KalahGameDO kGDO = kalahGameServiceImpl.getKalahById(gameId);
		if(kGDO == null) {
			logger.error("Game id does not exist "+gameId);
			throw new BadRequestException("invalid game id :: "+gameId);
		}
		responsibilityList.forEach(x->x.applyGameRules(kGDO,pit));
		return kGDO;
	}
	
}
