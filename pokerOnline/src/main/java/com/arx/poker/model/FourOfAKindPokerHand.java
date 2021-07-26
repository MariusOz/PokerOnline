package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class FourOfAKindPokerHand extends PokerHand {

	public FourOfAKindPokerHand(Integer highestValue, Integer secondHighestValue) {
		super(PokerHandEnum.FOUR_OF_A_KIND, highestValue, secondHighestValue, null, null, null);
	}

}
