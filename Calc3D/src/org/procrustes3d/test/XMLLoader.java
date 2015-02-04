package org.procrustes3d.test;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;
import org.procrustes3d.serialize.xml.ProjectSerializer;

import com.calc3d.app.elements.simpleelements.LandmarkSimpleElement;
import com.calc3d.app.elements.simpleelements.ProjectSimpleElement;

public class XMLLoader {

	@Test
	public void testParseProjectToXML() {
		ProjectSimpleElement project = new ProjectSimpleElement("proyecto");
		LandmarkSimpleElement lm1 = new LandmarkSimpleElement("lm1");
		lm1.addCoordinate(new double[]{1,2,3});
		project.addElement(lm1);
		try {
			ProjectSerializer.serialize(project, new BufferedOutputStream(
					new FileOutputStream("C:\\Users\\Lucas\\Desktop\\data.xml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
