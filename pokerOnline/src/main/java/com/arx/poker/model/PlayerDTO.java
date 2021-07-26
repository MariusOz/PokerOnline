package com.arx.poker.model;
/**
 * 
 * Informations publiques sur les joueurs
 * @author ozann
 *
 */
public class PlayerDTO {

	// nom du joueur
	private final String name;
	// argent mis en jeux
	private final int bet;
	// argent restant
	private final int funds;
	// participe a la manche
	private final boolean isStillInRound;
	
	private boolean hasPlayed;
	
	private int playerId;
	
	public PlayerDTO(String name, int bet, int bets, boolean isStillInRound, boolean hasPlayed, int PlayerId) {
		super();
		this.name = name;
		this.bet = bet;
		this.funds = bets;
		this.isStillInRound = isStillInRound;
		this.hasPlayed = hasPlayed;
		this.playerId = PlayerId;
	}

	public String getName() {
		return name;
	}

	public int getBet() {
		return bet;
	}

	public int getFunds() {
		return funds;
	}

	public boolean isStillInRound() {
		return isStillInRound;
	}
	
	public boolean hasPlayed() {
		return hasPlayed;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int id) {
		this.playerId = id;
	}
}
