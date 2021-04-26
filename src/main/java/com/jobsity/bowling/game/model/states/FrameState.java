package com.jobsity.bowling.game.model.states;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobsity.bowling.game.model.Frame;

public abstract class FrameState {

    @Autowired
    Frame frame;

    FrameState(Frame frame) {
        this.frame = frame;
    }

}
