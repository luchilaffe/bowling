package com.jobsity.bowling.game.model.states;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobsity.bowling.game.model.Frame;

public abstract class FrameState {
	
	@Autowired
	Frame frame;

	FrameState(Frame frame){
		this.frame = frame;
	}
	
	public abstract List<Integer> getValues();

}
