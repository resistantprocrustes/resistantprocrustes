import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.util.Pair;
import org.ejml.data.Complex64F;
import org.ejml.simple.SimpleEVD;
import org.ejml.simple.SimpleMatrix;

import com.example.Algorithms.CM;
import com.example.Algorithms.CustomMatrixUtils;
import com.example.Algorithms.Robusto;
import com.example.Algorithms.distances.MediumRepitedDistances;
import com.example.Algorithms.projections.EuclideanProjection;
import com.example.Algorithms.projections.ProjectionUtils;
import com.example.Algorithms.projections.RobustProjection;
import com.example.loaders.FileLoader;
import com.example.loaders.ILoadedDocument;
import com.example.loaders.PCEntity;
import com.example.loaders.TpsInterpreter;
import com.example.loaders.TpsLoadedDocument;
import com.example.tests.performance.TesteMatrixPerformance;


public class Main {

	public static void main(String[] args) {
		
//		double[][] x = {
//		
//				{0, 0},
//				{1, 0},
//				{0, 1},
//				{1, 1}
//		
//		};
//		
//		double[][] y = {
//				
//				{0, 0},
//				{1, 0},
//				{0, 1},
//				{3, 3}
//		
//		};
//		
//		SimpleMatrix X = new SimpleMatrix(x);
//		SimpleMatrix Y = new SimpleMatrix(y);
//		System.out.println("X: "+'\n'+X);
//		System.out.println("Y: "+'\n'+Y);
//		Robusto rb = new Robusto();
//		Pair<SimpleMatrix, SimpleMatrix> r = rb.rob2D(X,Y);
//		System.out.println("X: "+'\n'+r.getFirst());
//		System.out.println("Y: "+'\n'+r.getSecond());
		
		FileLoader loader = new FileLoader(new TpsInterpreter());

		ILoadedDocument doc = loader.Load("./resurces/data.tps"); 
		ArrayList<PCEntity> entities = doc.getEntitiesList();
		ArrayList<SimpleMatrix> matrix = new ArrayList<SimpleMatrix>();
		for(PCEntity entity : entities){
			SimpleMatrix matAux = new SimpleMatrix(entity.toMatrix());
			matrix.add(matAux);
		}
		Robusto cm = new Robusto();
		
//		doc = loader.Load("./resurces/prueba2.tps");
//		entities = doc.getEntitiesList();
//		ArrayList<SimpleMatrix> matrix2 = new ArrayList<SimpleMatrix>();
//		for(PCEntity entity : entities){
//			SimpleMatrix matAux = new SimpleMatrix(entity.toMatrix());
//			matrix2.add(matAux);
//		}
//		
////		TesteMatrixPerformance test = new TesteMatrixPerformance();
////		test.test();
//		Robusto cm = new Robusto();
		ArrayList<SimpleMatrix> m =cm.execute(matrix);
		m.toString();
//		IDistanceCalculator dCalc = new MediumRepitedDistances();
//		ArrayList<SimpleMatrix> subM = new ArrayList<SimpleMatrix>(m.subList(0, m.size()-2));
//		double a[][] = dCalc.calculate(m);
//		
//		//cm.execute(matrix2);
//	}
//	
//		
//		double[][] a = {
//				{0, 1.4142, 5.4772},
//			    {1.4142, 0, 6.1644},
//			    {5.4772, 6.1644, 0}
//		};
//		Random r = new Random();
//		double[][] X0 = new double[a[0].length][a[0].length];
//		SimpleMatrix rMat = SimpleMatrix.random(a[0].length, a[0].length, 0, 1, r);
//		for(int i=0; i<a[0].length;i++){
//			for(int j=0; j<a[0].length;j++){
//				X0[i][j] = rMat.get(i,j);
//			}
//		}
//		double [][] X0= {
//				{0.8147, 0.9134, 0.2785},
//			    {0.9058, 0.6324, 0.5469},
//			    {0.1270, 0.0975, 0.9575}
//    	};
		
//		double[][] X0 = {
//				 {0.9649,    0.9572},
//				 {0.1576,    0.4854},
//				 {0.9706,    0.8003}
//		};
//		
//		
//		
		
//		
//		EuclideanProjection ep = new EuclideanProjection();
//		RobustProjection rp = new RobustProjection(); 
//		ArrayList<Vector3D> result = ep.execute(new SimpleMatrix(a), new SimpleMatrix(X0));
//		System.out.println(result);
		
	

	}



}
