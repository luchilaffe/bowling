package com.jobsity.bowling.game.model;

import com.jobsity.bowling.game.model.states.FrameState;

public interface Frame {
	
	FrameState getState();
	
	Integer getScore();
	
	Integer calculateScore(Frame next, Frame second);
			
	String playsToString();
	
	Integer getNextValue(Frame next);
	
	Integer getFirstValue();

	Integer getSecondValue();
	
	Integer getThirdValue();
	
	Integer getSumValue(String a, String b);
	
	Integer getNextTwoValues(Frame next, Frame second);
	
	Integer integerValue(String value);
	
}
