package com.rps.app.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.LandmarkSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;

public class NtsExporter implements IExporter {

	
	public void export(SimpleElement element, String source) {
		File file = new File(source);
		FileWriter fw;
	
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw  = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			ComposeSimpleElement specimens = (ComposeSimpleElement) ((ComposeSimpleElement)element).getElementByKey("specimens");
			int countSpecimens = specimens.size(),
					dimentions = ((SampleSimpleElement)specimens.getContainedElement(0)).getDimension(),
					countLM = ((SampleSimpleElement)specimens.getContainedElement(0)).getAllElements().size();
			
			String head = "1 "+countSpecimens+"L "+countLM*dimentions+(dimentions==2?"":" Dim=3"),
					matrix="",ids="";
			
			for(SimpleElement elem : specimens.getAllElements()){
				SampleSimpleElement sample = (SampleSimpleElement) elem;
				int sampleDimension = sample.getDimension();
				
				for(SimpleElement landmark : sample.getAllElements()){
					matrix +=(sampleDimension ==3?
							((LandmarkSimpleElement)landmark).toString() + '\n':
							((LandmarkSimpleElement)landmark).toString2D()+ '\n');
				}
				matrix += '\n';
				ids += sample.getName()+'\t';
				
			}
			fw.write(head+'\n');
			fw.write(ids+'\n');
			fw.write(matrix);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
