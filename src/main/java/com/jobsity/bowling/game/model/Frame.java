package com.jobsity.bowling.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jobsity.bowling.game.model.states.FrameState;
import com.jobsity.bowling.game.model.states.Normal;
import com.jobsity.bowling.game.model.states.Spare;
import com.jobsity.bowling.game.model.states.Strike;
import com.jobsity.bowling.game.model.states.StrikeTenth;

public class Frame {
	
	private FrameState state;
	private List<String> play;
	private Integer score;
	private Integer order;
	
	public Frame(List<String> subFrame, Integer order) {
		play = new ArrayList<>();
		this.setState(subFrame);
		subFrame.stream().forEach( (p) -> { play.add(p); } );
		this.score = 0;
		this.order = order;
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
						: new Normal(this);
	}
	
	private Integer getSumValues(List<String> subFrame) {
		return Integer.parseInt(subFrame.get(0)) + Integer.parseInt(subFrame.get(1));
	}

	public FrameState getState() {
		return this.state;
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	private Boolean hasFault(List<String> subFrame) {
		Optional<String> response = subFrame.stream()
				.filter( p -> p.equals("F") )
				.findFirst();
		return response.isEmpty() ? false : true;
	}
	
	public Integer calculateScore(Frame next, Frame second) {
		if (this.state.getClass().equals(StrikeTenth.class)) {
			this.score = 10 + this.getSecondValue() + this.getThirdValue();
		} else if (this.state.getClass().equals(Strike.class)) {
			this.score = 10 + this.getNextTwoValues(next, second);
		} else if (this.state.getClass().equals(Spare.class)) {
			this.score = 10 + this.getNextValue(next);
		} else {
			this.score = getSumValue(play.get(0), play.get(1));
		}
		return score;
	}
	
	private Integer getSumValue(String a, String b) {
		return integerValue(a) + integerValue(b);
	}
	
	private Integer getNextTwoValues(Frame next, Frame second) {
		if (next.getState().getClass().equals(StrikeTenth.class)) {
			return 10 + next.getSecondValue();
		} else if (next.getState().getClass().equals(Strike.class)) {
			return 10 + next.getNextValue(second);
		} else {
			return next.getFirstValue() + next.getSecondValue();
		}
	}

	private Integer getNextValue(Frame next) {
		if (this.order == 9) {
			return next.getSecondValue();
		}
		return next.getFirstValue();
	}
	
	private Integer getFirstValue() {
		return integerValue(play.get(0));
	}

	private Integer getSecondValue() {
		return integerValue(play.get(1));
	}
	
	private Integer getThirdValue() {
		if (this.order == 9) {
			return integerValue(play.get(2));
		}
		return 0;
	}
	
	private Integer integerValue(String value) {
		if (value.equals("X")) {
			return 10;
		} else if (value.equals("F")) {
			return 0;
		} else if (value.equals("/")) {
			return 10 - integerValue(play.get(0));
		} else if (Integer.parseInt(value) >= 0 && Integer.parseInt(value) <= 10 ) {
			return Integer.parseInt(value);
		} else {
			return 0;
		}
	}
	
}
