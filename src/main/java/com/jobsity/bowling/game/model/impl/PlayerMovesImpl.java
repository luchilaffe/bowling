package com.jobsity.bowling.game.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobsity.bowling.game.model.Frame;
import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.MovesValidator;

public class PlayerMovesImpl implements PlayerMoves {

    @Autowired
    MovesValidator movesValidator;

    String name;
    List<Frame> frames = new ArrayList<>();
    List<Integer> scores = new ArrayList<>();

    public PlayerMovesImpl(String name, List<String> frames, MovesValidator validator) {
        super();
        this.name = name;
        this.movesValidator = validator;
        this.frames = movesValidator.validateFrames(frames);
        this.calculateScores();
    }

    public PlayerMovesImpl calculateScores() {
        for (int i = 0; i < 8; i++) {
            scores.add(frames.get(i).calculateScore(frames.get(i + 1), frames.get(i + 2)));
        }
        scores.add(frames.get(8).calculateScore(frames.get(9), null));
        frames.get(9).calculateScore(null, null);
        return this;
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
