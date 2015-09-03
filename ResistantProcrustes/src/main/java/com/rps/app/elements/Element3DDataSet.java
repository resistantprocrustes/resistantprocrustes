package com.rps.app.elements;

import java.awt.Color;
import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.ejml.simple.SimpleMatrix;

import com.rps.app.Globalsettings;
import com.rps.app.elements.dataset.DataSet;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.geometry3d.Clip;
import com.rps.geometry3d.Element;
import com.rps.geometry3d.ElementCollection;
import com.rps.geometry3d.ElementPoint;
import com.rps.math.Vector3D;
import com.rps.utils.IMatrixable;
import com.example.loaders.PCEntity;
import com.procrustes.Utils.Commons;

public class Element3DDataSet extends Element3DCollection implements IMatrixable<double[][]> {

	ComposeSimpleElement dataset;
	
	public Element3DDataSet(){
		super();
	}
	
	public Element3DDataSet(ComposeSimpleElement dataset) {
		this.dataset = dataset;
		ComposeSimpleElement specimens = ((ComposeSimpleElement) dataset.getElementByKey("specimens"));
		if(specimens==null) return;
		ArrayList<SampleSimpleElement> specimensList = (ArrayList<SampleSimpleElement>) specimens.getAllElements();
		Element3DCollection specimens3D = new Element3DCollection();
		specimens3D.setName("specimens");
		for(int i=0; i<specimens.size(); i++){
			SampleSimpleElement sample = specimensList.get(i);
			Element3DEntity specimen3D = new Element3DEntity(sample);
			specimens3D.add(specimen3D);
		}
		this.add(specimens3D);
		String name = dataset.getName(); 
		this.setName(name);
	}

	
	public String getDefinition() {
		return "Dataset";
	}
	
	
	public Vector3D getMaxBound(){
		Vector3D maxbound = new Vector3D(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY);
		for(int i=0; i<elements.size(); i++){
			Vector3D bound = elements.get(i).getMaxBound();
			maxbound.set(
					maxbound.getX() < bound.getX() ? bound.getX() : maxbound.getX(),
					maxbound.getY() < bound.getY() ? bound.getY() : maxbound.getY(),
					maxbound.getZ() < bound.getZ() ? bound.getZ() : maxbound.getZ());
		}
		int a = 1;
		return maxbound;
	}
	
	
	public Vector3D getMinBound(){
		Vector3D minbound = new Vector3D(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		for(int i=0; i<elements.size(); i++){
			Vector3D bound = elements.get(i).getMinBound();
			minbound = new Vector3D(Math.min(bound.getX(), minbound.getX()),
					Math.min(bound.getY(), minbound.getY()), Math.min(bound.getZ(), minbound.getZ()));
		}
		return minbound;
	}
	
	
	public double[][] toMatrix() {
		
		return null;
	}
	
	
	public ComposeSimpleElement getDataSet(){
		return dataset;
	}
	


}
