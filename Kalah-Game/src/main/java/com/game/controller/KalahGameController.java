package com.game.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.datatransferobjects.KalahCreatedResponseDTO;
import com.game.datatransferobjects.KalahResponseDTO;
import com.game.service.PlayKalahService;
import com.game.service.impl.KalahGameServiceImpl;
import com.game.service.impl.PlayKalahServiceImpl;
import com.game.utilities.UtilityHelper;

import io.swagger.annotations.ApiOperation;


/**
 * This is main controller for the applications
 * It exposes 2 rest apis , one for creating a
 * new kalah game and other one for making a move
 * 
 * @author Rahul
 * */
@Validated
@RestController
public class KalahGameController {

	
	private PlayKalahService playKalahService;
	
	@Autowired
	KalahGameController(PlayKalahService playKalahService){
		this.playKalahService = playKalahService;
	}
	
	/**
	 * Creates a kalah instance
	 * 
	 * @return {@link KalahCreatedResponseDTO}
	 * */
	@PostMapping(path="/games")
	@ApiOperation(value = "Create an instance of Kalah",
	notes =  "Create and initializes instance of Kalah game",
	response=ResponseEntity.class)
	public ResponseEntity<KalahCreatedResponseDTO> createKalah() {
		return
		UtilityHelper.buildKalahCreatedResponseDTO(
				playKalahService.createAndAddKalahInstance());
	}
	
	/**
	 * Makes an appropriate kalah move if inputs are valid
	 * 
	 * @return {@link KalahResponseDTO}
	 * */
	@PutMapping(path="/games/{gameId}/pits/{pitId}")
	@ApiOperation(value = "Make a kalah move",
	notes =  "We can make a kalah move by specifying game id and pit",
	response=ResponseEntity.class)
	public ResponseEntity<KalahResponseDTO> makeAkalahMove(@PathVariable("gameId") @Min(1) long gameId,
										 @PathVariable("pitId") @Min(1) @Max(14)int pitId) {
		return
		UtilityHelper.buildKalahResponseDTO(
				playKalahService.executeMove(gameId, pitId));
	}
	
}
