package com.jobsity.bowling.game.service;

import java.util.List;

import com.jobsity.bowling.game.model.Frame;

public interface MovesValidator {
	
	List<Frame> validateFrames(List<String> frames);
	
	Boolean validateMoveValue(String move);

}
