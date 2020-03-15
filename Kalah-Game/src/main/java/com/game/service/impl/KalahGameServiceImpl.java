package com.game.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.game.constants.GameConstants;
import com.game.dataaccessobject.KalahGameRepository;
import com.game.entities.KalahBoardDO;
import com.game.entities.KalahGameDO;
import com.game.exceptions.BadRequestException;
import com.game.service.KalahGameService;

/**
 * This class is implementation of {@link KalahGameService}
 * 
 * */
@Service
public class KalahGameServiceImpl implements KalahGameService {
	Logger logger = LoggerFactory.getLogger(KalahGameServiceImpl.class);
	private KalahGameRepository kalahRepository;
		
	@Autowired
	public KalahGameServiceImpl(KalahGameRepository kalahRepository) {
		this.kalahRepository = kalahRepository;
	}
	
	/**
	 * returns kalah game instance by id
	 * Also uses caching to store kalah game instances
	 * @param gameId
	 * @return {@link KalahGameDO}
	 * */
	@Cacheable("kalahCache")
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public KalahGameDO getKalahById(long gameId) {
		Optional<KalahGameDO> kDO = kalahRepository.findById(gameId);
		if(!kDO.isPresent()) {
			throw new BadRequestException("invalid game id :: "+gameId);
		}
		return kDO.get();
	}

	/**
	 * Persists kalah game instance {@link KalahGameDO} to database
	 * 
	 * @param kBoard {@link KalahBoardDO}
	 * @return {@link KalahGameDO}
	 * */
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public KalahGameDO addKalahGameInstance(KalahGameDO kGDO) {
		logger.debug("Adding Kalah Game instance");
		return
		kalahRepository.save(kGDO);
		
	}
	
	/**
	 * Creates and returns kalah game
	 * 
	 * @return {@link KalahGameDO}
	 * */
	@Override
	public KalahGameDO createKalahInstance(KalahBoardDO kBDO) {
		logger.debug("Creating Kalah Game instance");

		return
		KalahGameDO.builder().lastMovedPit(0)
							 .wasKalah(false)
							 .gameOver(false)
							 .previousPlayer(GameConstants.NOT_STARTED)
							 .currentPlayer(GameConstants.NOT_STARTED)
							 .kalahLocation(GameConstants.NOT_STARTED)
							 .oneMoreMove(false)
							 .kalahBoardDO(kBDO)
							 .build();
	}
	
	
	
	
}
