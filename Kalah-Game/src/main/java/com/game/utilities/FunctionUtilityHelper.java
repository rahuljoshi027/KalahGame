package com.game.utilities;

import java.util.function.Function;

import com.game.constants.GameConstants;

public class FunctionUtilityHelper {

	public static Function<Integer, String> calculateCurrentPlayer = 
			pit->(pit>=1&&pit<=6)?GameConstants.SOUTH_PLAYER:
				                  GameConstants.NORTH_PLAYER;
			
    public static Function<Integer, String> getKalahLocation = 
    		pit->(pit==7)?GameConstants.SOUTH_PLAYER:
    			          GameConstants.NORTH_PLAYER;
    		
    public static Function<Integer, String> getPitLocation = 
    		pit->(pit>=1&&pit<=6)?GameConstants.SOUTH_PLAYER:
    			                  GameConstants.NORTH_PLAYER;
    		
    public static Function<String,Integer> getKalahPitIndex = 
    		loc -> loc.equals(GameConstants.SOUTH_PLAYER)?GameConstants.SOUTHKALAHINDEX:
    				  									  GameConstants.NORTHKALAHINDEX;

}
