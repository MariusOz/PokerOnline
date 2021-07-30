package com.arx.poker;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arx.poker.model.Card;
import com.arx.poker.model.GameState;
import com.arx.poker.model.GameStateStatusEnum;
import com.arx.poker.model.PhaseEnum;
import com.arx.poker.model.Player;
import com.arx.poker.service.ActionEnum;
import com.arx.poker.service.GameHolderService;
import com.arx.poker.service.GameService;

@SpringBootTest
public class GameTest {
	@Autowired
	private GameService gameService;
	@Autowired
	private GameHolderService gameHolderService;

	@Test
	public void should_pick_random_first_player()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		GameState gs = new GameState(2);

		Player p1 = new Player(256, "player1");
		Player p2 = new Player(3654, "player2");
		Player p3 = new Player(1258, "player3");

		gameService.registerPlayer(p1, gs);
		gameService.registerPlayer(p2, gs);
		gameService.registerPlayer(p3, gs);

		gs.setDealer(gameService.findDealer(gs));
		int indexFP1 = gs.getPlayers().indexOf(gs.getDealer());

		gs.setDealer(gameService.findDealer(gs));
		int indexFP2 = gs.getPlayers().indexOf(gs.getDealer());

		gs.setDealer(gameService.findDealer(gs));
		int indexFP3 = gs.getPlayers().indexOf(gs.getDealer());

		gs.setDealer(gameService.findDealer(gs));
		int indexFP4 = gs.getPlayers().indexOf(gs.getDealer());

		// The first player is random for the first , the
		Assertions.assertFalse(indexFP1 == indexFP2);
		Assertions.assertFalse(indexFP2 == indexFP3);
		Assertions.assertTrue(indexFP1 == indexFP4);

	}

	@Test
	public void should_deal_cards_to_players()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		GameState gs = new GameState(2);

		gameService.initDeck(gs);

		Player p1 = new Player(8547, "player1");
		Player p2 = new Player(6589, "player2");
		Player p3 = new Player(9856, "player3");

		gameService.registerPlayer(p1, gs);
		gameService.registerPlayer(p2, gs);
		gameService.registerPlayer(p3, gs);

		gs.getPlayersInGame().addAll(gs.getPlayers());

		int deckSize = gs.getDeck().size();

		gameService.dealCardsToPlayer(gs);

		int deckSizeAfterCardDeal = gs.getDeck().size();

		Assertions.assertEquals(52, deckSize);
		Assertions.assertEquals(46, deckSizeAfterCardDeal);
		Assertions.assertEquals(2, p1.getPrivateHand().size());
		Assertions.assertEquals(2, p2.getPrivateHand().size());
		Assertions.assertEquals(2, p3.getPrivateHand().size());

		List<Card> intersection = gs.getDeck().stream().distinct().filter(p1.getPrivateHand()::contains)
				.collect(Collectors.toList());

		Assertions.assertEquals(0, intersection.size());
	}

	@Test
	public void test_fold_should_end_round() {
		int id = gameService.createGame(2).getId();

		int playerId1 = gameService.addPlayerToSpecificGame(id, "test1").getPlayerId();
		int playerId2 = gameService.addPlayerToSpecificGame(id, "test2").getPlayerId();

		Assertions.assertEquals(GameStateStatusEnum.IN_PROGRESS, gameService.getGame(id, playerId1).getStatus());
		Assertions.assertEquals(PhaseEnum.PRE_FLOP, gameService.getGame(id, playerId1).getPhase());
		Assertions.assertEquals(1, gameService.getGame(id, playerId1).getCurrentRound());

		GameState gs = gameHolderService.accessSpecificGameState(id);
		gameService.reactToPlayerAction(id, gs.getCurrentPlayer().getId(), ActionEnum.FOLD);

		Assertions.assertEquals(GameStateStatusEnum.IN_PROGRESS, gameService.getGame(id, playerId1).getStatus());
		Assertions.assertEquals(PhaseEnum.PRE_FLOP, gameService.getGame(id, playerId1).getPhase());
		Assertions.assertEquals(2, gameService.getGame(id, playerId1).getCurrentRound());
		
		gameService.reactToPlayerAction(id, gs.getCurrentPlayer().getId(), ActionEnum.FOLD);
		
		Assertions.assertEquals(GameStateStatusEnum.IN_PROGRESS, gameService.getGame(id, playerId1).getStatus());
		Assertions.assertEquals(PhaseEnum.PRE_FLOP, gameService.getGame(id, playerId1).getPhase());
		Assertions.assertEquals(3, gameService.getGame(id, playerId1).getCurrentRound());

		for (int i = 4; i <= 50; i++) {
			gameService.reactToPlayerAction(id, gs.getCurrentPlayer().getId(), ActionEnum.FOLD);
			Assertions.assertEquals(GameStateStatusEnum.IN_PROGRESS, gameService.getGame(id, playerId1).getStatus());
			Assertions.assertEquals(i, gameService.getGame(id, playerId1).getCurrentRound());
		}

	}

}
