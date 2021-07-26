package com.arx.poker.model;

public class Pot {

	private final int value;
	private final Player[] competingPlayers;
	
	public Pot(int value, Player[] competingPlayers) {
		super();
		this.value = value;
		this.competingPlayers = competingPlayers;
	}
	
	
	public int getValue() {
		return value;
	}
	
	public Player[] getCompetingPlayers() {
		return competingPlayers;
	}
}
