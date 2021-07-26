package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class ThreeOfAKindPokerHand extends PokerHand {

	public ThreeOfAKindPokerHand(Integer highestValue, Integer secondHighestValue, Integer thirdHighestValue) {
		super(PokerHandEnum.THREE_OF_A_KIND, highestValue, secondHighestValue, thirdHighestValue, null, null);
	}

}
