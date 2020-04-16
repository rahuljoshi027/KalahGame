package com.game.domain.datatransferobjects;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * This class is a DTO for newly created kalah instance. Provide builder implementation
 * to create the instance
 * 
 * @author Rahul
 * */
@Builder
@Data
@ApiModel(description="Contains Kalah instance details such as kalah game Id and path")
public class KalahCreatedResponseDTO  {

	long id;
	String uri;
}
