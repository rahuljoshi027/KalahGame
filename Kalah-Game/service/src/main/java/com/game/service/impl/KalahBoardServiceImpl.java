package com.game.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.domain.dataaccessobject.KalahBoardRepository;
import com.game.domain.entities.KalahBoardDO;
import com.game.service.KalahBoardService;


/**
 * This class is implementation of {@link KalahBoardService}
 *
 *@author Rahul
 * */
@Service
public class KalahBoardServiceImpl implements KalahBoardService {
	Logger logger = LoggerFactory.getLogger(KalahBoardServiceImpl.class);
	private KalahBoardRepository kalahBoardRepository;
	
	@Autowired
	public KalahBoardServiceImpl(KalahBoardRepository kalahBoardRepository) {
		this.kalahBoardRepository = kalahBoardRepository;
	}
	
	/**
	 * intitalizes kalah pits
	 * 
	 * @param pits
	 * */
	private int[] initKalahPits(int[] pits) {
		Arrays.fill(pits, 6);
		pits[7]=0;
		pits[14]=0;
		pits[0]=-1;
		return pits;
	}
	
	/**
	 * Creates and returns kalah board
	 * 
	 * @return {@link KalahBoardDO}
	 * */
	public KalahBoardDO createKalahBoard() {
		int pits[]=new int[15];
		return KalahBoardDO.builder().pits(initKalahPits(pits))
							  .filled(false)
							  .build();
	}
	
	/**
	 * Persists kalah board instance {@link KalahBoardDO} to database
	 * 
	 * @param kBoard {@link KalahBoardDO}
	 * @return {@link KalahBoardDO}
	 * */
	public KalahBoardDO addKalahBoardDO(KalahBoardDO kBoardDO) {
		logger.debug("Adding kalah Board instance");
		return
		kalahBoardRepository.save(kBoardDO);
	}
}
