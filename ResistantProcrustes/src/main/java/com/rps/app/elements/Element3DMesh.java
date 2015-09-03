package com.rps.app.elements;

import java.awt.Color;

import com.rps.geometry3d.Clip;
import com.rps.geometry3d.Element;
import com.rps.geometry3d.ElementCollection;
import com.rps.geometry3d.ElementPoly;
import com.rps.math.Vector3D;

public class Element3DMesh extends Element3D {

	Vector3D[] points;
	int[][] faces;
	
	public Element3DMesh( Vector3D[] p, int[][] f){
		points = p;
		faces = f;
	}
	
	
	
	public String getDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Element createElement() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Element createElement(Clip clip) {
		
		ElementCollection surface3D = new ElementCollection();
		for(int i = 0; i<faces.length; i++){
			ElementPoly element = new ElementPoly();
			element.addPoint(points[faces[i][0]]);
			element.addPoint(points[faces[i][1]]);
			element.addPoint(points[faces[i][2]]);
			element.setFilled(false);
		    element.setLineColor(getLineColor());
		    element.setCurveWidth(getCurveWidth());
		    if (null != clip) {
				ElementPoly clippoly = new ElementPoly();
				if (clip.getClippedPoly(element.vertices, clippoly.vertices) != 3) {
					clippoly.reCalculateNormalandCentre();
					clippoly.setFilled(false);
					clippoly.setFillColor(element.getFillColor());
					clippoly.setLineColor(Color.white);
					clippoly.setCurveWidth(element.getCurveWidth());
					if (getFillmode()==1)clippoly.setFilled(false); else clippoly.setFilled(true);
					clippoly.setSpilttable(isSplittable());
					clippoly.drawContours=true;
					if (clippoly.vertices.size()>2)surface3D.addElement(clippoly);
				}
			} else {
				surface3D.addElement(element);
			}
		}
		element = surface3D;
		return (surface3D.elements.size() > 0) ? surface3D : null;
	}

}
