package com.game.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import com.game.domain.entities.KalahGameDO;
import com.game.utilities.exceptions.BadRequestException;
import com.game.utilities.general.UtilityHelper;
import com.game.utility.TestUtilityHelper;

@SpringBootTest
public class TestPlayKalahService {

	@Autowired
	PlayKalahService playKalahService;
	@Autowired
	KalahBoardService kalahBoardService;
	@Autowired
	KalahGameService kalahGameService;
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testExecuteMoveException() {
		HashMap<String,String> expectedMap = TestUtilityHelper.buildExecuteMoveResponse();
		Throwable t = assertThrows(BadRequestException.class,()->  UtilityHelper.buildKalahResponseDTO(playKalahService.executeMove(99, 1)));
		assertEquals(t.getMessage(),"invalid game id :: 99");
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testKalahMove_1() {
		int[] expectedPits = new int[15];
		Arrays.fill(expectedPits, 6);
		expectedPits[0] = -1;
		expectedPits[2]=expectedPits[14]=0;
		expectedPits[3] = expectedPits[4]=expectedPits[5]=expectedPits[6]=expectedPits[8]=7;
		expectedPits[7]=1;
		KalahGameDO kGDO = playKalahService.createAndAddKalahInstance();
		kGDO =playKalahService.executeMove(kGDO.getId(), 2);
		assertArrayEquals(expectedPits, kGDO.getKalahBoardDO().getPits());
		
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testKalahMove_2() {
		int[] expectedPits = new int[15];
		Arrays.fill(expectedPits, 6);
		expectedPits[0] = -1;
		expectedPits[1] = expectedPits[2]=expectedPits[14]=0;
		expectedPits[3] = expectedPits[4]=expectedPits[5]=expectedPits[6]=8;
		expectedPits[7]=2;
		expectedPits[8]=expectedPits[9]=7;
		KalahGameDO kGDO = playKalahService.createAndAddKalahInstance();
		playKalahService.executeMove(kGDO.getId(), 1);
		kGDO = playKalahService.executeMove(kGDO.getId(), 2);
		assertArrayEquals(expectedPits, kGDO.getKalahBoardDO().getPits());
		
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testKalahMove_3() {
		int[] expectedPits = new int[15];
		Arrays.fill(expectedPits, 6);
		expectedPits[0] = -1;
		expectedPits[2]=expectedPits[14]=0;
		expectedPits[3] = expectedPits[4]=expectedPits[5]=expectedPits[6]=expectedPits[8]=7;
		expectedPits[7]=1;
		KalahGameDO kGDO = playKalahService.createAndAddKalahInstance();
		playKalahService.executeMove(kGDO.getId(), 2);
		Throwable t = assertThrows(BadRequestException.class,()->  playKalahService.executeMove(kGDO.getId(), 3));
		assertEquals(t.getMessage(),"Wrong move. Player selection incorrect");
		
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testKalahMove_4() {
		int[] expectedPits = new int[15];
		Arrays.fill(expectedPits, 6);
		expectedPits[0] = -1;
		expectedPits[8] = expectedPits[9]=expectedPits[7]=0;
		expectedPits[3] = expectedPits[4]=expectedPits[5]=expectedPits[6]=6;
		expectedPits[14]=2;
		expectedPits[1]=expectedPits[2]=7;
		expectedPits[10]=expectedPits[11]=expectedPits[12]=expectedPits[13]=8;
		KalahGameDO kGDO = playKalahService.createAndAddKalahInstance();
		playKalahService.executeMove(kGDO.getId(), 8);
		kGDO = playKalahService.executeMove(kGDO.getId(), 9);
		assertArrayEquals(expectedPits, kGDO.getKalahBoardDO().getPits());
		
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testWrongPlayerException() {
		KalahGameDO kGDO = playKalahService.createAndAddKalahInstance();
		playKalahService.executeMove(kGDO.getId(), 1);
		Throwable t = assertThrows(BadRequestException.class,()->  playKalahService.executeMove(kGDO.getId(), 8));
		assertEquals(t.getMessage(),"Wrong move. Player selection incorrect");
	}
	
	
	
	
}
