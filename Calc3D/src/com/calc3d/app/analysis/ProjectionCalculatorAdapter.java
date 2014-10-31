package com.calc3d.app.analysis;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.ejml.simple.SimpleMatrix;

import com.calc3d.app.elements.simpleelements.ComposeSimpleElement;
import com.calc3d.app.elements.simpleelements.DistanceSimpleElement;
import com.calc3d.app.elements.simpleelements.LandmarkSimpleElement;
import com.calc3d.app.elements.simpleelements.SampleSimpleElement;
import com.calc3d.app.elements.simpleelements.SimpleElement;
import com.example.Algorithms.projections.EuclideanProjection;
import com.example.Algorithms.projections.ICalcProjection;
import com.example.Algorithms.projections.RobustProjection;

public class ProjectionCalculatorAdapter {

	ProjectionConfiguration configuration;

	public ProjectionCalculatorAdapter(ProjectionConfiguration configuration2) {
		this.configuration = configuration2;
	}

	public ComposeSimpleElement calculate(
			ComposeSimpleElement distances) {
		ArrayList<DistanceSimpleElement> dist = (ArrayList<DistanceSimpleElement>) distances.getAllElements();
		int distSizes = distances.getElementByKey("projections")==null?dist.size():dist.size()-1;
		SimpleMatrix distancesMat;
		int counter = 0;
		while(true){
			double sum = counter * (counter-1) / 2;
			if(sum == distSizes) break;
			counter++;
			
		}
		double[][] distArrayMat = new double[counter][counter];
		int count = 0;
		for(int i=0; i<counter; i++){
			int j=0;
			while(j!=i){
				DistanceSimpleElement distance = dist.get(count);
				count++;
				distArrayMat[i][j] = distance.getDistance();
				distArrayMat[j][i]= distance.getDistance();
				j++;
			}
			distArrayMat[i][j]=0;
			
		}
		
		ICalcProjection calculator = this.getCalculator();
	 	ArrayList<Vector3D> resultRaw = calculator.execute(new SimpleMatrix(distArrayMat), SimpleMatrix.random(counter, configuration.getDimensions(), 0, 1, new Random()));
		
	 	ComposeSimpleElement result = new ComposeSimpleElement("projection");
	 	for(int i=0; i<resultRaw.size(); i++){
	 		LandmarkSimpleElement lm = new LandmarkSimpleElement("sample-"+i);
	 		lm.addCoordinate(resultRaw.get(i).toArray());
	 		result.addElement(lm);
	 	}
	 	result.setName(configuration.getName());
	 	
		return result;
	}

	private ICalcProjection getCalculator() {
		if(configuration.getType() == ProjectionConfiguration.LEAST_SQR_PROJETION){
			return new EuclideanProjection();
		}else if(configuration.getType() == ProjectionConfiguration.ROBUST_PROJECTION){
			return new RobustProjection();
		}
		return null;
	}

}
