package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class StraightFlushPokerHand extends PokerHand {

	public StraightFlushPokerHand(Integer highestValue) {
		super(PokerHandEnum.STRAIGHT_FLUSH, highestValue, null, null, null, null);
	}

}
