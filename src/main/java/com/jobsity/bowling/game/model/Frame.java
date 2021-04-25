package com.jobsity.bowling.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jobsity.bowling.game.model.states.FrameState;
import com.jobsity.bowling.game.model.states.Spare;
import com.jobsity.bowling.game.model.states.Strike;
import com.jobsity.bowling.game.model.states.StrikeTenth;

public class Frame {
	
	private FrameState state;
	private List<String> play;
	
	public Frame(List<String> subFrame) {
		play = new ArrayList<>();
		this.setState(subFrame);
		subFrame.stream().forEach( (p) -> { play.add(p); } );
	}
	
	private void setState(List<String> subFrame) {
		this.state = (subFrame.size() == 3) 
				? new StrikeTenth(this) 
				: (subFrame.size() == 1) 
					? new Strike(this) 
					: ( subFrame.size() == 2 
						&& !hasFault(subFrame)
						&& (getSumValues(subFrame) == 10 ) )
						? new Spare(this)
						: null;
	}
	
	private Integer getSumValues(List<String> subFrame) {
		return Integer.parseInt(subFrame.get(0)) + Integer.parseInt(subFrame.get(1));
	}
	
	public FrameState getState() {
		return this.state;
	}
	
	private Boolean hasFault(List<String> subFrame) {
		Optional<String> response = subFrame.stream()
				.filter( p -> p.equals("F") )
				.findFirst();
		return response.isEmpty() ? false : true;
	}
}
