package com.rps.app.reports;

import java.util.ArrayList;

import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.LandmarkSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;

public class ProcrustesFitDetalier implements DataDetailer {

	
	public String getDetails(SimpleElement data) {
		String report = "";
		ComposeSimpleElement dataset = (ComposeSimpleElement) data;
		ComposeSimpleElement specimens = (ComposeSimpleElement)dataset.getElementByKey("specimens");
		SampleSimpleElement consensus = (SampleSimpleElement)dataset.getElementByKey("consensus");
		
		if(specimens!= null){
			report+= "Superimposed Configurations"+'\n';
			for(int i=0; i<specimens.size(); i++){
				ComposeSimpleElement sample = (ComposeSimpleElement)specimens.getContainedElement(i);
				report += "----------------------------------------------------------------------------------------------------------------------------------------------"+'\n';
				report += "SAMPLE: " + sample.getName()+'\n';
				report += "----------------------------------------------------------------------------------------------------------------------------------------------"+'\n';
				ArrayList<LandmarkSimpleElement> landmarks = (ArrayList<LandmarkSimpleElement>) sample.getAllElements();
				for(int j=0; j<landmarks.size(); j++){
					LandmarkSimpleElement landmark = landmarks.get(j);
					report += landmark.getCoords()[0] + "		" + landmark.getCoords()[1] + "		" +
							landmark.getCoords()[2]+'\n';
					
				}
			}
		}
		
		if(consensus != null){
			report += "----------------------------------------------------------------------------------------------------------------------------------------------"+'\n';
			report += "CONSENSUS: " + consensus.getName()+'\n';
			report += "----------------------------------------------------------------------------------------------------------------------------------------------"+'\n';
			
			ArrayList<LandmarkSimpleElement> landmarks = (ArrayList<LandmarkSimpleElement>) consensus.getAllElements();
			for(int j=0; j<landmarks.size(); j++){
				LandmarkSimpleElement landmark = landmarks.get(j);
				report += landmark.getCoords()[0] + "		" + landmark.getCoords()[1] + "		" +
						landmark.getCoords()[2]+'\n';
				
			}
		}
		return report;
	}

}
