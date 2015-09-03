package com.rps.app.fileload;

import com.rps.app.elements.dataset.DataSet;

public abstract class DataSetLoader extends FileLoader {

	
	public abstract Object load(String path);

}
