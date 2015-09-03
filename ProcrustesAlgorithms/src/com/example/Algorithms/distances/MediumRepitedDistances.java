package com.example.Algorithms.distances;

import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.ejml.simple.SimpleMatrix;

public class MediumRepitedDistances extends DistanceCalculator{

	protected double calculateDistance(SimpleMatrix m1,
			SimpleMatrix m2) {
		int numLandmarks = m1.numRows();
		int dim = m1.numCols();
		double d = 0;
		for(int i=0; i<numLandmarks; i++){
			
			Vector3D a = m1.numCols()==2 ? new Vector3D(m1.get(i,0),m1.get(i,1),0) :
				new Vector3D(m1.extractVector(true, i).getMatrix().getData());
			Vector3D b = m2.numCols()==2 ? new Vector3D(m2.get(i,0),m2.get(i,1),0) :
				new Vector3D(m2.extractVector(true, i).getMatrix().getData());
			d += a.distance(b);
		}
		
		return d;
	}

}
