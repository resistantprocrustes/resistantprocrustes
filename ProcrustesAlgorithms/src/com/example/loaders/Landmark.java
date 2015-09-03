package com.example.loaders;

public class Landmark {

	public int dimentions = 0;
	double[] _coordinates;
	public Landmark(int dimentions){ 
		this.dimentions = dimentions;
		_coordinates = new double[dimentions];
	}
	
	public Landmark(double[] coords){
		dimentions = coords.length;
		_coordinates = coords;
	}
	
	public void addCordinate(double coord, int index){ 
		_coordinates[index] = coord;		
	}

	public double[] getCoordinates() {
		return _coordinates;
	}

	public double[] get3DCoordinates() {
		
		return _coordinates;
	}

}
