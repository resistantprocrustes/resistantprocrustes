package com.example.loaders;

public class FileLoader {

	
	IInterpeter _interpreter;
	
	public FileLoader(IInterpeter interpreter){
		_interpreter = interpreter;
		
	}
	
	public ILoadedDocument Load(String filepath){
		return _interpreter.getDocument(filepath);
	}
}
