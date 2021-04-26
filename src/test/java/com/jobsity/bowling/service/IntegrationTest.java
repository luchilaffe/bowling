package com.jobsity.bowling.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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
		
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final String utf8 = StandardCharsets.UTF_8.name();
		
		try (PrintStream ps = new PrintStream(baos, true, utf8)) {
			
			String out = baos.toString(utf8);
			assertThat(out.indexOf("Frames\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\r\n")).isNotNull();
			
			
		}
		
		
		
		
		assertThat(path).isNotNull();
	}
	
}
