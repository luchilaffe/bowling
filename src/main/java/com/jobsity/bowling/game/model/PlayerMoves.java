package com.jobsity.bowling.game.model;

import java.util.List;

public interface PlayerMoves {

    PlayerMoves calculateScores();

    String getName();

    void setName(String name);

    List<Frame> getFrames();

    void setFrames(List<Frame> frames);

}
