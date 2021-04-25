package com.jobsity.bowling.game.model.states;

import java.util.ArrayList;
import java.util.List;

import com.jobsity.bowling.game.model.Frame;

public class StrikeTenth extends FrameState{
	
	List<Integer> values;
	
	public StrikeTenth(Frame frame) {
		super(frame);
		
	}
	
	@Override
	public List<Integer> getValues() {
		List<Integer> values = new ArrayList<Integer>();
		values.add(10);
		return values;
	}

}
