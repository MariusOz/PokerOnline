package com.arx.poker.model;

public class PlayerDTOWithoutPlayerID {

	// nom du joueur
		private final String name;
		// argent mis en jeux
		private final int bet;
		// argent restant
		private final int funds;
		// participe a la manche
		private final boolean isStillInRound;
		
		private boolean hasPlayed;
		
		
		public PlayerDTOWithoutPlayerID(String name, int bet, int bets, boolean isStillInRound, boolean hasPlayed) {
			super();
			this.name = name;
			this.bet = bet;
			this.funds = bets;
			this.isStillInRound = isStillInRound;
			this.hasPlayed = hasPlayed;
		}

		public String getName() {
			return name;
		}

		public int getBet() {
			return bet;
		}

		public int getFunds() {
			return funds;
		}

		public boolean isStillInRound() {
			return isStillInRound;
		}
		
		public boolean hasPlayed() {
			return hasPlayed;
		}
}
