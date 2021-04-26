package com.jobsity.bowling.game.factory.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.factory.FrameFactory;
import com.jobsity.bowling.game.model.Frame;
import com.jobsity.bowling.game.model.impl.FrameImpl;

@Service
public class FrameFactoryImpl implements FrameFactory {

	@Override
	public Frame createFrame(List<String> subFrame, Integer order) {
		return new FrameImpl(subFrame, order);
	}

}
