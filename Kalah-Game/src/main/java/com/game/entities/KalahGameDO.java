package com.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is an entity class for kalah game 
 * 
 * @author Rahul
 * */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KalahGameDO {

	
	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "user_sequence"),
        @Parameter(name = "initial_value", value = "10"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	long id;
	@Column
	int lastMovedPit;
	@Column
	String previousPlayer;
	@Column
	String currentPlayer;
	@Column
	String kalahLocation;
	@Column
	boolean wasKalah;
	@Column
	boolean gameOver;
	@Column
	boolean oneMoreMove;
	@OneToOne
	KalahBoardDO kalahBoardDO;
	
	
}
