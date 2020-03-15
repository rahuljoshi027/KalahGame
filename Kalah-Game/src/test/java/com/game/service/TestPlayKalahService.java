package com.game.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.game.entities.KalahGameDO;
import com.game.exceptions.BadRequestException;
import com.game.utilities.UtilityHelper;
import com.game.utility.TestUtilityHelper;

@SpringBootTest
public class TestPlayKalahService {

	@Autowired
	PlayKalahService playKalahService;
	
	@Test
	@DirtiesContext
	public void  testCreateAndAddKalahInstance() {
		
		KalahGameDO expectedkGDO = TestUtilityHelper.buildKalahGameDO(TestUtilityHelper.buildKalahBoard());
		KalahGameDO actualKGDO = playKalahService.createAndAddKalahInstance();
		expectedkGDO.setId(actualKGDO.getId());
		assertEquals(expectedkGDO.getCurrentPlayer(),actualKGDO.getCurrentPlayer());
		assertEquals(expectedkGDO.getKalahLocation(),actualKGDO.getKalahLocation());
		assertEquals(expectedkGDO.getLastMovedPit(),actualKGDO.getLastMovedPit());
		assertEquals(expectedkGDO.getPreviousPlayer(),actualKGDO.getPreviousPlayer());
		assertArrayEquals(expectedkGDO.getKalahBoardDO().getPits(),actualKGDO.getKalahBoardDO().getPits());

	}
	
	@Test
	@DirtiesContext
	public void testExecuteMoveException() {
		HashMap<String,String> expectedMap = TestUtilityHelper.buildExecuteMoveResponse();
		Throwable t = assertThrows(BadRequestException.class,()->  UtilityHelper.buildKalahResponseDTO(playKalahService.executeMove(99, 1)));
		assertEquals(t.getMessage(),"invalid game id :: 99");
	}
	
}
