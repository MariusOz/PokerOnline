package com.arx.poker.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.arx.poker.service.GameStateStatusEnum;
import com.arx.poker.service.PhaseEnum;

public class GameState {

	private int id;
	private  String name;

	private final ArrayList<Card> deck;
	private final ArrayList<Player> players;
	private final Board board;
	private ArrayList<Player> playersInGame;
	private Player dealer = null;
	private Player currentPlayer = null;
	private int currentRound = 0;
	private int smallBlind = 1;
	private int BigBlind = 2;
	private GameStateStatusEnum status = GameStateStatusEnum.WAITING;
	private int nbOfPlayer;
	private PhaseEnum currentPhase;
	private LocalDateTime beginPlayerAction;


	public GameState(int nbOfPlayer) {
		deck = new ArrayList<Card>();
		players = new ArrayList<Player>();
		playersInGame = new ArrayList<Player>();
		board = new Board();
		this.nbOfPlayer = nbOfPlayer;
	}

	public Player getDealer() {
		return dealer;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Player> getPlayersInGame() {
		return playersInGame;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public Board getBoard() {
		return board;
	}

	public void setDealer(Player firstPlayer) {
		this.dealer = firstPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
	}

	public int getBigBlind() {
		return BigBlind;
	}

	public void setBigBlind(int bigBlind) {
		BigBlind = bigBlind;
	}

	public int getId() {
		return id;
	}

	public void setId(int randomID) {
		this.id = randomID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public GameStateStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(GameStateStatusEnum readyToStart) {
		this.status = readyToStart;
	}
	
	public int getNbOfPlayer() {
		return nbOfPlayer;
	}
	
	public void setNbOfPlayer(int nbOfPlayer) {
		this.nbOfPlayer = nbOfPlayer;
	}

	public PhaseEnum getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(PhaseEnum currentPhase) {
		this.currentPhase = currentPhase;
	}
	
	public LocalDateTime getBeginPlayerAction() {
		return beginPlayerAction;
	}
	
	public void setBeginPlayerAction(LocalDateTime beginPlayerAction) {
		this.beginPlayerAction = beginPlayerAction;
	}
}
