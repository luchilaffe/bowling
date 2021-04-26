package com.jobsity.bowling.game.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.factory.PlayerMovesFactory;
import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.FileParser;
import com.jobsity.bowling.game.service.MovesValidator;

@Service
public class FileParserImpl implements FileParser {
	
	@Autowired
	MovesValidator movesValidator;
	
	@Autowired
	PlayerMovesFactory playerMovesFactory;

	@Override
	public List<PlayerMoves> parse(BufferedReader reader) throws RuntimeException {
	    final Map<String, List<String>> players = new HashMap<>();
		try {
			
		    /* Read csv file */
		    Iterable<CSVRecord> records = CSVFormat.TDF.parse(reader);
		    
		    for (CSVRecord record : records) {
		    	
		        if (movesValidator.validateMoveValue(record.get(1))) {
			        if (players.containsKey(record.get(0))) {
			        	players.get(record.get(0)).add(record.get(1));
			        } else {
			        	List<String> pins = new ArrayList<>();
			        	pins.add(record.get(1));
			        	players.put(record.get(0), pins);
			        }
		        } else {
		        	long currentLine = record.getParser().getCurrentLineNumber();
		        	System.out.println("ERROR: Invalid input. Value: " + record.get(1) 
		        		+ " is not correct in line: " + currentLine);
		        	throw new RuntimeException();
		        }
		    }
		    
		    /* Close the reader */
		    reader.close();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		return players.entrySet().stream()
				.map((entry) -> {
						return playerMovesFactory.createPlayerMoves(entry.getKey(), entry.getValue(), movesValidator);
				}).collect(Collectors.toList());
	}
	
}
