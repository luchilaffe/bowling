package com.jobsity.bowling.game.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.Printer;

@Service
public class PrinterImpl implements Printer {

    @Override
    public void printGame(List<PlayerMoves> playerMoves) {

        /* Header */
        printHeader(playerMoves.get(0).getFrames().size());

        /* For each Player */
        playerMoves.stream().forEach(play -> {
            printPlayerMatchSummary(play);
        });
    }

    private void printPlayerMatchSummary(PlayerMoves play) {
        printPlayer(play);
        printFrames(play);
        printScores(play);
    }

    private void printPlayer(PlayerMoves play) {
        System.out.println(play.getName());
    }

    private void printHeader(Integer movesQuantity) {
        System.out.print("Frame");
        for (int i = 1; i <= movesQuantity; i++) {
            System.out.print("\t\t" + i);
        }
        System.out.println("");
    }

    private void printFrames(PlayerMoves play) {
        System.out.print("Pinfalls");
        for (int i = 0; i < play.getFrames().size(); i++) {
            System.out.print("\t" + play.getFrames().get(i).playsToString());
        }
        System.out.print("\n");
    }

    private void printScores(PlayerMoves play) {
        System.out.print("Score");
        Integer sum = 0;
        for (int i = 0; i < play.getFrames().size(); i++) {
            sum = sum + play.getFrames().get(i).getScore();
            System.out.print("\t\t" + sum);
        }
        System.out.println("");
    }
}
