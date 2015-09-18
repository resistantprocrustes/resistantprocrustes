package com.rps.app.reports;

import java.util.ArrayList;

import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.LandmarkSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;

public class ProjectionDetailer implements DataDetailer{

	@Override
	public String getDetails(SimpleElement data) {
		String report="\n";
		ComposeSimpleElement projections = (ComposeSimpleElement) data;
		for(LandmarkSimpleElement lm : (ArrayList<LandmarkSimpleElement>)projections.getAllElements()){
			report +=lm.getName()+"\t"+ lm.toString()+'\n';
		}
		return report+"\n\n\n";
	}

}