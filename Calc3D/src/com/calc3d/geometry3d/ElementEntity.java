package com.calc3d.geometry3d;

import com.calc3d.math.AffineTransform3D;

public class ElementEntity extends Element {

	private Element[] points;
	
	
	
	public ElementEntity(Element[] points2) {
		points = points2;
	}



	@Override
	public void transform(AffineTransform3D T){ 
		for(int i=0; i<points.length; i++){
			points[i].transform(T);
		}
		
	}

}
