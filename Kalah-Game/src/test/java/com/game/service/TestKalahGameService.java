package com.game.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.game.constants.GameConstants;
import com.game.entities.KalahBoardDO;
import com.game.entities.KalahGameDO;
import com.game.utility.TestUtilityHelper;

@SpringBootTest
public class TestKalahGameService {
	
	@Autowired
	KalahGameService kalahGameService;
	
	@Test
	@DirtiesContext
	public void testCreateKalahInstance() {
		KalahGameDO expectedKGDO = TestUtilityHelper.buildKalahGameDO(null);
		KalahGameDO actualKGDO = kalahGameService.createKalahInstance(null);
		assertEquals(expectedKGDO,actualKGDO);
	}
	
	@Test
	@DirtiesContext
	public void testAddKalahInstance() {
		KalahGameDO expectedKGDO = TestUtilityHelper.buildKalahGameDO(null);
		KalahGameDO actualKGDO = kalahGameService.addKalahGameInstance(expectedKGDO);
		assertEquals(expectedKGDO,actualKGDO);
	}

}
