package com.game.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.game.constants.GameConstants;
import com.game.datatransferobjects.KalahCreatedResponseDTO;
import com.game.datatransferobjects.KalahResponseDTO;
import com.game.entities.KalahGameDO;


/**
 * This is a simple Utility helper class which provides methods 
 * to build response DTOs
 * 
 * @author Rahul
 * */
public class UtilityHelper {

	private static String SUBSTR = "/pits";
	
	/**
	 * Builds uri for given {@link KalahGameDO}
	 * 
	 * @param kGDO {@link KalahGameDO}
	 * @return String URI
	 * */
	private static String buildURI(KalahGameDO kGDO) {
		return 
		ServletUriComponentsBuilder.
	  	   fromCurrentRequest().
	  	   path("/{id}").
	  	   buildAndExpand(kGDO.getId()).
	  	   toUri().toString();
	}
	
	/**
	 * Builds a map of kalah pits
	 * 
	 * @param pits int[]
	 * @return StatusMap 
	 * */
	private static HashMap buildStatusMap(int[] pits) {
		LinkedHashMap<String,String> pitMap= new LinkedHashMap<>();
		for(int i=0;i<GameConstants.TOTALPITS;i++) {
			pitMap.put(String.valueOf(i+1), String.valueOf(pits[i]));
		}
		return pitMap;
	}
	
	/**
	 * Builds reponse DTO for passsed kalah game object after creation
	 * 
	 * @param kGDO {@link KalahGameDO}
	 * @return {@link KalahCreatedResponseDTO}
	 * */
	public static ResponseEntity<KalahCreatedResponseDTO> buildKalahCreatedResponseDTO(KalahGameDO kGDO) {
		
		return new ResponseEntity<KalahCreatedResponseDTO>( 
		KalahCreatedResponseDTO.builder().id(kGDO.getId())
								  .uri(buildURI(kGDO))
								  .build(),HttpStatus.CREATED);
	}
	
	/**
	 * Builds reponse DTO for passsed kalah game object after making a move
	 * 
	 * @param kGDO {@link KalahGameDO}
	 * @return {@link KalahResponseDTO}
	 * */
	public static ResponseEntity<KalahResponseDTO> buildKalahResponseDTO(KalahGameDO kGDO) {
		String url = buildURI(kGDO);
		return new ResponseEntity<KalahResponseDTO>(
		KalahResponseDTO.builder().id(kGDO.getId())
								  .url(url.substring(0, url.indexOf(SUBSTR)))
								  .status(buildStatusMap(Arrays.copyOfRange(kGDO.getKalahBoardDO().getPits(),1,
												  			                GameConstants.TOTALPITS+1)))
								  .build(),HttpStatus.OK);
	}
	
	
}
