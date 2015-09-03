package com.rps.app.elements;

import java.awt.Color;


import com.rps.app.LocalSettings;
import com.rps.geometry3d.Clip;
import com.rps.geometry3d.Element;
import com.rps.geometry3d.ElementPoint;
import com.rps.math.Vector3D;

/**
 * Class for Elements3D representing point in 3D space
 * 
 * @author mahesh
 * 
 */
public class Element3DPoint extends Element3D {


	private static final long serialVersionUID = -7718288181668445927L;
	private Vector3D point;
	private int radius=4;
	private String text;
	public Element3DPoint(double x, double y, double z) {
		element = new ElementPoint(x, y, z);
		point=new Vector3D(x,y,z);
	}

	public Element3DPoint(Vector3D point) {
		element = new ElementPoint(point);
		this.point=new Vector3D(point);
	}
	
	public Element3DPoint(Vector3D point, String name,Color color) {
		element = new ElementPoint(point,name,color);
		this.point=new Vector3D(point);
		setFillColor(color);
		setLineColor(color);
		super.setName(name);
	}
	
	
	public String getDefinition() {
		return "(" +point.getX() + ","
				+ point.getY() + "," +point.getZ()
				+ ")";
	}
    
	
	public void setName(String name){
		super.setName(name);
		((ElementPoint)element).setText(name);
	}

	public Vector3D getPoint() {
		// TODO Auto-generated method stub
		return point;
	}
	
	public void setPoint(Vector3D point) {
		this.point=new Vector3D(point);
		element=new ElementPoint(point); 
	}

	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		if (radius>=1) this.radius=radius;
	}

	
	public Element createElement() {
		Vector3D pt=new Vector3D(this.settings.inverseMapX(point.getX()),settings.inverseMapY(point.getY()),settings.inverseMapZ(point.getZ()));
		ElementPoint e=new ElementPoint(pt);
		e.setText(getText());
		element=e;
		element.setFillColor(getFillColor());
	    element.setLineColor(getLineColor());
	    element.setCurveWidth(getCurveWidth());
	    if (radius>=1) ((ElementPoint)element).setRadius(radius);
	    element.setDashed(isDashed());
	    isCreated=true;
		return element;
	}

	
	public Element createElement(Clip clip) {
		if(settings != null){
		Vector3D pt=new Vector3D(settings.inverseMapX(point.getX()),settings.inverseMapY(point.getY()),settings.inverseMapZ(point.getZ()));
		ElementPoint e=new ElementPoint(pt);
		e.setText(getText());
		if(T!=null)e.transform(T);
		element=clip.getClippedElement(e);
		if (null==element) return null;
		element.setFillColor(getFillColor());
	    element.setLineColor(getLineColor());
	    element.setCurveWidth(getCurveWidth());
	    element.setDashed(isDashed());
	    if (radius>=1) ((ElementPoint)element).setRadius(radius);
	    isCreated=true;
		}
		return element;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public Vector3D getMaxBound(){
		return point;
	}
	
	public Vector3D getMinBound(){
		return point;
	}
	
	
	public void select(boolean b){
		if(b){
			this.radius += 1;
		}else
			this.radius -= 1;
	}
}

/**
 * Class for Elements3D representing Line in 3D space
 * 
 * @author mahesh
 * 
 */