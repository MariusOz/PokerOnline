package com.arx.poker.model;

import java.util.ArrayList;

public class Player {

	private final String name;
	private final ArrayList<Card> privateHand = new ArrayList<Card>(2);
	private int funds = 200;
	private int bet;
	private boolean hasPlayed = false;
	private int id;


	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getPrivateHand() {
		return privateHand;
	}

	public int getFunds() {
		return funds;
	}

	public int getBet() {
		return bet;
	}

	public void setFunds(int totalFunds) {
		this.funds = totalFunds;
	}

	public void setBet(int i) {
		this.bet = i;
	}

	public boolean getHasPlayed() {
		return hasPlayed;
	}

	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
