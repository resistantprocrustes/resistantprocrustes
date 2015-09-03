package com.rps.geometry3d;

import com.rps.math.AffineTransform3D;

public class ElementEntity extends Element {

	private Element[] points;
	
	
	
	public ElementEntity(Element[] points2) {
		points = points2;
	}



	
	public void transform(AffineTransform3D T){ 
		for(int i=0; i<points.length; i++){
			points[i].transform(T);
		}
		
	}

}
