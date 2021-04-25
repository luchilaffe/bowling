package com.jobsity.bowling.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jobsity.bowling.game.controller.cmd.CommandLineInputController;

@SpringBootApplication
public class BowlingServiceApplication implements CommandLineRunner {

	@Autowired
	CommandLineInputController cmd;
		
	public static void main(String[] args) {
		SpringApplication.run(BowlingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cmd.run(args[0]);
	}

}
