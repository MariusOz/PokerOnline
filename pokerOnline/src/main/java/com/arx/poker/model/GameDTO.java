package com.arx.poker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.arx.poker.service.GameStateStatusEnum;
import com.arx.poker.service.PhaseEnum;
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

	public GameDTO(int gameId, String name, ArrayList<Card> playerHand, ArrayList<Card> cardsOnTable, int totalBets,
			ArrayList<PlayerDTO> playerInfo, PhaseEnum phase, GameStateStatusEnum status, int currentRound) {
		this.gameId = gameId;
		this.playerHand = playerHand;
		this.cardsOnTable = cardsOnTable;
		this.totalBets = totalBets;
		this.playerInfo = playerInfo;
		this.name = name;
		this.phase = phase;
		this.status = status;
		this.currentRound = currentRound;
	}
	public List<Card> getPlayerHand() {
		return Collections.unmodifiableList(playerHand);
	}

	public List<Card> getCardsOnTable() {
		return Collections.unmodifiableList(cardsOnTable);
	}

	public int getTotalBets() {
		return totalBets;
	}

	public List<PlayerDTO> getPlayerInfo() {
		return Collections.unmodifiableList(playerInfo);
	}
	
	public String getName() {
		return name;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}
	public void setCardsOnTable(List<Card> cardsOnTable) {
		this.cardsOnTable = cardsOnTable;
	}
	public void setTotalBets(int totalBets) {
		this.totalBets = totalBets;
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
}
