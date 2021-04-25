package com.jobsity.bowling.game.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerMoves{

	String name;
	List<Frame> frames = new ArrayList<>();
	
	public PlayerMoves(String name, List<String> frames) {
		super();
		this.name = name;
		this.frames = this.convertFrames(frames);
	}
	
	private List<Frame> convertFrames(List<String> frames){
		/* TODO: Convertir los Frames */
		int currentIndex = 0;
		List<Frame> response = null;
		for (int i = 0; i < 10; i++) {
			if ((frames.get(currentIndex).equals("X") || frames.get(currentIndex).equals("10")) && i == 10 ) {
				System.out.println("No.: " + i + ". INFO: Strike en la 10ma. " 
						+ frames.subList(currentIndex, currentIndex+3).toString());
				// TODO: Frame currentFrame = new Frame();
				currentIndex = currentIndex + 3;
				
			} else if (frames.get(currentIndex).equals("X") || frames.get(currentIndex).equals("10")) {
				System.out.println("No.: " + i + ". INFO: Strike. " 
						+ frames.subList(currentIndex, currentIndex+1).toString());
				currentIndex = currentIndex + 1;
				
			} else if (Integer.parseInt(frames.get(currentIndex)) 
					+ Integer.parseInt(frames.get(currentIndex +1)) 
					== 10) {
				System.out.println("No.: " + i + ". INFO: Spare. " 
						+ frames.subList(currentIndex, currentIndex+2).toString());
				currentIndex = currentIndex + 2;
				
			} else {
				System.out.println("No.: " + i + ". INFO: Nothing special. " 
						+ frames.subList(currentIndex, currentIndex+2).toString());
				currentIndex = currentIndex + 2;
			}
			/* TODO: validar */
		}
		
		return response;
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
