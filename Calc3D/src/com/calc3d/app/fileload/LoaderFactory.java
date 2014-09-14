package com.calc3d.app.fileload;

public class LoaderFactory {
	
	public static FileLoader create(String ext){
		if(ext.endsWith(".tps")){
			return new TpsLoader();
		}
		return null;
	};
	
}
