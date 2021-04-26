package com.jobsity.bowling.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobsity.bowling.game.model.Frame;
import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.model.impl.FrameImpl;
import com.jobsity.bowling.game.model.impl.PlayerMovesImpl;
import com.jobsity.bowling.game.service.MovesValidator;

/**
 * @author Carlos Lafferriere
 *
 */
@ExtendWith(MockitoExtension.class)
class PlayerMovesImplTest {

    @Mock
    MovesValidator movesValidator;

    static PlayerMoves playerAllStrike, playerAllZero;
    static List<Frame> strikeTenthFrame, zeroFrame;

    @BeforeEach
    void setup() {
        /* Create 10 Strikes For 1 Player */
        strikeTenthFrame = new ArrayList<>();
        List<String> listOneStrike = new ArrayList<String>();
        listOneStrike.add("X");
        for (int i = 0; i < 9; i++) {
            Frame strikeFrame = new FrameImpl(listOneStrike, i);
            strikeTenthFrame.add(strikeFrame);
        }
        List<String> listThreeStrike = new ArrayList<String>();
        listThreeStrike.add("X");
        listThreeStrike.add("X");
        listThreeStrike.add("X");
        Frame strike10Frame = new FrameImpl(listThreeStrike, 9);
        strikeTenthFrame.add(strike10Frame);

        List<String> listStrike = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            listStrike.add("X");
        }
        when(movesValidator.validateFrames(listStrike)).thenReturn(strikeTenthFrame);
        playerAllStrike = new PlayerMovesImpl("AllStrike", listStrike, movesValidator);

        /* Create 10 Zero For 1 Player */
        zeroFrame = new ArrayList<>();
        List<String> listOneZero = new ArrayList<String>();
        listOneZero.add("0");
        listOneZero.add("0");
        for (int i = 0; i < 10; i++) {
            Frame frameWithZeros = new FrameImpl(listOneZero, i);
            zeroFrame.add(frameWithZeros);
        }
        List<String> listZero = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            listZero.add("0");
        }
        when(movesValidator.validateFrames(listZero)).thenReturn(zeroFrame);
        playerAllZero = new PlayerMovesImpl("AllZero", listZero, movesValidator);
    }

    @Test
    void calculateScoreAllStrikes() {
        PlayerMoves playerWithScore = playerAllStrike.calculateScores();
        Integer totalScore = 0;
        for (int i = 0; i < 10; i++) {
            totalScore = totalScore + playerWithScore.getFrames().get(i).getScore();
        }
        assertThat(playerWithScore.getFrames().get(0).getScore()).isNotNull();
        assertThat(playerWithScore.getFrames().get(9).getScore()).isNotNull();
        assertEquals(300, totalScore);
    }

    @Test
    void calculateScoreAllZeros() {
        PlayerMoves playerWithScore = playerAllZero.calculateScores();
        Integer totalScore = 0;
        for (int i = 0; i < 10; i++) {
            totalScore = totalScore + playerWithScore.getFrames().get(i).getScore();
        }
        assertThat(playerWithScore.getFrames().get(0).getScore()).isNotNull();
        assertThat(playerWithScore.getFrames().get(9).getScore()).isNotNull();
        assertEquals(0, totalScore);
    }

}
