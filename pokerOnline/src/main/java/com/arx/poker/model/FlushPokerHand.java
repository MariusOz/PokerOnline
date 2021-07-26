package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class FlushPokerHand extends PokerHand {

	public FlushPokerHand(Integer highestValue, Integer secondHighestValue,
			Integer thirdHighestValue, Integer fourthHighestValue, Integer fifthHighestValue) {
		super(PokerHandEnum.FLUSH, highestValue, secondHighestValue, thirdHighestValue, fourthHighestValue, fifthHighestValue);
		// TODO Auto-generated constructor stub
	}

}
