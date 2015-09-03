package com.rps.app.elements;

import java.awt.Color;
import java.util.ArrayList;

import com.rps.app.Globalsettings;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.LandmarkSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.geometry3d.Clip;
import com.rps.geometry3d.Element;
import com.rps.geometry3d.ElementCurve;
import com.rps.geometry3d.ElementPoint;
import com.rps.math.Vector3D;

/**
 * Class for Elements3D representing point in 3D space
 * 
 * @author Lucas Marquez (lucasasecas@gmail.com)
 * 
 */
public class Element3DWireframe extends Element3DCollection {

	/**
	 * 
	 */
	
	private Vector3D point;
	private String text;
	
	protected int[][] links;
	
	public Element3DWireframe(SampleSimpleElement specimen, int[][] links) {
		ArrayList<LandmarkSimpleElement> points = (ArrayList<LandmarkSimpleElement>) specimen.getAllElements();
		for(int i =0; i<links.length; i++){
			LandmarkSimpleElement lm1 = points.get(links[i][0]);
			LandmarkSimpleElement lm2 = points.get(links[i][1]);
			try {
				Element3DLineSegment segment = new Element3DLineSegment(new Vector3D(lm1.getCoords()), new Vector3D(lm2.getCoords()) );
				this.add(segment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	public String getDefinition() {
		return "(" +point.getX() + ","
				+ point.getY() + "," +point.getZ()
				+ ")";
	}
    
	
	public void setName(String name){
		super.setName(name);
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
	


}

