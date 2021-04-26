package com.jobsity.bowling.game.service;

import java.io.BufferedReader;
import java.util.List;

import com.jobsity.bowling.game.model.PlayerMoves;

public interface FileParser {

    List<PlayerMoves> parse(BufferedReader reader);

}
