package com.game.utility;

import java.util.Arrays;
import java.util.HashMap;

import com.game.constants.GameConstants;
import com.game.entities.KalahBoardDO;
import com.game.entities.KalahGameDO;

public class TestUtilityHelper {
	private static int[] initKalahPits(int[] pits) {
		Arrays.fill(pits, 6);
		pits[7]=0;
		pits[14]=0;
		pits[0]=-1;
		return pits;
	}
	

	public static KalahGameDO buildKalahGameDO(KalahBoardDO kBDO) {
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
	
	public static KalahBoardDO buildKalahBoard() {
		int pits[]=new int[15];
		return KalahBoardDO.builder().pits(initKalahPits(pits))
							  .filled(false)
							  .build();
	}

	
	public KalahGameDO buildKalahGameAndBoardDO(){
		return buildKalahGameDO(buildKalahBoard());
	}
	
	public static HashMap<String,String> buildExecuteMoveResponse(){
		
		HashMap<String,String> hm=new HashMap<>();
		hm.put("1","6");
		hm.put("2","0");
		hm.put("3","7");
		hm.put("4","7");
		hm.put("5","7");
		hm.put("6","7");
		hm.put("7","1");
		hm.put("8","7");
		hm.put("9","6");
		hm.put("10","6");
		hm.put("11","6");
		hm.put("12","6");
		hm.put("13","6");
		hm.put("14","0");
		return hm;
		
	}
}
