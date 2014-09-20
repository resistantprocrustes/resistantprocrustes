package com.calc3d.app.analysis;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import com.calc3d.app.elements.simpleelements.ComposeSimpleElement;
import com.calc3d.app.elements.simpleelements.SimpleElement;
import com.example.loaders.PCEntity;

/**
 * @author Lucas
 *
 */
/**
 * @author Lucas
 *
 */
public class AnalysisConfiguration extends DialogConfiguration{
	public static int MIN_SQUARES_FIT = 0;
	public static int ROBUST_FIT = 1;
	
	private int type;
	private ArrayList<? extends SimpleElement> elements;
	private String name;
	/**
	 * @param type
	 * @param elements
	 */
	public AnalysisConfiguration(int type, ArrayList<SimpleElement> elements) {
		super();
		this.type = type;
		this.elements = elements;
	}
	/**
	 * 
	 */
	public AnalysisConfiguration() {
		super();
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public ArrayList<? extends SimpleElement> getElements() {
		return elements;
	}
	
	public void setElements(ArrayList<SimpleElement> arrayList) {
		this.elements = arrayList;
	}
	public void setName(String name) {
		this.name= name; 
	}
	
	public String getName(){
		return name;
	}
	
	
	
	

}
