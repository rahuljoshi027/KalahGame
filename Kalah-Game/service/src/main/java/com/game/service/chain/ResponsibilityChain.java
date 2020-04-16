package com.game.service.chain;

import org.springframework.core.Ordered;

import com.game.domain.entities.KalahGameDO;

/**
 * Main abstraction for all of rules applier.
 * <p>
 * This is some kind of Chain of Responsibility pattern in Spring ecosystem using {@link Ordered} interface.
 * The less order number {@link ResponsibilityChain} instance has, the sooner it is called to process the current turn
 *
 * @author Rahul
 * @see com.game.service.chain.impl.MakeMove
 * @see com.game.service.chain.impl.UpdateMove
 * @see com.game.service.chain.impl.ValidateMove
 */

public interface ResponsibilityChain extends Ordered{

	 /**
     * main entry point for each element of the chain.
     * By applying the rule, it means that each chain element changes {@param kGDO} based on rules, which it's responsible for
     *
     * @param kGDO {@link KalahGameDO}
     * @param pit
     */
	void applyGameRules(KalahGameDO kGDO,int pit);
	
	
	 /**
     * The less order number {@link ResponsibilityChain} instance has, the sooner it is called to process the current turn
     *
     * @return order number
     * @see Ordered
     */
	int getOrder();
}
