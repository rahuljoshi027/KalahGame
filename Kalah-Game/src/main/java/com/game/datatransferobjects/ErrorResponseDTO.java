package com.game.datatransferobjects;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * This class is a DTO for error response. Provide builder implementation
 * to create the instance
 * 
 * @author Rahul
 * */
@Data
@Builder
@JsonInclude(value=Include.NON_NULL)
@ApiModel(description="Contains error response details such as timestamp,error,message,path")
public class ErrorResponseDTO {
	LocalDateTime timeStamp;
	HttpStatus error;
	String message;
	String path;
	Object data;
	Object metaData;
}
