package com.calc3d.app.fileload;

import com.calc3d.app.elements.dataset.DataSet;

public abstract class DataSetLoader extends FileLoader {

	@Override
	public abstract Object load(String path);

}
