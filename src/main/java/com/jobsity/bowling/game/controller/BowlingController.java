package com.jobsity.bowling.game.controller;

import java.io.InputStream;
import java.util.List;

import com.jobsity.bowling.game.model.PlayerMoves;

public interface BowlingController {
	public List<PlayerMoves> parseFile(InputStream stream) throws Throwable;
}
