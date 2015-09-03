package com.example.loaders;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

public class PCEntity {

	ArrayList<Landmark> _landmarks;
	public PCEntity(int numLandmarks){ 
		_landmarks = new ArrayList<Landmark>();
	}

	public PCEntity(SimpleMatrix mat) {
		_landmarks  = new ArrayList<Landmark>();
		for(int i=0; i<mat.numRows(); i++){
			this.addLandmarkToLast(new Landmark(mat.extractVector(true, i).getMatrix().getData()));
		}
		
	}

	public void loadLandmark(Landmark landmark){ 
		_landmarks.add(landmark);
	}

	public double[][] toMatrix() {
		double[][] elem = new double[_landmarks.size()][3];
		int dimentions = _landmarks.get(0).dimentions;
		for(int i=0; i<_landmarks.size(); i++){
			elem[i] = _landmarks.get(i).get3DCoordinates();
		}
		return elem;
	}

	public ArrayList<Landmark> getLandmarks() {
		return _landmarks;
	}

	public void addLandmarkToLast(Landmark landmark) {
		_landmarks.add(landmark);
		
	}

}
