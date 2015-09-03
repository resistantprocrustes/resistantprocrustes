package com.rps.app.reports;

import java.util.ArrayList;

import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.LandmarkSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;

public class ProjectionDetailer implements DataDetailer{

	
	public String getDetails(SimpleElement data) {
		String report="";
		ComposeSimpleElement projections = (ComposeSimpleElement) data;
		for(LandmarkSimpleElement lm : (ArrayList<LandmarkSimpleElement>)projections.getAllElements()){
			report +=lm.getName()+"    "+ lm.toString()+'\n';
		}
		return report+"\n\n\n";
	}

}
