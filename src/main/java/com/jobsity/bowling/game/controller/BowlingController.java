package com.jobsity.bowling.game.controller;

import java.io.InputStream;
import java.util.List;

import com.jobsity.bowling.game.model.PlayerMoves;

/**
 * Bowling's interface that allow to process a file that contains a bowling game.
 * 
 * @author Carlos Lafferriere
 *
 */
public interface BowlingController {

    /**
     * Parse a file that contains a bowling game, and return it as list of players with theirs
     * moves.
     * 
     * @param stream
     * @return a list of players with theirs moves
     * @throws Throwable
     */
    List<PlayerMoves> parseFile(InputStream stream) throws Throwable;

}
