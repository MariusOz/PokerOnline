package com.arx.poker;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arx.poker.model.Card;
import com.arx.poker.model.GameState;
import com.arx.poker.model.Player;
import com.arx.poker.service.GameService;

@SpringBootTest
public class GameTest {
	@Autowired
	private GameService gameService;
	
	@Test
	public void should_pick_random_first_player() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
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
	public void should_deal_cards_to_players() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
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
		

		List<Card> intersection = gs.getDeck().stream()
		  .distinct()
		  .filter(p1.getPrivateHand()::contains)
		  .collect(Collectors.toList());
		
		Assertions.assertEquals(0, intersection.size());
	}
	
//	@Test
//	public void should_set_next_player() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
//	{
//		GameState gs = new GameState();
//		gameService.initDeck(gs);
//		Player p1 = new Player("player1");
//		Player p2 = new Player("player2");
//		Player p3 = new Player("player3");
//		Player p4 = new Player("player4");
//		gameService.registerPlayer(p1, gs);
//		gameService.registerPlayer(p2, gs);
//		gameService.registerPlayer(p3, gs);
//		gameService.registerPlayer(p4, gs);
//
//		gs.getPlayersInGame().addAll(gs.getPlayers());
//		gs.setDealer(p1);
//		gameService.betBlinds(gs);
//		gs.setCurrentPlayer(p3);
//		
//		Player nextPlayer= gameService.nextPlayer(gs);
//		Assertions.assertEquals(p4.getName(),nextPlayer.getName());
//		
//		//p4 suit
//		p4.setBet(2);
//		gs.setCurrentPlayer(p4);
//		
//		nextPlayer= gameService.nextPlayer(gs);
//		Assertions.assertEquals(p1.getName(),nextPlayer.getName());
//		
//		//p1 se couche
//		gs.getPlayersInGame().remove(p1);
//		gs.setCurrentPlayer(p1);
//		
//		nextPlayer= gameService.nextPlayer(gs);
//		Assertions.assertEquals(p2.getName(),nextPlayer.getName());
//		
//		//p2 relance
//		p2.setBet(4);
//		gs.setCurrentPlayer(p2);
//		
//		nextPlayer= gameService.nextPlayer(gs);
//		Assertions.assertEquals(p3.getName(),nextPlayer.getName());
//		
//		//p3 suit
//		p3.setBet(4);
//		gs.setCurrentPlayer(p3);
//		
//		nextPlayer= gameService.nextPlayer(gs);
//		Assertions.assertEquals(p4.getName(),nextPlayer.getName());
//		
//		//p4 suit
//		p4.setBet(4);
//		gs.setCurrentPlayer(p4);
//		
//		nextPlayer= gameService.nextPlayer(gs);
//		Assertions.assertNull(nextPlayer);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
