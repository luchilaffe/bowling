package com.jobsity.bowling.game.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobsity.bowling.game.service.MovesValidator;

public class PlayerMoves{

	@Autowired
	MovesValidator movesValidator; 
	
	String name;
	List<Frame> frames = new ArrayList<>();
	
	public PlayerMoves(String name, List<String> frames, MovesValidator validator) {
		super();
		this.name = name;
		this.movesValidator = validator;
		this.frames = movesValidator.validateFrames(frames);
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Frame> getFrames() {
		return frames;
	}
	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

}
