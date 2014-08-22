package com.calc3d.app.elements;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import com.calc3d.geometry3d.Clip;
import com.calc3d.geometry3d.Element;
import com.calc3d.geometry3d.ElementCollection;
import com.calc3d.math.Vector3D;

public class Element3DProjection extends Element3DCollection {

	
	public static int ROBUSTO = 0;
	public static int CM = 1;
	public int typeOp = 0;
	private ArrayList<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> _projections; 
	
	public Element3DProjection(){
		super();
	}
	
	public void setTypeOperation(int op){
		typeOp = op;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTypeOp() {
		return typeOp;
	}
	
	@Override
	public Vector3D getMaxBound(){
		Vector3D maxbound = new Vector3D(0,0,0);
		for(int i=0; i<elements.size(); i++){
			Vector3D bound = elements.get(i).getMaxBound();
			if(bound.getLength() > maxbound.getLength())
				maxbound = bound;
		}
		return maxbound;
	}
	
	@Override
	public Vector3D getMinBound(){
		Vector3D maxbound = new Vector3D(0,0,0);
		for(int i=0; i<elements.size(); i++){
			Vector3D bound = elements.get(i).getMaxBound();
			if(bound.getLength() < maxbound.getLength())
				maxbound = bound;
		}
		return maxbound;
	}

	public void populateProjections(
			ArrayList<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> projections,
			Element3D selected) {
		_projections = projections;
		ArrayList<Element3D> elems = ((Element3D)selected).getContainedElements();
		for(int i=0; i<projections.size(); i++){
			org.apache.commons.math3.geometry.euclidean.threed.Vector3D vector = projections.get(i);
			Element3D elem = elems.get(i);
			Element3DPoint point = new Element3DPoint(new Vector3D(vector.getX(), vector.getY(), vector.getZ()));
			point.setName(elem.getName()+"_Projection");
			point.setText("");
			this.add(point);
		}
		
		
	}
	
	@Override
	public ArrayList<Element3D> getContainedElements(){
		return elements;
	}
	
	

}
