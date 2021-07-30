package com.arx.poker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arx.poker.model.GameDTO;
import com.arx.poker.model.GameState;
import com.arx.poker.model.PlayerDTO;
import com.arx.poker.service.ActionEnum;
import com.arx.poker.service.GameHolderService;
import com.arx.poker.service.GameService;

@RestController
@RequestMapping(value = "games")

public class GameController {

	// un controller ne retourne que des DTO
	// Il retourne ce dont les services ont besoin
	// si un param est dans le path, c'est forcément un ID
	@Autowired
	private GameService gameService;

	// stoquer chaque partie pour pouvoir les récupérer
	// est il possible de creer un array pour garder les parties
	@PostMapping
	public GameDTO createGame(@RequestParam("nbOfPlayer") int nbOfPlayer) throws Exception {
		GameState gamestate = gameService.createGame(nbOfPlayer);
		return gameService.gameStateToGameInfo(gamestate, null, false);
	}

	@RequestMapping(value = "/{gameId}/player", method = RequestMethod.POST)
	@ResponseBody
	public PlayerDTO addPlayerToExistingGame(@PathVariable("gameId") Integer id, @RequestBody PlayerDTO player)
			throws Exception {
		// ajouter le nouveau joueur a l'arraylist de players pour la partie
		return gameService.addPlayerToSpecificGame(id, player.getName());
	}

	/**
	 * Get list of all games
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<GameDTO> gamesLists() {
		return gameService.list();
	}

	@RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
	public GameDTO game(@PathVariable("gameId") int gameId, @RequestParam("playerId") Integer playerId) {
		return gameService.getGame(gameId, playerId);
	}

	@RequestMapping(value = "/{gameId}/play", method = RequestMethod.POST)
	public void move(@PathVariable("gameId") int gameId, @RequestParam("playerId") Integer playerId,
			@RequestParam("playerAction") String playerAction) {
		// la partie dans laquel il joue
		// verifier si le joueur qui joue est bien celui qui doit jouer
		// gérer son action (bet, fonds restants, stillInGame)
		// passer au joueur d'après (si il existe)
		// fin prématurer du round?
		// dernier round?
		gameService.reactToPlayerAction(gameId, playerId, ActionEnum.valueOf(playerAction));
	}

}
