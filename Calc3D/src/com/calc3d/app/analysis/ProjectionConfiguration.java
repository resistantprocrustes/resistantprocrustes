package com.calc3d.app.analysis;

public class ProjectionConfiguration extends DialogConfiguration {

	public static final int LEAST_SQR_PROJETION = 0;
	public static final int ROBUST_PROJECTION = 1;
	int type = 0;
	private int dimentions;
	private String name =""; 
	
	public ProjectionConfiguration(int i) {
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setDimensions(int i) {
		this.dimentions = i;
		
	}

	public int getDimensions() {
		return dimentions;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

}
