package com.jobsity.bowling.game.service;

import java.util.List;

import com.jobsity.bowling.game.model.PlayerMoves;

public interface Printer {
	
	void printGame(List<PlayerMoves> playerMoves);

}
