package org.procrustes3d.serialize.xml;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.OutputStream;

import com.calc3d.app.elements.simpleelements.ProjectSimpleElement;

public class ProjectSerializer {

	public static void serialize(ProjectSimpleElement project, OutputStream source){
		XMLEncoder encoder = new XMLEncoder(source);
		encoder.setExceptionListener(new ExceptionListener(
				) {
			
			@Override
			public void exceptionThrown(Exception e) {
				System.err.println("Tengo la excepcion: "+e);
				e.printStackTrace();
				
			}
		});
		encoder.writeObject(project);
		encoder.close();
		
	}
	
	public SimpleElement unserialize(OutputStream source){
		XMLDecoder decoder = 
	}
}
