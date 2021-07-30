package com.arx.poker;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arx.poker.model.Card;
import com.arx.poker.model.ColorEnum;
import com.arx.poker.model.FlushPokerHand;
import com.arx.poker.model.FourOfAKindPokerHand;
import com.arx.poker.model.FullHousePokerHand;
import com.arx.poker.model.HighHandPokerHand;
import com.arx.poker.model.OnePairPokerHand;
import com.arx.poker.model.PokerHand;
import com.arx.poker.model.StraightFlushPokerHand;
import com.arx.poker.model.StraightPokerHand;
import com.arx.poker.model.ThreeOfAKindPokerHand;
import com.arx.poker.model.TwoPairPokerHand;
import com.arx.poker.service.HandService;
import com.arx.poker.service.PokerHandEnum;

@SpringBootTest
public class PokerHandTest {
	
	@Autowired
	private HandService handService;

	@Test
	public void test_poker_hands_comparisson (){
		
		PokerHand pokerHand1 = new StraightFlushPokerHand(14);
		PokerHand pokerHand2 = new StraightFlushPokerHand(9);
		PokerHand pokerHand3 = new FourOfAKindPokerHand(8, 7);
		PokerHand pokerHand4 = new FourOfAKindPokerHand(8, 5);
		PokerHand pokerHand5 = new FullHousePokerHand(12, 4);
		PokerHand pokerHand6 = new FullHousePokerHand(10, 13);
		PokerHand pokerHand7 = new FlushPokerHand(12, 10, 7, 5, 3);
		PokerHand pokerHand8 = new FlushPokerHand(7, 5, 4, 3, 2);
		PokerHand pokerHand9 = new StraightPokerHand(8);
		PokerHand pokerHand10 = new StraightPokerHand(6);
		PokerHand pokerHand11 = new ThreeOfAKindPokerHand(9, 11, 10);
		PokerHand pokerHand12 = new ThreeOfAKindPokerHand(5, 9, 3);
		PokerHand pokerHand13 = new TwoPairPokerHand(8, 6, 13);
		PokerHand pokerHand14 = new TwoPairPokerHand(8, 6, 10);
		PokerHand pokerHand15 = new OnePairPokerHand(11, 14, 10, 6);
		PokerHand pokerHand16 = new OnePairPokerHand(11, 14, 10, 5);
		PokerHand pokerHand17 = new HighHandPokerHand(13, 8, 6, 4, 3);
		PokerHand pokerHand18 = new HighHandPokerHand(13, 8, 6, 4, 2);
		
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand2) > 0);	
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand3) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand5) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand7) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand9) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand11) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand13) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand15) > 0);
		Assertions.assertTrue(pokerHand1.compareTo(pokerHand17) > 0);
		
		Assertions.assertTrue(pokerHand3.compareTo(pokerHand4) > 0);
		Assertions.assertTrue(pokerHand5.compareTo(pokerHand6) > 0);
		Assertions.assertTrue(pokerHand7.compareTo(pokerHand8) > 0);
		Assertions.assertTrue(pokerHand9.compareTo(pokerHand10) > 0);
		Assertions.assertTrue(pokerHand11.compareTo(pokerHand12) > 0);
		Assertions.assertTrue(pokerHand13.compareTo(pokerHand14) > 0);
		Assertions.assertTrue(pokerHand15.compareTo(pokerHand16) > 0);
		Assertions.assertTrue(pokerHand17.compareTo(pokerHand18) > 0);
		
		
		
		Assertions.assertTrue(pokerHand2.compareTo(pokerHand1) < 0);	
		Assertions.assertTrue(pokerHand3.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand5.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand7.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand9.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand11.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand13.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand15.compareTo(pokerHand1) < 0);
		Assertions.assertTrue(pokerHand17.compareTo(pokerHand1) < 0);
		
		Assertions.assertTrue(pokerHand4.compareTo(pokerHand3) < 0);
		Assertions.assertTrue(pokerHand6.compareTo(pokerHand5) < 0);
		Assertions.assertTrue(pokerHand8.compareTo(pokerHand7) < 0);
		Assertions.assertTrue(pokerHand10.compareTo(pokerHand9) < 0);
		Assertions.assertTrue(pokerHand12.compareTo(pokerHand11) < 0);
		Assertions.assertTrue(pokerHand14.compareTo(pokerHand13) < 0);
		Assertions.assertTrue(pokerHand16.compareTo(pokerHand15) < 0);
		Assertions.assertTrue(pokerHand18.compareTo(pokerHand17) < 0);
		
		
		Assertions.assertTrue(pokerHand18.compareTo(pokerHand18) == 0);
		Assertions.assertTrue(pokerHand7.compareTo(pokerHand7) == 0);
		Assertions.assertTrue(pokerHand5.compareTo(pokerHand5) == 0);
		Assertions.assertTrue(pokerHand13.compareTo(pokerHand13) == 0);
		Assertions.assertTrue(pokerHand3.compareTo(pokerHand3) == 0);
		
	}
	
	@Test
	public void test_poker_hand_detection() {
		
		List<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card(14, ColorEnum.CLUB));
		playerHand.add(new Card(7, ColorEnum.CLUB));
		playerHand.add(new Card(3, ColorEnum.CLUB));
		playerHand.add(new Card(5, ColorEnum.CLUB));
		playerHand.add(new Card(11, ColorEnum.CLUB));
		playerHand.add(new Card(6, ColorEnum.CLUB));
		playerHand.add(new Card(2, ColorEnum.CLUB));
		PokerHand ph = handService.findBestCombination(playerHand);
		
		Assertions.assertEquals(PokerHandEnum.FLUSH, ph.getPokerHandEnum());
		Assertions.assertEquals(14, ph.getHighestValue());
		Assertions.assertEquals(11, ph.getSecondHighestValue());
		Assertions.assertEquals(7, ph.getThirdHighestValue());
		Assertions.assertEquals(6, ph.getFourthHighestValue());
		Assertions.assertEquals(5, ph.getFifthHighestValue());		
		
		
		List<Card> playerHand2 = new ArrayList<>();
		playerHand2.add(new Card(14, ColorEnum.CLUB));
		playerHand2.add(new Card(7, ColorEnum.DIAMOND));
		playerHand2.add(new Card(3, ColorEnum.HEART));
		playerHand2.add(new Card(5, ColorEnum.CLUB));
		playerHand2.add(new Card(11, ColorEnum.SPADE));
		playerHand2.add(new Card(6, ColorEnum.CLUB));
		playerHand2.add(new Card(7, ColorEnum.SPADE));
		PokerHand ph2 = handService.findBestCombination(playerHand2);
		
		Assertions.assertEquals(PokerHandEnum.ONE_PAIR, ph2.getPokerHandEnum());
		Assertions.assertEquals(7, ph2.getHighestValue());
		Assertions.assertEquals(14, ph2.getSecondHighestValue());
		Assertions.assertEquals(11, ph2.getThirdHighestValue());
		Assertions.assertEquals(6, ph2.getFourthHighestValue());

		List<Card> playerHand3 = new ArrayList<>();
		playerHand3.add(new Card(14, ColorEnum.SPADE));
		playerHand3.add(new Card(7, ColorEnum.SPADE));
		playerHand3.add(new Card(3, ColorEnum.HEART));
		playerHand3.add(new Card(5, ColorEnum.SPADE));
		playerHand3.add(new Card(11, ColorEnum.SPADE));
		playerHand3.add(new Card(6, ColorEnum.SPADE));
		playerHand3.add(new Card(7, ColorEnum.CLUB));
		PokerHand ph3 = handService.findBestCombination(playerHand3);
		
		Assertions.assertEquals(PokerHandEnum.FLUSH, ph3.getPokerHandEnum());
		Assertions.assertEquals(14, ph3.getHighestValue());
		Assertions.assertEquals(11, ph3.getSecondHighestValue());
		Assertions.assertEquals(7, ph3.getThirdHighestValue());
		Assertions.assertEquals(6, ph3.getFourthHighestValue());
		Assertions.assertEquals(5, ph3.getFifthHighestValue());	
		
		List<Card> playerHand4 = new ArrayList<>();
		playerHand4.add(new Card(14, ColorEnum.SPADE));
		playerHand4.add(new Card(7, ColorEnum.SPADE));
		playerHand4.add(new Card(3, ColorEnum.HEART));
		playerHand4.add(new Card(3, ColorEnum.SPADE));
		playerHand4.add(new Card(7, ColorEnum.DIAMOND));
		playerHand4.add(new Card(6, ColorEnum.SPADE));
		playerHand4.add(new Card(7, ColorEnum.CLUB));
		PokerHand ph4 = handService.findBestCombination(playerHand4);
		
		Assertions.assertEquals(PokerHandEnum.FULL_HOUSE, ph4.getPokerHandEnum());
		Assertions.assertEquals(7, ph4.getHighestValue());
		Assertions.assertEquals(3, ph4.getSecondHighestValue());
		
		
		List<Card> playerHand5 = new ArrayList<>();
		playerHand5.add(new Card(14, ColorEnum.SPADE));
		playerHand5.add(new Card(7, ColorEnum.SPADE));
		playerHand5.add(new Card(7, ColorEnum.HEART));
		playerHand5.add(new Card(3, ColorEnum.DIAMOND));
		playerHand5.add(new Card(7, ColorEnum.DIAMOND));
		playerHand5.add(new Card(3, ColorEnum.HEART));
		playerHand5.add(new Card(7, ColorEnum.CLUB));
		PokerHand ph5 = handService.findBestCombination(playerHand5);
		
		Assertions.assertEquals(PokerHandEnum.FOUR_OF_A_KIND, ph5.getPokerHandEnum());
		Assertions.assertEquals(7, ph5.getHighestValue());
		Assertions.assertEquals(14, ph5.getSecondHighestValue());

		List<Card> playerHand6 = new ArrayList<>();
		playerHand6.add(new Card(14, ColorEnum.SPADE));
		playerHand6.add(new Card(14, ColorEnum.SPADE));
		playerHand6.add(new Card(12, ColorEnum.HEART));
		playerHand6.add(new Card(11, ColorEnum.DIAMOND));
		playerHand6.add(new Card(10, ColorEnum.DIAMOND));
		playerHand6.add(new Card(3, ColorEnum.HEART));
		playerHand6.add(new Card(3, ColorEnum.CLUB));
		PokerHand ph6 = handService.findBestCombination(playerHand6);
		
		Assertions.assertEquals(PokerHandEnum.TWO_PAIR, ph6.getPokerHandEnum());
		Assertions.assertEquals(14, ph6.getHighestValue());
		Assertions.assertEquals(3, ph6.getSecondHighestValue());
		Assertions.assertEquals(12, ph6.getThirdHighestValue());
		
		List<Card> playerHand7 = new ArrayList<>();
		playerHand7.add(new Card(14, ColorEnum.SPADE));
		playerHand7.add(new Card(7, ColorEnum.SPADE));
		playerHand7.add(new Card(2, ColorEnum.HEART));
		playerHand7.add(new Card(3, ColorEnum.SPADE));
		playerHand7.add(new Card(7, ColorEnum.DIAMOND));
		playerHand7.add(new Card(6, ColorEnum.SPADE));
		playerHand7.add(new Card(7, ColorEnum.CLUB));
		PokerHand ph7 = handService.findBestCombination(playerHand7);
		
		Assertions.assertEquals(PokerHandEnum.THREE_OF_A_KIND, ph7.getPokerHandEnum());
		Assertions.assertEquals(7, ph7.getHighestValue());
		Assertions.assertEquals(14, ph7.getSecondHighestValue());
		Assertions.assertEquals(6, ph7.getThirdHighestValue());	
		
		List<Card> playerHand8 = new ArrayList<>();
		playerHand8.add(new Card(14, ColorEnum.SPADE));
		playerHand8.add(new Card(6, ColorEnum.SPADE));
		playerHand8.add(new Card(5, ColorEnum.HEART));
		playerHand8.add(new Card(3, ColorEnum.SPADE));
		playerHand8.add(new Card(7, ColorEnum.DIAMOND));
		playerHand8.add(new Card(2, ColorEnum.SPADE));
		playerHand8.add(new Card(12, ColorEnum.CLUB));
		PokerHand ph8 = handService.findBestCombination(playerHand8);
		
		Assertions.assertEquals(PokerHandEnum.HIGH_HAND, ph8.getPokerHandEnum());
		Assertions.assertEquals(14, ph8.getHighestValue());
		Assertions.assertEquals(12, ph8.getSecondHighestValue());
		Assertions.assertEquals(7, ph8.getThirdHighestValue());	
		Assertions.assertEquals(6, ph8.getFourthHighestValue());	
		Assertions.assertEquals(5, ph8.getFifthHighestValue());			
		
		List<Card> playerHand9 = new ArrayList<>();
		playerHand9.add(new Card(14, ColorEnum.SPADE));
		playerHand9.add(new Card(13, ColorEnum.SPADE));
		playerHand9.add(new Card(12, ColorEnum.HEART));
		playerHand9.add(new Card(11, ColorEnum.SPADE));
		playerHand9.add(new Card(10, ColorEnum.DIAMOND));
		playerHand9.add(new Card(9, ColorEnum.SPADE));
		playerHand9.add(new Card(9, ColorEnum.CLUB));
		PokerHand ph9 = handService.findBestCombination(playerHand9);
		
		Assertions.assertEquals(PokerHandEnum.STRAIGHT, ph9.getPokerHandEnum());
		Assertions.assertEquals(14, ph9.getHighestValue());
	
		List<Card> playerHand10 = new ArrayList<>();
		playerHand10.add(new Card(14, ColorEnum.HEART));
		playerHand10.add(new Card(13, ColorEnum.SPADE));
		playerHand10.add(new Card(12, ColorEnum.SPADE));
		playerHand10.add(new Card(11, ColorEnum.SPADE));
		playerHand10.add(new Card(10, ColorEnum.SPADE));
		playerHand10.add(new Card(9, ColorEnum.SPADE));
		playerHand10.add(new Card(8, ColorEnum.SPADE));
		PokerHand ph10 = handService.findBestCombination(playerHand10);
		
		Assertions.assertEquals(PokerHandEnum.STRAIGHT_FLUSH, ph10.getPokerHandEnum());
		Assertions.assertEquals(13, ph10.getHighestValue());
		
		List<Card> playerHand11 = new ArrayList<>();
		playerHand11.add(new Card(14, ColorEnum.SPADE));
		playerHand11.add(new Card(9, ColorEnum.SPADE));
		playerHand11.add(new Card(7, ColorEnum.SPADE));
		playerHand11.add(new Card(5, ColorEnum.SPADE));
		playerHand11.add(new Card(4, ColorEnum.SPADE));
		playerHand11.add(new Card(3, ColorEnum.SPADE));
		playerHand11.add(new Card(2, ColorEnum.SPADE));
		PokerHand ph11 = handService.findBestCombination(playerHand11);
		
		Assertions.assertEquals(PokerHandEnum.STRAIGHT_FLUSH, ph11.getPokerHandEnum());
		Assertions.assertEquals(5, ph11.getHighestValue());
		
		List<Card> playerHand12 = new ArrayList<>();
		playerHand12.add(new Card(14, ColorEnum.HEART));
		playerHand12.add(new Card(9, ColorEnum.SPADE));
		playerHand12.add(new Card(7, ColorEnum.SPADE));
		playerHand12.add(new Card(5, ColorEnum.HEART));
		playerHand12.add(new Card(4, ColorEnum.HEART));
		playerHand12.add(new Card(3, ColorEnum.HEART));
		playerHand12.add(new Card(2, ColorEnum.HEART));
		PokerHand ph12 = handService.findBestCombination(playerHand12);
		
		Assertions.assertEquals(PokerHandEnum.STRAIGHT_FLUSH, ph12.getPokerHandEnum());
		Assertions.assertEquals(5, ph12.getHighestValue());
		
		
	}
}
