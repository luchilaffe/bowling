package com.jobsity.bowling.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jobsity.bowling.game.controller.cmd.CommandLineInputController;

/**
 * Coding challenge. Bowling service.
 * 
 * The program runs from the command-line and takes a text file as input.
 * 
 * The program reads the input text file and parse its content, which should have the results for
 * several players bowling 10 frames each.
 * 
 * The program handles bad input like more than ten throws (i.e., no chance will produce a negative
 * number of knocked down pins or more than 10, etc), invalid score value or incorrect format.
 * 
 * The program output the scoring for the associated game.
 * 
 * @author Carlos Lafferriere
 */
@SpringBootApplication
public class BowlingServiceApplication implements CommandLineRunner {

    @Autowired
    CommandLineInputController cmd;

    public static void main(String[] args) {
        SpringApplication.run(BowlingServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cmd.run(args[0]);
    }

}
