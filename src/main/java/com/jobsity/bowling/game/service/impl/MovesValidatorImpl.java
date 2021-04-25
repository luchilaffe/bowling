package com.jobsity.bowling.game.service.impl;

import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.service.MovesValidator;

@Service
public class MovesValidatorImpl implements MovesValidator {

	@Override
	public Boolean validateMove(String move) {
		return (// F
				move.equals("F")
				// X
				|| move.equals("X")
				// /
				|| move.equals("/")
				// 0 a 10
				|| ( Integer.parseInt(move) >= 0 && Integer.parseInt(move) <= 10 ));
	}

}
