package com.jobsity.bowling.game.factory;

import java.util.List;

import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.MovesValidator;

public interface PlayerMovesFactory {

    PlayerMoves createPlayerMoves(String name, List<String> frames, MovesValidator validator);

}
