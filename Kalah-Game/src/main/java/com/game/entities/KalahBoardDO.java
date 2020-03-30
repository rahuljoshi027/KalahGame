package com.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	int[] pits;
	@Column
	boolean filled;
	/*@OneToOne(mappedBy="kalahBoardDO", fetch=FetchType.LAZY)
    @JsonManagedReference
	KalahGameDO kalahGameDO;*/
	 
}
