package com.jobsity.bowling.service.controller.cmd;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "scoregame")
public class CommandLineInputController {
	
	@Option(names = { "-p", "--path" }, description = "Path's file to be process")
	public String path;

	
	public void run() {
		try {
			if (path == null) {
				throw new RuntimeException(
						"Path's file must be delivered. Use '-p' or '--path' param to provide it.");
			}
			Path correctPath = this.getCorrectPath(path);				
			/** Process the file */
			try (InputStream is = new FileInputStream(correctPath.toString())) {
				/**
				 * TODO: Here the file should be processed 
				 */
				// System.out.println("InputStream: " + is);
				
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
		
		/** Get absolute path */
		String relativePathPreffix = System.getProperty("user.dir");
		
		/** Get relative path */
		Path relativeObj = Paths.get(String.join("/", relativePathPreffix, path));

		/** Verify if exists absolute or relative path */
		if (Files.exists(pathObject)) {
			correctPath = pathObject;
		} else if (Files.exists(relativeObj)) {
			correctPath = relativeObj;
		} else {
			throw new RuntimeException("ERROR: File does not exists");
		}
		
		/** Verify if it's not a directory */
		if (Files.isDirectory(correctPath)) {
			throw new RuntimeException("ERROR: Directory has been provided.");
		}

		return correctPath;
	}

}
