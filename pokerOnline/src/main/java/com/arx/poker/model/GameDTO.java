package com.arx.poker.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDTO {
	
	private int gameId;
	
	// nom du joueur
	private String name;

	// votre main
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Card> playerHand;
	
	// cartes et mises sur la table
	private List<Card> cardsOnTable;
	private int totalBets;
	// ce que chaque joueur à sur la table et en banque
	// si les joueurs son couchés ou actifs
	private List<PlayerDTO> playerInfo;
	
	private PhaseEnum phase = null;
	
	private GameStateStatusEnum status;
	
	private int currentRound;

	private boolean myTurn;

	private int currentPlayerId;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public List<Card> getCardsOnTable() {
		return cardsOnTable;
	}

	public void setCardsOnTable(List<Card> cardsOnTable) {
		this.cardsOnTable = cardsOnTable;
	}

	public int getTotalBets() {
		return totalBets;
	}

	public void setTotalBets(int totalBets) {
		this.totalBets = totalBets;
	}

	public List<PlayerDTO> getPlayerInfo() {
		return playerInfo;
	}

	public void setPlayerInfo(List<PlayerDTO> playerInfo) {
		this.playerInfo = playerInfo;
	}

	public PhaseEnum getPhase() {
		return phase;
	}

	public void setPhase(PhaseEnum phase) {
		this.phase = phase;
	}

	public GameStateStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameStateStatusEnum status) {
		this.status = status;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}

	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}


	
}
