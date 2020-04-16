package com.game.utilities.lambdas;

import java.util.function.Predicate;

import com.game.utilities.constants.GameConstants;

public class PredicateUtilityHelper {

	public static Predicate<Integer> isThisKalah = pit -> (pit==7||pit==14)?true:false;
	
	public static Predicate<Integer> isKalah = 
			pit->(pit==GameConstants.SOUTHKALAHINDEX||pit==GameConstants.NORTHKALAHINDEX)?true:false;

}
