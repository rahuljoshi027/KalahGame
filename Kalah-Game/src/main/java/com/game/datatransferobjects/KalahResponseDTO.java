package com.game.datatransferobjects;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * This class is a DTO for sharing kalah details of successfully completed move. Provide builder implementation
 * to create the instance
 * 
 * @author Rahul
 * */
@Data
@Builder
@ApiModel(description="Contians response Kalah response details such as kalah game id, path, pit status")
public class KalahResponseDTO {
	long id;
	String url;
	Map status;
}
