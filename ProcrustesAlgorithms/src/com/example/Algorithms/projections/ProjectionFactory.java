package com.example.Algorithms.projections;

public class ProjectionFactory {

	public static ICalcProjection create(int typeOp) {
		
		if(typeOp == 0){
			return new RobustProjection();
		}else if(typeOp == 1){
			return new EuclideanProjection();
		}else
			return null;	
	}
	

}
