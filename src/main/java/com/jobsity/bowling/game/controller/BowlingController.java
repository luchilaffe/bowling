package com.jobsity.bowling.game.controller;

import java.io.InputStream;
import java.util.List;

import com.jobsity.bowling.game.model.PlayerMoves;

public interface BowlingController {
	
	List<PlayerMoves> parseFile(InputStream stream) throws Throwable;
	
	List<PlayerMoves> calculateScore(List<PlayerMoves> playerMoves);
	
}
