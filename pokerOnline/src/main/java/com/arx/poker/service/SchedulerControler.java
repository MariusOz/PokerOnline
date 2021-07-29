package com.arx.poker.service;

import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.arx.poker.model.GameState;
import com.arx.poker.model.Player;

@Component
public class SchedulerControler {

	private int turnMaxDelaySecond = 30;

	@Autowired
	private GameHolderService games;

	@Autowired
	private GameService gameService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerControler.class);

	@Scheduled(fixedRate = 5000)
	public void abortAllLongTurn() {

		LOGGER.info("début du scheduler");
		for (GameState gs : games.getGameStateList()) {
			abortLongTurn(gs);
		}
	}

	private void abortLongTurn(GameState gs) {

		// comment obtenir la date du début du tour du current player ?
		LocalDateTime dateDebutActionJoueur = gs.getBeginPlayerAction();
		if (dateDebutActionJoueur != null && GameStateStatusEnum.IN_PROGRESS.equals(gs.getStatus())) {
			LocalDateTime dateNow = LocalDateTime.now();
			Player currentPlayer = gs.getCurrentPlayer();
			LOGGER.info("il est : " + dateNow + ", c'est au tour de " + gs.getCurrentPlayer().getName() + " et son tour a commencé à " + dateDebutActionJoueur);

			if (dateDebutActionJoueur.plusSeconds(turnMaxDelaySecond).isBefore(dateNow)) {
				gameService.reactToPlayerAction(gs.getId(), currentPlayer.getId(), ActionEnum.FOLD);
				LOGGER.info(currentPlayer.getName()
						+ " à été inactif trop longtemps, il/elle ne participe plus a ce tour de jeu");
				

			}
		}

	}
}
