package com.jobsity.bowling.game.factory;

import java.util.List;

import com.jobsity.bowling.game.model.Frame;

public interface FrameFactory {

    Frame createFrame(List<String> subFrame, Integer order);

}
