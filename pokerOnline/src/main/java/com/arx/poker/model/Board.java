package com.arx.poker.model;

import java.util.ArrayList;

public class Board {

	private final ArrayList<Card> communityCards = new ArrayList<Card>();

	public ArrayList<Card> getCommunityCards() {
		return communityCards;
	}
}
