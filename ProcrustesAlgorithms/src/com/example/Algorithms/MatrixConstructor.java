package com.example.Algorithms;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

public class MatrixConstructor {

	public static ArrayList<SimpleMatrix> create(int j, int numRows,
			int numCols, int numEntities){ 
		
		ArrayList<SimpleMatrix> list = new ArrayList<SimpleMatrix>();
		for(int i=0; i<numEntities; i++)	{
			list.add(MatrixConstructor.create(j, numRows, numCols));
		}
		return list;
	}

	public static SimpleMatrix create(int i, int numRows, int numCols){ 
		SimpleMatrix matrix = new SimpleMatrix(numRows, numCols);
		matrix.set(i);
		return matrix;
		
	}

}
