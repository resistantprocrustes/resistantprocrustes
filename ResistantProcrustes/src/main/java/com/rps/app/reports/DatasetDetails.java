package com.rps.app.reports;

import java.util.ArrayList;

import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;

public class DatasetDetails implements DataDetailer{

	
	public String getDetails(SimpleElement data) {
		String report = "";
		ComposeSimpleElement dataset = (ComposeSimpleElement)data;
		String name = data.getName();
		for(String key : dataset.getAllKeys()){
			if(key=="specimens"){
				ComposeSimpleElement specimens = (ComposeSimpleElement) dataset.getElementByKey(key);
				report += "Dataset has "+specimens.getAllElements().size()+" specimens"+'\n';
				SampleSimpleElement sample = (SampleSimpleElement)specimens.getContainedElement(0);
				report += "with " + (sample).size()+" landmarks"+'\n'+'\n'+'\n';
				
			}
		}
		return report;
	}
	
}
