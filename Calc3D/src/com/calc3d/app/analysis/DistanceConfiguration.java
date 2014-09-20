package com.calc3d.app.analysis;

public class DistanceConfiguration extends DialogConfiguration{
	public static int ROB_DISTANCE = 0;
	public static int MIN_SQR_DISTANCE = 1;
	
	private int typeDistance = 0;
	
	public DistanceConfiguration(int type){
		typeDistance = type;
		
	}

	public int getType(){
		return typeDistance;
	}
	public DistanceConfiguration() {}

}
