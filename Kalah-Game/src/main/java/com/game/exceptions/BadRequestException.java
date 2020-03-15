package com.game.exceptions;

import lombok.NoArgsConstructor;

/**
 * This is an implementation of runtime exception {@link RuntimeException}
 * for throwing an runtime exception in case of bad request/input
 * 
 * @author Rahul
 * */
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
	
	public BadRequestException(String message) {
		super(message);
	}
}
