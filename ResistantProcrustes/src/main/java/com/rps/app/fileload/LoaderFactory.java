package com.rps.app.fileload;

import com.rps.app.exceptions.InvalidDatasetFileException;
import com.rps.app.resources.Messages;

public class LoaderFactory {
	
	public static FileLoader create(String ext) throws InvalidDatasetFileException{
		if(ext.endsWith(".tps")){
			return new TpsLoader();
		}
		if(ext.endsWith(".nts") || ext.endsWith(".NTS")){
			return new NtsLoader();
		}
		if(ext.endsWith(".txt")){
			return new MorphologikaLoader();
		}
		throw new InvalidDatasetFileException(Messages.getString("exception.datasetfile"));
		
	};
	
}
