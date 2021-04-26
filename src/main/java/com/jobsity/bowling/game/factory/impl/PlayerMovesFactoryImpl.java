package com.jobsity.bowling.game.factory.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.factory.PlayerMovesFactory;
import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.model.impl.PlayerMovesImpl;
import com.jobsity.bowling.game.service.MovesValidator;

@Service
public class PlayerMovesFactoryImpl implements PlayerMovesFactory {

	@Override
	public PlayerMoves createPlayerMoves(String name, List<String> frames, MovesValidator validator) {
		return new PlayerMovesImpl(name, frames, validator);
	}

}
