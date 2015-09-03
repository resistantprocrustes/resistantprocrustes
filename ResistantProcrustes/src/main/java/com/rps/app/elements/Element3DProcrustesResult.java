package com.rps.app.elements;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import com.rps.geometry3d.Clip;
import com.rps.geometry3d.Element;
import com.rps.geometry3d.ElementCollection;
import com.rps.math.Vector3D;
import com.example.loaders.Landmark;
import com.example.loaders.PCEntity;
import com.procrustes.dataContainer.ProcrustesResult;

public class Element3DProcrustesResult extends Element3D {

	ArrayList<SimpleMatrix> entities;
	ArrayList<Element3D> entities3D;
	SimpleMatrix result;
	private Element3DEntity result3D;
	
	
	
	
	public Element3DProcrustesResult(ArrayList<SimpleMatrix> entities,
			SimpleMatrix result) {
		super();
		this.entities = entities;
		this.result = result;
		this.entities3D = new ArrayList<Element3D>();
		for(int i=0; i<entities.size(); i++){
			entities3D.add(new Element3DEntity(entities.get(i)));
		}
	}
	
	public Element3DProcrustesResult(
			ProcrustesResult result) {
		ArrayList<PCEntity> entities = result.getTransformations();
		ArrayList<Landmark> consensus = result.getResult();
		entities3D = new ArrayList<Element3D>();
//		supuesta entidad
		Element3DEntity entity = new Element3DEntity();
		entity.setName("Result");
		//dibujo resultado
		for(int i=0; i<consensus.size(); i++){
			String name = "Result-LM" + i;
			double[] coords = consensus.get(i).getCoordinates();
			double zCoord = coords.length == 3 ? coords[2] : 0;
			Element3DPoint point = new Element3DPoint(coords[0], coords[1], zCoord);
			point.setFillColor(Color.WHITE);
			point.setLineColor(Color.WHITE);
			point.setRadius(4);
			point.setText("");
			point.setName(name);
			entity.addPoint(point);
		}
		
		for(int i=0; i<entities.size(); i++){
			
			Element3DEntity especimen = new Element3DEntity();
			
			especimen.setName("Especimen "+ i);
			ArrayList<Landmark> lms = entities.get(i).getLandmarks();
			int R = 128 + (int) (Math.random() * 128);
			int G = 128 + (int) (Math.random() * 128);
			int B = 128 + (int) (Math.random() * 128);
			Color entityColor = new Color(R, G, B); 
			for(int j=0; j<lms.size(); j++){
				String name = "E" + i + "-LM" + j;
				double[] coords = lms.get(j).getCoordinates();
				double zCoord = coords.length == 3 ? coords[2] : 0;
				Element3DPoint point = new Element3DPoint(coords[0], coords[1], zCoord);
				point.setFillColor(entityColor);
				point.setLineColor(entityColor);
				point.setText("");
				point.setRadius(3);
				point.setName(name);
				especimen.addPoint(point);
			}
			especimen.setBackColor(entityColor);
			especimen.setFillColor(entityColor);
			especimen.setLineColor(entityColor);
			this.entities3D.add(especimen);
		}
		this.result3D = entity;

	}
	
	

	public Element3DProcrustesResult() {
		super();
		entities = new ArrayList<SimpleMatrix>();
		entities3D = new ArrayList<Element3D>();
	}



	
	public String getDefinition() {
		// TODO Auto-generated method stub
		return "Dataset";
	}

	
	public Element createElement() {
		// TODO Auto-generated method stub
		ElementCollection ec = new ElementCollection();
		for(int i=0; i<entities3D.size(); i++){
			ec.addElement(entities3D.get(i).getElement());
		}
		this.elementContainer = true;
		return ec;
	}

	
	public Element createElement(Clip clip) {
		ElementCollection ec = new ElementCollection();
		if(T!=null)ec.transform(T);
		for(int i=0; i<entities3D.size(); i++){
			if(entities3D.get(i).isVisible())
				ec.addElement(entities3D.get(i).createElement(clip));
		}
		this.elementContainer = true;
		
		clip.getClippedElement(ec);
		return ec;
	}
	
//	public void addEntitie(Element3DEntity e){
//		entities.add(e);
//	}
	
	
	public Vector3D getMaxBound(){
		Vector3D maxbound = new Vector3D(0,0,0);
		for(int i=0; i<entities3D.size(); i++){
			Vector3D bound = entities3D.get(i).getMaxBound();
			if(bound.getLength() > maxbound.getLength())
				maxbound = bound;
		}
		return maxbound;
	}
	
	
	public Vector3D getMinBound(){
		Vector3D maxbound = new Vector3D(0,0,0);
		for(int i=0; i<entities3D.size(); i++){
			Vector3D bound = entities3D.get(i).getMaxBound();
			if(bound.getLength() < maxbound.getLength())
				maxbound = bound;
		}
		return maxbound;
	}
	
	
	public ArrayList<Element3D> getContainedElements(){
		
		return entities3D;
	}
	
	 
	public void setVisible(boolean visible){
		super.setVisible(visible);
		for(int i=0; i<entities3D.size(); i++){
			entities3D.get(i).setVisible(visible);
		}
	}

	public void addEntities(ArrayList<SimpleMatrix> subList) {
		entities = subList;
	}

	public ArrayList<SimpleMatrix> getEntities() {
		ArrayList<SimpleMatrix> aux = new ArrayList<SimpleMatrix>();
		for(int i=0; i<entities3D.size(); i++){
			aux.add(new SimpleMatrix(((Element3DEntity)entities3D.get(i)).toMatrix()));
		}
		return aux;
	}

}
