package com.jobsity.bowling.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobsity.bowling.game.model.Frame;
import com.jobsity.bowling.game.model.impl.FrameImpl;

@ExtendWith(MockitoExtension.class)
class FrameImplTest {
	
	static FrameImpl frameStrike, frameSpare, frameStrikeTenth, frameNormal, frame3StrikeTenth;
	
	@BeforeEach
	void setup() {
		List<String> listStrike = new ArrayList<String>();
		listStrike.add("X");
		frameStrike = new FrameImpl(listStrike, 1);
		
		List<String> listSpare = new ArrayList<String>();
		listSpare.add("4");
		listSpare.add("6");
		frameSpare = new FrameImpl(listSpare, 2);

		List<String> listStrikeTenth = new ArrayList<String>();
		listStrikeTenth.add("X");
		listStrikeTenth.add("6");
		listStrikeTenth.add("5");
		frameStrikeTenth = new FrameImpl(listStrikeTenth, 9);
		
		List<String> list3StrikeTenth = new ArrayList<String>();
		list3StrikeTenth.add("X");
		list3StrikeTenth.add("X");
		list3StrikeTenth.add("X");
		frame3StrikeTenth = new FrameImpl(list3StrikeTenth, 9);

		List<String> listNormal = new ArrayList<String>();
		listNormal.add("2");
		listNormal.add("3");
		frameNormal = new FrameImpl(listNormal, 2);
		
	}
	
	
	@ParameterizedTest
	@CsvSource({"X", "2,8", "2,4", "X,2,4", "F,2", "2,F", "2,/"})
	void createFrame(ArgumentsAccessor argumentsAccessor) {
		List<String> listFrame = new ArrayList<>();
		for (int i = 0; i < argumentsAccessor.size(); i++) {			
			listFrame.add(argumentsAccessor.getString(i));
		}
		Frame frame = new FrameImpl(listFrame, 3);
		assertThat(frame).isNotNull();
	}
	
	@Test
	void calculateScore3Strikes() {
		Integer score = frameStrike.calculateScore(frameStrike, frameStrike);
		assertThat(score).isNotNull();
		assertEquals(score, 30);
	}

	@Test
	void calculateScoreSpare() {
		Integer score = frameSpare.calculateScore(frameStrike, frameStrikeTenth);
		assertThat(score).isNotNull();
		assertEquals(score, 20);
	}
	
	@Test
	void calculateScoreNormal() {
		Integer score = frameNormal.calculateScore(frameStrike, frameStrikeTenth);
		assertThat(score).isNotNull();
		assertEquals(score, 5);
	}
	
	@Test
	void calculateScoreStrikeTenth() {
		Integer score = frameStrikeTenth.calculateScore(null, null);
		assertThat(score).isNotNull();
		assertEquals(score, 21);
	}
	
	@Test
	void calculateScore3StrikeTenth() {
		Integer score = frame3StrikeTenth.calculateScore(null, null);
		assertThat(score).isNotNull();
		assertEquals(score, 30);
	}
}
