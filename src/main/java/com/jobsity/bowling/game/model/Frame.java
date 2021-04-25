package com.jobsity.bowling.game.model;

import java.util.ArrayList;
import java.util.List;

import com.jobsity.bowling.game.model.states.FrameState;

public class Frame {
	
	private FrameState state;
	private List<String> play;
	
	public Frame(List<String> subFrame) {
		play = new ArrayList<>();
		subFrame.stream().forEach( (p) -> { play.add(p); } );
	}
	
}
