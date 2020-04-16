package com.game.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.game.domain.datatransferobjects.ErrorResponseDTO;
import com.game.utilities.exceptions.BadRequestException;


/**
 * This is a single place to handle exceptions all over the application
 * 
 * @author Rahul
 * */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
		logger.error("Constaints violated");
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseDTO> handleGameOverException(Exception ex,WebRequest req){
		logger.error(ex.getMessage(), ex);
	  return new ResponseEntity<ErrorResponseDTO>(ErrorResponseDTO.builder()
				.timeStamp(LocalDateTime.now())
				.error(HttpStatus.BAD_REQUEST)
				.message(ex.getMessage().toString())
				.path(((ServletWebRequest)req).getRequest().getRequestURI())
				.build(),
				 HttpStatus.BAD_REQUEST);
	}
	
	
}
