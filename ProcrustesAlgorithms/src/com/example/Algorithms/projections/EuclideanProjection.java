package com.example.Algorithms.projections;

import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.ejml.simple.SimpleMatrix;

import com.example.Algorithms.CustomMatrixUtils;
import com.example.utils.CommonUtils;

public class EuclideanProjection implements ICalcProjection {

	static int LIMIT = 150;

	
	public ArrayList<Vector3D> execute(SimpleMatrix distances, SimpleMatrix seeds) {
		SimpleMatrix X = seeds.transpose();
		int k = distances.numRows();
		int n = distances.numCols();
		int dim = seeds.numRows();
		
		SimpleMatrix DK = ProjectionUtils.distAllPairsL2(X);
		double c = CustomMatrixUtils.sumRows(
				CustomMatrixUtils.sumCols(
						CustomMatrixUtils.powElements(distances.minus(DK), 2))).get(0,0);
		System.out.println("Costo inicial: "+c);
		double cant = 0;
		for(int iter =0; iter<LIMIT; iter++){
			for(int ii=0; ii<n; ii++){
				double f = Math.floor(Math.sqrt(iter+1));
				for(int it=0; it<f; it++){
					SimpleMatrix Z = ProjectionUtils.computeIntersections(X, ii, distances);
					SimpleMatrix a = CustomMatrixUtils.Mean2(Z);
					X.insertIntoThis(0, ii, a);					
				}
			}
			DK = ProjectionUtils.distAllPairsL2(X);
			cant = c;
			X.print();
			c = CustomMatrixUtils.sumRows(
					CustomMatrixUtils.sumCols(
							CustomMatrixUtils.powElements(distances.minus(DK), 2))).get(0,0);
			if(Math.abs(c - cant) < 0.00000001){
				break;
			}
			System.out.println("Costo en it "+ iter +": "+c); 
		}
		X = X.transpose();
		ArrayList<Vector3D> v = new ArrayList<Vector3D>();
		for(int i=0; i<X.numRows();i++){
			double[] data = X.extractVector(true, i).getMatrix().getData();
			if(data.length == 3)
				v.add(new Vector3D(data));
			else 
				v.add(new Vector3D(data[0], data[1], 0));
		}
		return v;
	}

}
