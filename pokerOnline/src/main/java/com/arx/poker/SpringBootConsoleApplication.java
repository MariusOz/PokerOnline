package com.arx.poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arx.poker.service.GameService;

@SpringBootApplication
public class SpringBootConsoleApplication {

	@Autowired
	private GameService gameService;

	private static Logger LOG = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE PokerOnline APPLICATION");
		SpringApplication.run(SpringBootConsoleApplication.class, args);
	}

}
