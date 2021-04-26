package com.jobsity.bowling.game.controller.cmd;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jobsity.bowling.game.controller.BowlingController;
import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.Printer;

@Component
public class CommandLineInputController {
	
	private BowlingController bowlingController;

	private Printer printer;
	
	@Autowired
	public CommandLineInputController(BowlingController bowlingController, Printer printer) {
		this.bowlingController = bowlingController;
		this.printer = printer;
	}
	
	public void run(String path) {
		try {
			if (path == null) {
				throw new RuntimeException(
						"Path's file must be delivered.");
			}
			Path correctPath = this.getCorrectPath(path);				
			/* Process the file */
			try (InputStream is = new FileInputStream(correctPath.toString())) {
				/*
				 * File is processed, and players are created with theirs moves.
				 */
				List<PlayerMoves> playersMoves = bowlingController.parseFile(is);
				/*
				 * Game should be print.
				 */
				printer.printGame(playersMoves);
				
			} catch (Throwable e) {
				System.out.println(e.getMessage());
			}
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Get the correct path (absolute or relative), and validate that it's a file.
	 * 
	 * @param path to verify
	 * @return the correct path
	 * @throws RuntimeException when the path is not correct
	 */
	private Path getCorrectPath(String path) throws RuntimeException {
		Path correctPath = null;
		Path pathObject = Paths.get(path);
		
		/* Get absolute path */
		String relativePathPreffix = System.getProperty("user.dir");
		
		/* Get relative path */
		Path relativeObj = Paths.get(String.join("/", relativePathPreffix, path));

		/* Verify if exists absolute or relative path */
		if (Files.exists(pathObject)) {
			correctPath = pathObject;
		} else if (Files.exists(relativeObj)) {
			correctPath = relativeObj;
		} else {
			throw new RuntimeException("ERROR: File does not exists");
		}
		
		/* Verify if it's not a directory */
		if (Files.isDirectory(correctPath)) {
			throw new RuntimeException("ERROR: Directory has been provided.");
		}

		return correctPath;
	}

}
