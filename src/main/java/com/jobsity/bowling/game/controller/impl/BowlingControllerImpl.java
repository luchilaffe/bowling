package com.jobsity.bowling.game.controller.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jobsity.bowling.game.controller.BowlingController;
import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.FileParser;

@Controller
public class BowlingControllerImpl implements BowlingController {

	@Autowired
	private FileParser parser;
	
	@Override
	public List<PlayerMoves> parseFile(InputStream stream) throws Throwable {
		try (Reader reader = new InputStreamReader(stream)) {
			BufferedReader bufferedReader = new BufferedReader(reader);
			List<PlayerMoves> playerMoves = parser.parse(bufferedReader);
			return playerMoves
					.stream()
					//.map(player -> this.factory.create(player.getName(), player.getFrames()))
					.collect(Collectors.toList());
		} catch (Throwable e) {
			throw e;
		}
	}

	
}
