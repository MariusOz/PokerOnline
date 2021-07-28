package com.arx.poker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arx.poker.model.Card;
import com.arx.poker.model.GameDTO;
import com.arx.poker.model.GameState;
import com.arx.poker.model.Player;
import com.arx.poker.model.PlayerDTO;
import com.arx.poker.model.Pot;

@Component
public class GameService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
	@Autowired
	private HandService handService;
	@Autowired
	private GameHolderService gameHolderService;

	public void initDeck(GameState gameState) {
		ArrayList<Card> deck = gameState.getDeck();
		deck.clear();
		deck.add(new Card(2, ColorEnum.SPADE));
		deck.add(new Card(3, ColorEnum.SPADE));
		deck.add(new Card(4, ColorEnum.SPADE));
		deck.add(new Card(5, ColorEnum.SPADE));
		deck.add(new Card(6, ColorEnum.SPADE));
		deck.add(new Card(7, ColorEnum.SPADE));
		deck.add(new Card(8, ColorEnum.SPADE));
		deck.add(new Card(9, ColorEnum.SPADE));
		deck.add(new Card(10, ColorEnum.SPADE));
		deck.add(new Card(11, ColorEnum.SPADE));
		deck.add(new Card(12, ColorEnum.SPADE));
		deck.add(new Card(13, ColorEnum.SPADE));
		deck.add(new Card(14, ColorEnum.SPADE));
		deck.add(new Card(2, ColorEnum.HEART));
		deck.add(new Card(3, ColorEnum.HEART));
		deck.add(new Card(4, ColorEnum.HEART));
		deck.add(new Card(5, ColorEnum.HEART));
		deck.add(new Card(6, ColorEnum.HEART));
		deck.add(new Card(7, ColorEnum.HEART));
		deck.add(new Card(8, ColorEnum.HEART));
		deck.add(new Card(9, ColorEnum.HEART));
		deck.add(new Card(10, ColorEnum.HEART));
		deck.add(new Card(11, ColorEnum.HEART));
		deck.add(new Card(12, ColorEnum.HEART));
		deck.add(new Card(13, ColorEnum.HEART));
		deck.add(new Card(14, ColorEnum.HEART));
		deck.add(new Card(2, ColorEnum.CLUB));
		deck.add(new Card(3, ColorEnum.CLUB));
		deck.add(new Card(4, ColorEnum.CLUB));
		deck.add(new Card(5, ColorEnum.CLUB));
		deck.add(new Card(6, ColorEnum.CLUB));
		deck.add(new Card(7, ColorEnum.CLUB));
		deck.add(new Card(8, ColorEnum.CLUB));
		deck.add(new Card(9, ColorEnum.CLUB));
		deck.add(new Card(10, ColorEnum.CLUB));
		deck.add(new Card(11, ColorEnum.CLUB));
		deck.add(new Card(12, ColorEnum.CLUB));
		deck.add(new Card(13, ColorEnum.CLUB));
		deck.add(new Card(14, ColorEnum.CLUB));
		deck.add(new Card(2, ColorEnum.DIAMOND));
		deck.add(new Card(3, ColorEnum.DIAMOND));
		deck.add(new Card(4, ColorEnum.DIAMOND));
		deck.add(new Card(5, ColorEnum.DIAMOND));
		deck.add(new Card(6, ColorEnum.DIAMOND));
		deck.add(new Card(7, ColorEnum.DIAMOND));
		deck.add(new Card(8, ColorEnum.DIAMOND));
		deck.add(new Card(9, ColorEnum.DIAMOND));
		deck.add(new Card(10, ColorEnum.DIAMOND));
		deck.add(new Card(11, ColorEnum.DIAMOND));
		deck.add(new Card(12, ColorEnum.DIAMOND));
		deck.add(new Card(13, ColorEnum.DIAMOND));
		deck.add(new Card(14, ColorEnum.DIAMOND));
		Collections.shuffle(deck);
		LOGGER.info("The deck is full and shuffled");
		// détermine l'ordre de jeu (premier joueur)
		// vider la table des cartes du round précédent
		//

	}

	public void registerPlayer(Player player, GameState gameState) {
		gameState.getPlayers().add(player);
	}

	public void dealCardsToPlayer(GameState gameState) {
		for (Player player : gameState.getPlayers()) {
			player.getPrivateHand().clear();
			player.getPrivateHand().add(gameState.getDeck().remove(0));
			player.getPrivateHand().add(gameState.getDeck().remove(0));
			LOGGER.info("cartes de " + player.getName() + " : " + player.getPrivateHand().toString());
		}
	}

	/**
	 * Détermine le premier joueur en fontion du moment de la partie. Si la partie
	 * viens de commencer, le premier joueur est séléctionné aléatoirement, sinon ce
	 * role est attribué au prochain joueur dans la liste.
	 */
	public Player findDealer(GameState gameState) {
		Player player;
		if (gameState.getDealer() == null) {
			int randomPlayerIndex = Double.valueOf(Math.ceil(Math.random() * gameState.getPlayers().size() - 1))
					.intValue();
			player = gameState.getPlayers().get(randomPlayerIndex);
		} else {
			int dealerIndex = (gameState.getPlayers().indexOf(gameState.getDealer()) + 1)
					% gameState.getPlayers().size();
			player = gameState.getPlayers().get(dealerIndex);
		}
		return player;
	}

	private Player findFirstPlayer(GameState gameState) {
		Player player;
		int nextPlayerIndex = (gameState.getPlayers().indexOf(gameState.getDealer()) + 3)
				% gameState.getPlayers().size();
		player = gameState.getPlayers().get(nextPlayerIndex);
		// TODO si player est tapi, trouver les prochain premier joueur
		return player;

	}
	/**
	 * Récupère le montant parié par un joueur et l'ajoute au pot
	 * 
	 * @param player
	 */
	private int computePot(GameState gameState) {
		int pot = 0;
		for (Player p : gameState.getPlayers()) {
			pot += p.getBet();
		}
		return pot;
	}

	private void dealCardsOnBoard(GameState gameState) {
		Card remove = gameState.getDeck().remove(0);
		gameState.getBoard().getCommunityCards().add(remove);
//		LOGGER.info("card added to the board: " + remove);
	}

	private boolean isRoundEarlyEnded(GameState gameState) {
		// verifie si il reste plus d'un joueur dans la partie ou que la phase 4 soit
		// terminé
		return gameState.getPlayersInGame().size() == 1;
	}

	private void status(GameState gameState) {

		Pot[] pots = new Pot[gameState.getPlayers().size()];
		int potCounter = 0;

		for (int i = 0; i < gameState.getPlayers().size(); i++) {
			// trouver la valeur minimum
			int minBet = Integer.MAX_VALUE;
			for (Player p : gameState.getPlayers()) {
				if (p.getBet() < minBet && isPlayerStillInRound(p, gameState)) {
					minBet = p.getBet();
				}
			}
			// fait participer les joueurs a ce pot précis
			int amountInPot = 0;

			List<Player> potentialWinnerForPot = new ArrayList<>();

			for (Player p : gameState.getPlayers()) {
				// récupérer pour tout les joueur la minBet ou leur bet si il est inférieur
				if (p.getBet() > 0) {
					if (p.getBet() >= minBet) {
						amountInPot += minBet;
						p.setBet(p.getBet() - minBet);
					} else {
						amountInPot += p.getBet();
						p.setBet(0);
					}
					if (isPlayerStillInRound(p, gameState)) {
						potentialWinnerForPot.add(p);
					}
				}
			}
			if (amountInPot > 0) {
				pots[potCounter] = new Pot(amountInPot,
						potentialWinnerForPot.toArray(new Player[potentialWinnerForPot.size()]));
				potCounter += 1;
			}

		}

		for (Pot pot : pots) {
			if (pot != null) {
				List<Player> winnersList = new ArrayList<>();

				if (pot.getCompetingPlayers().length > 1) {
					// on regarde parmis les potentiels winners qui sont les gagnants
					// les gagnants sont les joueur dont la main n'a aucune autre main plus forte
					for (Player p : pot.getCompetingPlayers()) {
						List<List<Card>> otherCards = new ArrayList<List<Card>>();
						for (Player player : pot.getCompetingPlayers()) {
							if (!player.equals(p)) {
								otherCards.add(player.getPrivateHand());
							}
						}
						boolean winner = !handService.isAnyoneStrongerThanMe(p.getPrivateHand(),
								gameState.getBoard().getCommunityCards(), otherCards);
						if (winner) {
							winnersList.add(p);
						}
					}
				} else {
					winnersList.add(pot.getCompetingPlayers()[0]);
				}

				String winners = winnersList.stream().map(Player::getName).collect(Collectors.joining(", "));
				if (winnersList.size() > 1) {
					LOGGER.info(winners + " se partagent " + pot.getValue() + " !");
				} else {
					LOGGER.info(winners + " remporte " + pot.getValue() + " !");
				}

				for (Player p : winnersList) {
					p.setFunds(p.getFunds() + (int) Math.floor(pot.getValue() / winnersList.size()));
				}
			}

		}

		// supprime les personnes sans fonds
		gameState.getPlayers().removeIf(p -> p.getFunds() <= 0);
	}

	private void initRound(GameState gs) {
		initDeck(gs);
		gs.getBoard().getCommunityCards().clear();
		dealCardsToPlayer(gs);
		gs.setDealer(findDealer(gs));
		LOGGER.info("New dealer is " + gs.getDealer().getName());
		betBlinds(gs);
		gs.setCurrentPhase(PhaseEnum.PRE_FLOP);
		gs.setCurrentRound(gs.getCurrentRound() + 1);
		initPhase(gs);
	}

	private void initPhase(GameState gs) {

		for (Player player : gs.getPlayersInGame()) {
			player.setHasPlayed(false);
		}
		switch (gs.getCurrentPhase()) {
		case PRE_FLOP:
			gs.setCurrentPlayer(findFirstPlayer(gs));
			LOGGER.info("New first player is " + gs.getCurrentPlayer().getName());
			break;
		case FLOP:
			gs.setCurrentPlayer(findFirstPlayer(gs));
			LOGGER.info("New first player is " + gs.getCurrentPlayer().getName());
			dealCardsOnBoard(gs);
			dealCardsOnBoard(gs);
			dealCardsOnBoard(gs);
			break;
		case TURN:
			gs.setCurrentPlayer(findFirstPlayer(gs));
			LOGGER.info("New first player is " + gs.getCurrentPlayer().getName());
			dealCardsOnBoard(gs);
			break;
		case RIVER:
			gs.setCurrentPlayer(findFirstPlayer(gs));
			LOGGER.info("New first player is " + gs.getCurrentPlayer().getName());
			dealCardsOnBoard(gs);
			break;
		default:
		}
	}

	private PhaseEnum getNextPhaseEnum(PhaseEnum phase) {
		PhaseEnum[] enumArr = phase.values();

		if (!phase.equals(PhaseEnum.RIVER)) {
			return enumArr[(phase.ordinal() + 1)];
		}
		return null;
	}

	private void betBlinds(GameState gameState) {
		// déposer les blinds
		int indexOfSmallBlind = (gameState.getPlayers().indexOf(gameState.getDealer()) + 1)
				% gameState.getPlayers().size();
		Player smallBlind = gameState.getPlayers().get(indexOfSmallBlind);
		smallBlind.setBet(gameState.getSmallBlind());
		smallBlind.setFunds(smallBlind.getFunds() - smallBlind.getBet());
		LOGGER.info("New smallBlind is " + smallBlind.getName());

		int indexOfBigBlind = (gameState.getPlayers().indexOf(gameState.getDealer()) + 2)
				% gameState.getPlayers().size();
		Player bigBlind = gameState.getPlayers().get(indexOfBigBlind);
		bigBlind.setBet(gameState.getBigBlind());
		bigBlind.setFunds(bigBlind.getFunds() - bigBlind.getBet());
		LOGGER.info("New bigBlind is " + bigBlind.getName());
	}

	private Player searchPlayerById(GameState gs, int playerId) {
		for (Player player : gs.getPlayers()) {
			if (player.getId() == playerId) {
				return player;
			}
		}
		return null;
	}

	public GameDTO gameStateToGameInfo(GameState gs, Integer playerId) {
		ArrayList<Card> cardsOnTable = gs.getBoard().getCommunityCards();
		int totalBets = computePot(gs);

		// pour chaque joueurs a la table on ajoute les informations publiques
				ArrayList<PlayerDTO> playerInfos = new ArrayList<>();
				for (Player p : gs.getPlayers()) {
					boolean playerStillInRound = isPlayerStillInRound(p, gs);
					PlayerDTO playerInfo = new PlayerDTO();
					playerInfo.setName(p.getName());
					playerInfo.setBet(p.getBet());
					playerInfo.setFunds(p.getFunds());
					playerInfo.setStillInRound(playerStillInRound);
					playerInfo.setHasPlayed(p.getHasPlayed());
					playerInfo.setPlayerId(p.getId());
					playerInfos.add(playerInfo);
				}
				
				if (playerId != null) {
					Player player = searchPlayerById(gs, playerId);
					GameDTO game =  new GameDTO();
					game.setGameId(gs.getId());
					game.setName(player.getName());
					game.setPlayerHand(player.getPrivateHand());
					game.setCardsOnTable(cardsOnTable);
					game.setTotalBets(totalBets);
					game.setPlayerInfo(playerInfos);
					game.setPhase(gs.getCurrentPhase());
					game.setStatus(gs.getStatus());
					game.setCurrentRound(gs.getCurrentRound());
					
					return game;
				} else {
					GameDTO game =  new GameDTO();
					game.setGameId(gs.getId());
					game.setName(null);
					game.setPlayerHand(new ArrayList<>());
					game.setCardsOnTable(cardsOnTable);
					game.setTotalBets(totalBets);
					game.setPlayerInfo(playerInfos);
					game.setPhase(gs.getCurrentPhase());
					game.setStatus(gs.getStatus());
					game.setCurrentRound(gs.getCurrentRound());
					return game;
				}

	}
	
	public GameDTO gameStateToGameInfoWithoutId(GameState gs, Integer playerId) {
		ArrayList<Card> cardsOnTable = gs.getBoard().getCommunityCards();
		int totalBets = computePot(gs);
		
		// pour chaque joueurs a la table on ajoute les informations publiques
		ArrayList<PlayerDTO> playerInfos = new ArrayList<>();
		for (Player p : gs.getPlayers()) {
			boolean playerStillInRound = isPlayerStillInRound(p, gs);
			PlayerDTO playerInfo = new PlayerDTO();
			playerInfo.setName(p.getName());
			playerInfo.setBet(p.getBet());
			playerInfo.setFunds(p.getFunds());
			playerInfo.setStillInRound(playerStillInRound);
			playerInfo.setHasPlayed(p.getHasPlayed());
			playerInfos.add(playerInfo);
		}
		
		if (playerId != null) {
			Player player = searchPlayerById(gs, playerId);
			GameDTO game =  new GameDTO();
			game.setGameId(gs.getId());
			game.setName(player.getName());
			game.setPlayerHand(player.getPrivateHand());
			game.setCardsOnTable(cardsOnTable);
			game.setTotalBets(totalBets);
			game.setPlayerInfo(playerInfos);
			game.setPhase(gs.getCurrentPhase());
			game.setStatus(gs.getStatus());
			game.setCurrentRound(gs.getCurrentRound());
			if(playerId == gs.getCurrentPlayer().getId()) {
				game.setMyTurn(true);
			}
			
			return game;
		} else {
			GameDTO game =  new GameDTO();
			game.setGameId(gs.getId());
			game.setName(null);
			game.setPlayerHand(new ArrayList<>());
			game.setCardsOnTable(cardsOnTable);
			game.setTotalBets(totalBets);
			game.setPlayerInfo(playerInfos);
			game.setPhase(gs.getCurrentPhase());
			game.setStatus(gs.getStatus());
			game.setCurrentRound(gs.getCurrentRound());
			return game;
		}
		
	}

	private boolean isPlayerStillInRound(Player player, GameState gameState) {
		return gameState.getPlayersInGame().contains(player);
	}

	// TODO faire un testU
	private Player nextPlayer(GameState gs) {
		// trouver le prochaine joueur à jouer ou null si le tour est terminé

		Player player = gs.getCurrentPlayer();
		boolean maxBet;
		do {
			player = gs.getPlayers().get((gs.getPlayers().indexOf(player) + 1) % gs.getPlayers().size());

			maxBet = player.getBet() == getMaxBet(gs);

		} while (!
		// exit search when
		(player == gs.getCurrentPlayer()
				|| (isPlayerStillInRound(player, gs) && player.getFunds() > 0) && (!maxBet || !player.getHasPlayed())));

		if (player != gs.getCurrentPlayer()) {
			return player;
		} else {
			return null;
		}

	}

	private int generateGameId(List<GameState> gameStateList) {
		return generateGameId(gameStateList, new Random());

	}

	private int generateGameId(List<GameState> gameStateList, Random randomNumber) {
		int maxNumber = Integer.MAX_VALUE;
		int randomID = randomNumber.nextInt(maxNumber) + 1;
		while (verifyRandomGameIdUniqueness(gameStateList, randomID) == false) {
			randomID = generateGameId(gameStateList, randomNumber);
		}
		return randomID;
	}

	private int generatePlayerId(GameState gameState) {
		return generatePlayerId(gameState, new Random());

	}

	private int generatePlayerId(GameState gameState, Random randomNumber) {
		int maxNumber = Integer.MAX_VALUE;
		int randomID = randomNumber.nextInt(maxNumber) + 1;
		while (verifyRandomPlayerIdUniqueness(gameState.getPlayers(), randomID) == false) {
			randomID = generatePlayerId(gameState, randomNumber);
		}
		return randomID;
	}

	private boolean verifyRandomGameIdUniqueness(List<GameState> gameStateList, int randomID) {

		for (GameState gameState : gameStateList) {
			if (gameState.getId() == randomID) {
				return false;
			}
		}
		return true;

	}

	private boolean verifyRandomPlayerIdUniqueness(List<Player> players, int randomID) {

		for (Player p : players) {
			if (p.getId() == randomID) {
				return false;
			}
		}
		return true;

	}

	private int getMaxBet(GameState gameState) {
		int maxBet = Integer.MIN_VALUE;
		for (Player p : gameState.getPlayers()) {
			if (p.getBet() > maxBet) {
				maxBet = p.getBet();
			}
		}
		return maxBet;
	}

	public GameState createGame(int nbOfPlayer) {
		GameState gs = new GameState(nbOfPlayer);
		gs.setId(generateGameId(gameHolderService.getGameStateList()));
		gameHolderService.getGameStateList().add(gs);
		LOGGER.info("The game has been created successfully");
		return gs;
	}

	// récupérer une partie avec l'id donner
	// ajouter le joueur à cette partie
	public PlayerDTO addPlayerToSpecificGame(int id, String playerName) {
		GameState gs = gameHolderService.accessSpecificGameState(id);
		Player p = createPlayer(gs, playerName);
		gs.getPlayers().add(p);
		gs.getPlayersInGame().add(p);
		launchGameWhenReady(gs);
		for (PlayerDTO player : gameStateToGameInfo(gs, p.getId()).getPlayerInfo()) {
			if (player.getName().equals(playerName)) {
				LOGGER.info(player.getName() + " has joined the game");
				return player;
			}
		}
		throw new RuntimeException("This player does not exist !");
	}

	private void launchGameWhenReady(GameState gs) {
		if (gs.getPlayers().size() == gs.getNbOfPlayer()) {
			gs.setStatus(GameStateStatusEnum.IN_PROGRESS);
			initRound(gs);
		}
	}

	public GameDTO getGame(int gameId, Integer playerId) {
		GameState gs = gameHolderService.accessSpecificGameState(gameId);
		return gameStateToGameInfoWithoutId(gs, playerId);
	}

	public void reactToPlayerAction(int gameId, Integer playerId, ActionEnum playerAction) {
		GameState gs = gameHolderService.accessSpecificGameState(gameId);
		if (!GameStateStatusEnum.IN_PROGRESS.equals(gs.getStatus())) {
			throw new RuntimeException("This game is currently unavailable ");
		}
		if (gs.getCurrentPlayer().getId() != playerId) {
			throw new RuntimeException("It is not your turn to play !");
		}

		if (gs.getCurrentPlayer().getFunds() > 0) {
			applyAction(playerAction, gs);
		} else {
			throw new RuntimeException("Vous n'avez plus de fonds !");
		}

		boolean endOfRound = false;
		if (!isRoundEarlyEnded(gs)) {
			Player nextPlayer = nextPlayer(gs);
			if (nextPlayer != null) {

				gs.setCurrentPlayer(nextPlayer);
			} else {
				// pas de prochain player, la phase est terminée
				// mise en place de la prochaine phase ou fin du round
				if (gs.getCurrentPhase().equals(PhaseEnum.RIVER) || nbActivePlayer(gs) < 2) {
					endOfRound = true;
				} else {
					gs.setCurrentPhase(getNextPhaseEnum(gs.getCurrentPhase()));
					initPhase(gs);
				}

			}
		} else {
			endOfRound = true;
		}

		if (endOfRound) {
			while (!PhaseEnum.RIVER.equals(gs.getCurrentPhase())) {
				gs.setCurrentPhase(getNextPhaseEnum(gs.getCurrentPhase()));
				initPhase(gs);
			}
			status(gs);
			// test de fin de partie
			// si terminer set statusEnum to ended sinon lance un nouveau round
			if (gameOver(gs)) {
				gs.setStatus(GameStateStatusEnum.ENDED);
			} else {
				initRound(gs);
			}

		}
	}

	private int nbActivePlayer(GameState gs) {
		int nbActivePlayer = 0;
		for (Player player : gs.getPlayersInGame()) {
			if (player.getFunds() > 0) {
				nbActivePlayer += 1;
			}
		}
		return nbActivePlayer;
	}

	private void applyAction(ActionEnum playerAction, GameState gs) {
		// ATTENTION si le joueur mise la totalité de ses fonds il reste dans le jeu
		// quand bien meme il ne peux plus surenchérir
		int maxBet = getMaxBet(gs);
		if (ActionEnum.FOLLOW.equals(playerAction)) {
			// TODO un joueur ne peux pas miser plus que ses fonds
			// aligner le bet
			int newFunds;
			if (maxBet <= gs.getCurrentPlayer().getFunds() + gs.getCurrentPlayer().getBet()) {
				newFunds = gs.getCurrentPlayer().getFunds() - ((getMaxBet(gs) - gs.getCurrentPlayer().getBet()));
				gs.getCurrentPlayer().setFunds(newFunds);
				gs.getCurrentPlayer().setBet(maxBet);
			} else {
				// set les nouveau bet à la totalité des fonds du joueur
				newFunds = 0;
				gs.getCurrentPlayer().setBet(gs.getCurrentPlayer().getBet() + gs.getCurrentPlayer().getFunds());
				gs.getCurrentPlayer().setFunds(newFunds);
			}

		} else if (ActionEnum.OVERBET.equals(playerAction)) {
			// un joueur ne peux pas miser plus que ses fonds
			// bet maxbet * 2
			int newBet;
			int newFunds;
			if (maxBet + maxBet <= gs.getCurrentPlayer().getFunds() + gs.getCurrentPlayer().getBet()) {
				newBet = maxBet + maxBet;
				newFunds = gs.getCurrentPlayer().getFunds() + gs.getCurrentPlayer().getBet() - newBet;
				gs.getCurrentPlayer().setFunds(newFunds);
				gs.getCurrentPlayer().setBet(newBet);
			} else {
				// tapis
				newBet = gs.getCurrentPlayer().getBet() + gs.getCurrentPlayer().getFunds();
				gs.getCurrentPlayer().setBet(newBet);
				gs.getCurrentPlayer().setFunds(0);

			}

		} else if (ActionEnum.FOLD.equals(playerAction)) {
			// sortir de la liste des joueurs actif
			gs.getPlayersInGame().remove(gs.getCurrentPlayer());
		}
		gs.getCurrentPlayer().setHasPlayed(true);
	}

	private Player createPlayer(GameState gs, String name) {
		Player player = new Player(generatePlayerId(gs), name);
		return player;
	}

	private boolean gameOver(GameState gameState) {
		return gameState.getPlayers().size() == 1;
	}

}
