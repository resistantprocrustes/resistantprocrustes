package com.example.Algorithms.distances;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

public abstract class DistanceCalculator {
	
	public double[][] calculate(ArrayList<SimpleMatrix> elems) {
		int nElements = elems.size();
		double[][] aux = new double[nElements][nElements];
		for(int i=0; i<nElements; i++){
			for(int j=i; j<nElements; j++){
				double d = 0;
				if(i!=j){
					d=calculateDistance(elems.get(i), elems.get(j));
					aux[i][j] = d;
					aux[j][i] = d;
				}
				else
					aux[i][j] = d;
			}
		}
		return aux;
	}
	
	protected abstract double calculateDistance(SimpleMatrix a, SimpleMatrix b);

}
