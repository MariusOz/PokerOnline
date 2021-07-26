package com.arx.poker.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arx.poker.model.GameDTO;
import com.arx.poker.model.GameDTOWithoutPlayerId;
import com.arx.poker.model.GameState;
import com.arx.poker.model.Player;
import com.arx.poker.model.PlayerDTO;
import com.arx.poker.model.PlayerDTOWithoutPlayerID;
import com.arx.poker.service.ActionEnum;
import com.arx.poker.service.GameHolderService;
import com.arx.poker.service.GameService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "games")

public class GameController {

	// un controller ne retourne que des DTO
	// Il retourne ce dont les services ont besoin
	// si un param est dans le path, c'est forcément un ID
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameHolderService gameHolderService;

	// TODO stoquer chaque partie pour pouvoir les récupérer
	// est il possible de creer un array pour garder les parties 
	@PostMapping
	public GameDTO createGame(@RequestParam("nbOfPlayer") int nbOfPlayer) throws Exception {
		GameState gamestate = gameService.createGame(nbOfPlayer);
		return gameService.gameStateToGameInfo(gamestate, null);
	}
	
	@RequestMapping(value = "/{gameId}/player", method = RequestMethod.POST)
	@ResponseBody
	public PlayerDTO addPlayerToExistingGame(@PathVariable("gameId") Integer id, @RequestBody PlayerDTOWithoutPlayerID player) throws Exception {
		// ajouter le nouveau joueur a l'arraylist de players pour la partie
		return gameService.addPlayerToSpecificGame(id, player.getName());
	}
	
	// faire un web service que tout les players appellent constament pour savoir si c'est leur tour de jeu 
	// si oui joue, si non redemande
	
	@RequestMapping(method = RequestMethod.GET)
	public List<GameState> gamesLists() {
		return gameHolderService.getGameStateList();
	}
	
	@RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
	public GameDTOWithoutPlayerId game(@PathVariable("gameId") int gameId,@RequestParam("playerId") Integer playerId) {
		return gameService.getGame(gameId, playerId);
	}
	
	@RequestMapping(value  = "/{gameId}/play", method = RequestMethod.POST)
	public void move(@PathVariable("gameId") int gameId,@RequestParam("playerId") Integer playerId, @RequestParam("playerAction") ActionEnum playerAction) {
		// la partie dans laquel il joue
		// verifier si le joueur qui joue est bien celui qui doit jouer 
		// gérer son action (bet, fonds restants, stillInGame)
		// passer au joueur d'après (si il existe) 
		// fin prématurer du round?
		// dernier round?
		gameService.reactToPlayerAction(gameId, playerId, playerAction);
	}
	
}
