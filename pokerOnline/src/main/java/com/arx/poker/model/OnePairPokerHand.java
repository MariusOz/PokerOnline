package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class OnePairPokerHand extends PokerHand {

	public OnePairPokerHand(Integer highestValue, Integer secondHighestValue,
			Integer thirdHighestValue, Integer fourthHighestValue) {
		super(PokerHandEnum.ONE_PAIR, highestValue, secondHighestValue, thirdHighestValue, fourthHighestValue, null);
		// TODO Auto-generated constructor stub
	}

}
