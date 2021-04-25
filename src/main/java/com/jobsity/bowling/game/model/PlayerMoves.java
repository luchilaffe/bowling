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
		List<Frame> response = new ArrayList<>();

		int currentIndex = 0;
		for (int i = 0; i < 10; i++) {
			List<String> subList;
			if ((frames.get(currentIndex).equals("X") || frames.get(currentIndex).equals("10")) && i == 10 ) {
				subList = frames.subList(currentIndex, currentIndex+3);
				System.out.println("No.: " + i 
						+ ". INFO: Strike en la 10ma. " + subList.toString());
				currentIndex = currentIndex + 3;
				
			} else if (frames.get(currentIndex).equals("X") || frames.get(currentIndex).equals("10")) {
				subList = frames.subList(currentIndex, currentIndex+1);
				System.out.println("No.: " + i 
						+ ". INFO: Strike. " + subList.toString());
				currentIndex = currentIndex + 1;
				
			} else if (Integer.parseInt(frames.get(currentIndex)) 
					+ Integer.parseInt(frames.get(currentIndex +1)) 
					== 10) {
				subList = frames.subList(currentIndex, currentIndex+2);
				System.out.println("No.: " + i 
						+ ". INFO: Spare. " + subList.toString());
				currentIndex = currentIndex + 2;
				
			} else {
				subList = frames.subList(currentIndex, currentIndex+2);
				System.out.println("No.: " + i 
						+ ". INFO: Nothing special. " + subList.toString());
				currentIndex = currentIndex + 2;
			}
			/* TODO: validar */
			// Letra F
			// Que no sobren tiros
			// QUe no falten tiros
			// que los datos sean válidos ( 0-10 + X / F )
			response.add(new Frame(subList));
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
