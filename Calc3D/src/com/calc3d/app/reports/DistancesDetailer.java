package com.calc3d.app.reports;

import java.util.ArrayList;



import org.ejml.simple.SimpleMatrix;

import com.calc3d.app.elements.simpleelements.*;

public class DistancesDetailer implements DataDetailer{
	
	
	
	
	@Override
	public String getDetails(SimpleElement data) {
		String report = "";
		
		ArrayList<DistanceSimpleElement> distances= (ArrayList<DistanceSimpleElement>) ((ComposeSimpleElement) data).getAllElements();
		int numberOfElements = this.getCountOfElements(distances.size());
		String names = "";
		double[][] distArrayMat = new double[numberOfElements][numberOfElements];
		int count = 0; 
		for(int i=0; i<numberOfElements; i++){
			names += distances.size()==0?"":distances.get(0).getElementA().getName()+ "   ";
			for(int j=i; j<numberOfElements; j++){
				if(i!=j){
					DistanceSimpleElement distance = distances.remove(0); 
					distArrayMat[i][j] = distance.getDistance();
					distArrayMat[j][i] = distance.getDistance();
				}
				else{
					distArrayMat[i][j] = 0;
					distArrayMat[j][i] = 0;
				}
					
			}
		}
		names += ((ComposeSimpleElement) data).size()==0?"":((DistanceSimpleElement)((ComposeSimpleElement) data).getContainedElement(((ComposeSimpleElement) data).size()-1)).getElementA().getName();
		
		report += "Count of elements: "+numberOfElements+'\n';
		report += "Order of elements: \n";
		report += names;
		report += "Distances Matrix: \n";
		report += new SimpleMatrix(distArrayMat).toString();
		return report+"\n\n\n";
	}

	private int getCountOfElements(int size) {
		int result = 0;
		boolean cont = true;
		while(cont){
			if((result*(result-1))/2 == size)
				cont=false;
			else
				result++;
		}
		return result;
	}

}
