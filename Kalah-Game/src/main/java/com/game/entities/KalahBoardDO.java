package com.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This is an entity class for kalah game board
 * 
 * @author Rahul
 * */
@Entity
@Data
@AllArgsConstructor
@Builder
public class KalahBoardDO {
	
	KalahBoardDO(){}

	@Id
	@GeneratedValue
	long id;
	@Column
	int[] pits;
	@Column
	boolean filled;
	@OneToOne
	KalahGameDO kalahGameDO;
	 
}
