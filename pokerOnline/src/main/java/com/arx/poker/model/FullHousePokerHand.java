package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class FullHousePokerHand extends PokerHand {

	public FullHousePokerHand(Integer highestValue, Integer secondHighestValue) {
		super(PokerHandEnum.FULL_HOUSE, highestValue, secondHighestValue, null, null, null);
	}

}
