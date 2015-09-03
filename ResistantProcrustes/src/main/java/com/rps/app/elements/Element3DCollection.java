package com.rps.app.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.rps.app.LocalSettings;
import com.rps.geometry3d.Clip;
import com.rps.geometry3d.Element;
import com.rps.geometry3d.ElementCollection;
import com.rps.math.Vector3D;

public class Element3DCollection extends Element3D implements Collection {
	
	ArrayList<Element3D> elements = new ArrayList<Element3D>();

	public Element3DCollection(){}
	
	/**
	 * @param elements
	 */
	public Element3DCollection(ArrayList<Element3D> elements) {
		super();
		this.elements = elements;
	}

	
	public String getDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Element createElement() {
		ElementCollection ec = new ElementCollection();
		for(int i=0; i<elements.size(); i++){
			ec.addElement(elements.get(i).getElement());
		}
		this.elementContainer = true;
		return ec;
	}

	
	public Element createElement(Clip clip) {
		ElementCollection ec = new ElementCollection();
		if(T!=null)ec.transform(T);
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).isVisible())
				ec.addElement(elements.get(i).createElement(clip));
		}
		this.elementContainer = true;
		
		Element elem = clip.getClippedElement(ec);
		return elem;
	}

	
	public boolean add(Object e) {
		elements.add((Element3D)e);
		this.refresh();
		return true;
	}

	private void refresh() {
		this.setSettings(this.settings);
		
	}

	
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	public ArrayList<Element3D> getContainedElements(){
		return elements;
	}
	
	 
	public void setVisible(boolean visible){
		super.setVisible(visible);
		for(int i=0; i<elements.size(); i++){
			elements.get(i).setVisible(visible);
		}
	}
	
	
	public void select(boolean b){
		for(int i=0; i<elements.size(); i++){
			elements.get(i).select(b);
		}
	}
	
	
	public void setSettings(LocalSettings settings){
		for(Element3D element3D : this.elements)
			element3D.setSettings(settings);
		this.settings = settings;
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
	
	
	
}
