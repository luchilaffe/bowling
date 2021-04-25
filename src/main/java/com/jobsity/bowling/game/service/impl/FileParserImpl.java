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
import org.springframework.stereotype.Service;

import com.jobsity.bowling.game.model.PlayerMoves;
import com.jobsity.bowling.game.service.FileParser;

@Service
public class FileParserImpl implements FileParser {

	@Override
	public List<PlayerMoves> parse(BufferedReader reader) {
	    final Map<String, List<String>> players = new HashMap<>();
		try {
			
		    /* read csv file */
		    Iterable<CSVRecord> records = CSVFormat.TDF.parse(reader);
		    
		    System.out.println("Read input file:");
		    for (CSVRecord record : records) {
		    	
		        System.out.println("JUGADOR: " + record.get(0) + " PINS: " + record.get(1));
		        
		        if (players.containsKey(record.get(0))) {
		        	System.out.println("Parser. Jugador Existe.");
		        	players.get(record.get(0)).add(record.get(1));
		        } else {
		        	System.out.println("Parser. Jugador NO Existe. agregar. ");
		        	List<String> pins = new ArrayList<>();
		        	pins.add(record.get(1));
		        	players.put(record.get(0), pins);
		        }
		    }
		    
		    // close the reader
		    reader.close();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		System.out.println("Parser. JUGADORES: " + players.toString());
		System.out.println("......");
		return players.entrySet().stream()
				.map((entry) -> {
						return new PlayerMoves(entry.getKey(), entry.getValue());
				}).collect(Collectors.toList());
	}
	
}
