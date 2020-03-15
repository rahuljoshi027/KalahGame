package com.game.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.game.entities.KalahBoardDO;
import com.game.utility.TestUtilityHelper;

@SpringBootTest
public class TestKalahGameBoard {
	
	@Autowired
	KalahBoardService kalahBoardService;

	@Test
	@DirtiesContext
	public void testCreateKalahBoard() {
		KalahBoardDO expectedBDO = TestUtilityHelper.buildKalahBoard();
		KalahBoardDO actualBDO = kalahBoardService.createKalahBoard();
		assertEquals(expectedBDO,actualBDO);
	}
	
	@Test
	@DirtiesContext
	public void testAddKalahBoard() {
		KalahBoardDO expectedBDO = TestUtilityHelper.buildKalahBoard();
		KalahBoardDO actualBDO = kalahBoardService.addKalahBoardDO(expectedBDO);
		assertEquals(expectedBDO,actualBDO);
	}
}
