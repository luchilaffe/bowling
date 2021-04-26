package com.jobsity.bowling.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.factory.FrameFactory;
import com.jobsity.bowling.game.model.Frame;
import com.jobsity.bowling.game.service.MovesValidator;

@Service
public class MovesValidatorImpl implements MovesValidator {
	
	@Autowired
	FrameFactory frameFactory;

	public List<Frame> validateFrames(List<String> frames){
		List<Frame> response = new ArrayList<>();

		int currentIndex = 0;
		for (int i = 0; i < 10; i++) {
			List<String> subList;
			try {
				if ((frames.get(currentIndex).equals("X") && i == 9 )) {
					subList = frames.subList(currentIndex, currentIndex+3);
					currentIndex = currentIndex + 3;
				} else if (frames.get(currentIndex).equals("X") || frames.get(currentIndex).equals("10")) {
					subList = frames.subList(currentIndex, currentIndex+1);
					currentIndex = currentIndex + 1;
					
				} else if ( !hasFault(frames.get(currentIndex), frames.get(currentIndex +1) )
						&& sumIsTen(frames.get(currentIndex), frames.get(currentIndex +1) ) ) {
					subList = frames.subList(currentIndex, currentIndex+2);
					currentIndex = currentIndex + 2;
					
				} else if ( hasFault(frames.get(currentIndex), frames.get(currentIndex +1) )) {
					subList = frames.subList(currentIndex, currentIndex+2);
					currentIndex = currentIndex + 2;
					
				} else {
					subList = frames.subList(currentIndex, currentIndex+2);
					currentIndex = currentIndex + 2;
				}
				response.add(frameFactory.createFrame(subList, Integer.valueOf(i)));
			} catch (Exception e) {
				System.out.println("ERROR: Incorrect number of frames.");
			}
		}
		if (response.size() == 10 && frames.size() == currentIndex) {
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
