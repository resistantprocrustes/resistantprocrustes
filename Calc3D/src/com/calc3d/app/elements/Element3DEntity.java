package com.calc3d.app.elements;

import java.util.ArrayList;

import com.calc3d.app.Globalsettings;
import com.calc3d.geometry3d.Clip;
import com.calc3d.geometry3d.Element;
import com.calc3d.geometry3d.ElementCollection;
import com.calc3d.geometry3d.ElementPoint;
import com.calc3d.math.Vector3D;

public class Element3DEntity extends Element3D {

	private Element _entityElement;
	private ArrayList<Element3DPoint> _points;
 	
	public Element3DEntity(){
		_points = new ArrayList<Element3DPoint>();
	}
	
	public Element3DEntity(ArrayList<Element3DPoint> points){
		
//		_entityElement = new ElementEntity(points);
		_points = points;
		
		
	}
	
	
	
	@Override
	public String getDefinition() {
		
		return "entidad";
	}

	@Override
	public Element createElement() {
		ElementCollection ec = new ElementCollection();
		for(int i=0; i<_points.size(); i++){
			ec.addElement(_points.get(i).getElement());
		}
		this.elementContainer = true;
		return ec;
	}

	@Override
	public Element createElement(Clip clip) {
		ElementCollection ec = new ElementCollection();
		if(T!=null)ec.transform(T);
		for(int i=0; i<_points.size(); i++){
			Vector3D p = _points.get(i).getPoint();
			Vector3D tmpPoint = new Vector3D(Globalsettings.inverseMapX(p.getX()),Globalsettings.inverseMapY(p.getY()),Globalsettings.inverseMapZ(p.getZ()));
			ElementPoint npoint = new ElementPoint(tmpPoint); 
			npoint.setBackColor(this.backColor);
			npoint.setFillColor(this.fillColor);
			npoint.setRadius(5);
//			npoint.setText(p.getPointText());
//			npoint.setVisible(this.isVisible());
			ec.addElement(npoint);
		}
		this.elementContainer = true;
		
		clip.getClippedElement(ec);
		return ec;
	}
	
	public void addPoint(Element3DPoint p){
		_points.add(p);
	}
	
	@Override
	public Vector3D getMaxBound(){
		double maxX, maxY, maxZ;
		maxX = maxY = maxZ = 0;
		for(int i=0; i<_points.size(); i++){
			Vector3D point = _points.get(i).getPoint();
			maxX = maxX < point.getX() ? point.getX() : maxX;
			maxY = maxY < point.getY() ? point.getY() : maxY;
			maxZ = maxZ < point.getZ() ? point.getZ() : maxZ;
		}
		return new Vector3D(maxX, maxY, maxZ);
	}
	
	@Override
	public Vector3D getMinBound(){
		double minX, minY, minZ;
		minX = minY = minZ = 0;
		for(int i=0; i<_points.size(); i++){
			Vector3D point = _points.get(i).getPoint();
			minX = minX > point.getX() ? point.getX() : minX;
			minY = minY > point.getY() ? point.getY() : minY;
			minZ = minZ > point.getZ() ? point.getZ() : minZ;
		}
		return new Vector3D(minX, minY, minZ);
	}
	
	@Override
	public ArrayList<Element3D> getContainedElements(){
		ArrayList<Element3D> list = new ArrayList<Element3D>();
		for(int i =0; i<_points.size(); i++){
			list.add(_points.get(i));
		}
		return list;
	}
	
	@Override 
	public void setVisible(boolean visible){
		super.setVisible(visible);
		for(int i=0; i<_points.size(); i++){
			_points.get(i).setVisible(visible);
		}
	}
	
	
	
}
