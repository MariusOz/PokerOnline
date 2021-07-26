package com.arx.poker.model;

import com.arx.poker.service.PokerHandEnum;

public class StraightPokerHand extends PokerHand {

	public StraightPokerHand(Integer highestValue) {
		super(PokerHandEnum.STRAIGHT, highestValue, null, null, null, null);
		// TODO Auto-generated constructor stub
	}

}
