package com.calc3d.app.elements.dataset;

import org.ejml.simple.SimpleMatrix;


/**
 * @author Lucas
 *
 */
public class LMConfiguration {

	
	SimpleMatrix landmars;
	int ID;
	String name;
	double scale;
	
	
	
	/**
	 * 
	 */
	public LMConfiguration() {
		ID = 0;
		name = "";
		scale = 1;
	}



	/**
	 * @param landmars Landmarks of specimen
	 * @param iD Specimen id
	 * @param name specimen name
	 * @param scale factor scale
	 */
	public LMConfiguration(SimpleMatrix landmars, int iD, String name,
			double scale) {
		super();
		this.landmars = landmars;
		ID = iD;
		this.name = name;
		this.scale = scale;
	}



	public SimpleMatrix getLandmars() {
		return landmars;
	}



	public void setLandmars(SimpleMatrix landmars) {
		this.landmars = landmars;
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getScale() {
		return scale;
	}



	public void setScale(double scale) {
		this.scale = scale;
	}
	
	
	
}
