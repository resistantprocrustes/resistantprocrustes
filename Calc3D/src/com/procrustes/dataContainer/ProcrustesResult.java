package com.procrustes.dataContainer;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import com.example.loaders.Landmark;
import com.example.loaders.PCEntity;
import com.procrustes.Utils.Commons;

public class ProcrustesResult {

	private ArrayList<PCEntity> transformations;
	private ArrayList<Landmark> result;
	
	public ProcrustesResult(){
		transformations = new ArrayList<PCEntity>();
		result = new ArrayList<Landmark>();
	}
	
	public ProcrustesResult(ArrayList<SimpleMatrix> resultMatrix){
		transformations = new ArrayList<PCEntity>();
		result = new ArrayList<Landmark>();
		for(int i=0; i<resultMatrix.size() - 1; i++){
			SimpleMatrix mat = resultMatrix.get(i);
			PCEntity entity = new PCEntity(mat.numRows());
			for(int j=0; j<mat.numRows(); j++)
				entity.loadLandmark(new Landmark(Commons.getRowFromMatrix(mat.extractVector(true, j), 0)));
			transformations.add(entity);
		}
		SimpleMatrix res = resultMatrix.get(resultMatrix.size() - 1);
		for(int j=0; j<res.numRows(); j++)
			result.add(new Landmark(Commons.getRowFromMatrix(res.extractVector(true, j), 0)));
	}

	public ArrayList<PCEntity> getTransformations() {
		return transformations;
	}

	public void setTransformations(ArrayList<PCEntity> transformations){ 
		this.transformations = transformations;
	}

	public ArrayList<Landmark> getResult() {
		return result;
	}

	public void setResult(ArrayList<Landmark> result) {
		this.result = result;
	}
	
	
}
