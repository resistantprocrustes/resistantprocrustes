package com.example.loaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TpsInterpreter implements IInterpeter{ 

	int lmCounter = 0;
	int lmCount = 0;
	
	
	public ILoadedDocument getDocument(String filepath){ 
		BufferedReader br;
		TpsLoadedDocument doc = new TpsLoadedDocument();
		try{
			String currentLine;
			br = new BufferedReader(new FileReader(filepath));
			String header="";
			while((currentLine = br.readLine()) != null){
				currentLine = currentLine.trim();
				if(currentLine.startsWith("LM") || currentLine.startsWith("lm")){
					int numLandmarks = Integer.parseInt(currentLine.split("=")[1]);
					doc.addEntity(new PCEntity(numLandmarks));
					for(int i=0; i<numLandmarks; i++){
						String[] landmarkCoords = br.readLine().trim().split("\\s+");
						Landmark landmark = new Landmark(landmarkCoords.length);
						for(int j=0; j<landmarkCoords.length; j++){
							landmark.addCordinate(Double.parseDouble(landmarkCoords[j]),j); 
						}
						doc.addLandmarkToLast(landmark);
					}
				}
				
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		return doc;
	}

}
