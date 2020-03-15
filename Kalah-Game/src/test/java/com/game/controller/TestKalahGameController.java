package com.game.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestClientException;

import com.game.datatransferobjects.KalahCreatedResponseDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestKalahGameController {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    @DirtiesContext
    public void testCreateKalah() throws RestClientException, MalformedURLException {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.setAccept(Collections.singletonList( MediaType.APPLICATION_JSON));
    	ResponseEntity r =
    	        restTemplate.exchange(new URL("http://localhost:" + port + "/games").toString(), HttpMethod.POST,null, KalahCreatedResponseDTO.class);
    	       assertEquals(HttpStatus.CREATED, r.getStatusCode());
    }
    
    @Test
    @DirtiesContext
    public void testMakeAkalahMoveException_1() throws RestClientException, MalformedURLException {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.setAccept(Collections.singletonList( MediaType.APPLICATION_JSON));
    	
    	ResponseEntity r =
    	        restTemplate.exchange(new URL("http://localhost:" + port + "/games/10/pits/2").toString(), HttpMethod.PUT,null, KalahCreatedResponseDTO.class);
    	       assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());
    }
    
    @Test
    @DirtiesContext
    public void testMakeAkalahMoveException_2() throws RestClientException, MalformedURLException {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.setAccept(Collections.singletonList( MediaType.APPLICATION_JSON));
    	ResponseEntity r =
    	        restTemplate.exchange(new URL("http://localhost:" + port + "/games/0/pits/99").toString(), HttpMethod.PUT,null, KalahCreatedResponseDTO.class);
    	       assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());
    }
}
