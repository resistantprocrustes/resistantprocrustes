package com.calc3d.app.elements;

import java.util.ArrayList;

import com.calc3d.app.Globalsettings;
import com.calc3d.geometry3d.Clip;
import com.calc3d.geometry3d.Element;
import com.calc3d.geometry3d.ElementCollection;
import com.calc3d.geometry3d.ElementPoint;
import com.calc3d.math.Vector3D;

public class Element3DDataSet extends Element3D {

	ArrayList<Element3DEntity> entities;
	
	public Element3DDataSet(ArrayList<Element3DEntity> entities){
		this.entities = entities;
	}
	
	public Element3DDataSet(){
		entities = new ArrayList<Element3DEntity>();
	}
	
	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return "Dataset";
	}

	@Override
	public Element createElement() {
		// TODO Auto-generated method stub
		ElementCollection ec = new ElementCollection();
		for(int i=0; i<entities.size(); i++){
			ec.addElement(entities.get(i).getElement());
		}
		this.elementContainer = true;
		return ec;
	}

	@Override
	public Element createElement(Clip clip) {
		ElementCollection ec = new ElementCollection();
		if(T!=null)ec.transform(T);
		for(int i=0; i<entities.size(); i++){
			if(entities.get(i).isVisible())
				ec.addElement(entities.get(i).createElement(clip));
		}
		this.elementContainer = true;
		
		clip.getClippedElement(ec);
		return ec;
	}
	
	public void addEntitie(Element3DEntity e){
		entities.add(e);
	}
	
	@Override
	public Vector3D getMaxBound(){
		Vector3D maxbound = new Vector3D(0,0,0);
		for(int i=0; i<entities.size(); i++){
			Vector3D bound = entities.get(i).getMaxBound();
			if(bound.getLength() > maxbound.getLength())
				maxbound = bound;
		}
		return maxbound;
	}
	
	@Override
	public Vector3D getMinBound(){
		Vector3D maxbound = new Vector3D(0,0,0);
		for(int i=0; i<entities.size(); i++){
			Vector3D bound = entities.get(i).getMaxBound();
			if(bound.getLength() < maxbound.getLength())
				maxbound = bound;
		}
		return maxbound;
	}
	
	@Override
	public ArrayList<Element3D> getContainedElements(){
		ArrayList<Element3D> list = new ArrayList<Element3D>();
		for(int i =0; i<entities.size(); i++){
			list.add(entities.get(i));
		}
		return list;
	}
	
	@Override 
	public void setVisible(boolean visible){
		super.setVisible(visible);
		for(int i=0; i<entities.size(); i++){
			entities.get(i).setVisible(visible);
		}
	}

}
