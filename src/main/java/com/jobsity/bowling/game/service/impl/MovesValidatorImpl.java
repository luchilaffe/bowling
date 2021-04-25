package com.jobsity.bowling.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.model.Frame;
import com.jobsity.bowling.game.service.MovesValidator;

@Service
public class MovesValidatorImpl implements MovesValidator {

	public List<Frame> validateFrames(List<String> frames){
		List<Frame> response = new ArrayList<>();

		int currentIndex = 0;
		for (int i = 0; i < 10; i++) {
			List<String> subList;
			try {
				if ((frames.get(currentIndex).equals("X") && i == 9 )) {
					subList = frames.subList(currentIndex, currentIndex+3);
					System.out.println("No.: " + i 
							+ ". INFO: Strike en la 10ma. " + subList.toString());
					currentIndex = currentIndex + 3;
					
				} else if (frames.get(currentIndex).equals("X") || frames.get(currentIndex).equals("10")) {
					subList = frames.subList(currentIndex, currentIndex+1);
					System.out.println("No.: " + i 
							+ ". INFO: Strike. " + subList.toString());
					currentIndex = currentIndex + 1;
					
				} else if ( !hasFault(frames.get(currentIndex), frames.get(currentIndex +1) )
						&& sumIsTen(frames.get(currentIndex), frames.get(currentIndex +1) ) ) {
					subList = frames.subList(currentIndex, currentIndex+2);
					System.out.println("No.: " + i 
							+ ". INFO: Spare. " + subList.toString());
					currentIndex = currentIndex + 2;
					
				} else if ( hasFault(frames.get(currentIndex), frames.get(currentIndex +1) )) {
					subList = frames.subList(currentIndex, currentIndex+2);
					System.out.println("No.: " + i 
							+ ". INFO: FAULT. " + subList.toString());
					currentIndex = currentIndex + 2;
					
				} else {
					subList = frames.subList(currentIndex, currentIndex+2);
					System.out.println("No.: " + i 
							+ ". INFO: Nothing special. " + subList.toString());
					currentIndex = currentIndex + 2;
				}
				response.add(new Frame(subList));
			} catch (Exception e) {
				System.out.println("ERROR: Incorrect number of frames.");
			}
		}
		System.out.println("Frames: " + frames.size());
		System.out.println("Index: " + currentIndex);
		if (response.size() == 10 && frames.size() == currentIndex) {
			System.out.println("Frames quantity: " + response.size());
			return response;
		} else {
			System.out.println("ERROR: Incorrect number of frames.");
			throw new RuntimeException();
		}
	}

	@Override
	public Boolean validateMoveValue(String move) {
		return (move.equals("F") || move.equals("X") || move.equals("/")
				|| ( Integer.parseInt(move) >= 0 && Integer.parseInt(move) <= 10 ));
	}
	
	private Boolean sumIsTen(String a, String b) {
		return Integer.parseInt(a) + Integer.parseInt(b) == 10;
	}
	
	private Boolean hasFault(String a, String b) {
		return a.equals("F") || b.equals("F");
	}

}
