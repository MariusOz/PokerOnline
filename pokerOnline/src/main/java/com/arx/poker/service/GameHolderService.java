package com.arx.poker.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.arx.poker.model.GameState;
import com.arx.poker.model.Player;

@Component
public class GameHolderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameHolderService.class);
	
	private List<GameState> gameStateList = new ArrayList<>();

	// Lister les parties 
	// voir le detail d'une partie 
	public GameState accessSpecificGameState(int gameID){
		
		for (GameState gameState : gameStateList) {
			if(gameState.getId() == gameID ) {
				return gameState;
			}
		}
		throw new RuntimeException("this game does not exist"); 
	}
	
	public List<GameState> accessGameStates(){
		return gameStateList;
	}
	
	public void showGames() {
		for (int i = 0; i < gameStateList.size(); i++) {
			LOGGER.info("GameState ", gameStateList.get(i));
		}
	}
	
	public List<GameState> getGameStateList() {
		return gameStateList;
	}

}
