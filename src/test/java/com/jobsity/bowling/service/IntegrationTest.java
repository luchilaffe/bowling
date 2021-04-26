package com.jobsity.bowling.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jobsity.bowling.game.controller.BowlingController;
import com.jobsity.bowling.game.controller.cmd.CommandLineInputController;
import com.jobsity.bowling.game.controller.impl.BowlingControllerImpl;
import com.jobsity.bowling.game.factory.FrameFactory;
import com.jobsity.bowling.game.factory.PlayerMovesFactory;
import com.jobsity.bowling.game.factory.impl.FrameFactoryImpl;
import com.jobsity.bowling.game.factory.impl.PlayerMovesFactoryImpl;
import com.jobsity.bowling.game.service.FileParser;
import com.jobsity.bowling.game.service.MovesValidator;
import com.jobsity.bowling.game.service.Printer;
import com.jobsity.bowling.game.service.impl.FileParserImpl;
import com.jobsity.bowling.game.service.impl.MovesValidatorImpl;
import com.jobsity.bowling.game.service.impl.PrinterImpl;

class IntegrationTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final String frame = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";
	private final String jeff = "Jeff\n";
	private final String pinfallsStrikes = 
			"Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t10\t10\t10\n";
	private final String scoreAllStrikes = 
			"Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";
	
	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
    @Test
	void testAllStrikes() throws UnsupportedEncodingException {
    	FrameFactory frameFactory = new FrameFactoryImpl();
    	MovesValidator movesValidator = new MovesValidatorImpl(frameFactory);
    	PlayerMovesFactory playerMovesFactory = new PlayerMovesFactoryImpl();
    	FileParser parser = new FileParserImpl(movesValidator, playerMovesFactory); 
    	Printer printer = new PrinterImpl();
    	BowlingController bowlingController = new BowlingControllerImpl(parser);
    	
    	CommandLineInputController cmd = new CommandLineInputController(bowlingController, printer);
		
    	String path = "/src/test/resources/strikes.txt";
		cmd.run(path);
				
		try (PrintStream ps = new PrintStream(outContent, true)) {
			assertThat(outContent.toString()).contains(frame);
			assertThat(outContent.toString()).contains(jeff);
			assertThat(outContent.toString()).contains(pinfallsStrikes);
			assertThat(outContent.toString()).contains(scoreAllStrikes);
			assertThat(outContent.toString()).contains(frame+jeff+pinfallsStrikes+scoreAllStrikes);
		}
	}
	
}
