package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import com.example.Algorithms.CustomMatrixUtils;
import com.example.loaders.FileLoader;
import com.example.loaders.ILoadedDocument;
import com.example.loaders.PCEntity;
import com.example.loaders.TpsInterpreter;
import com.example.utils.CommonUtils;

public class MatrixCommonsTest {


	
	@Test
	public void testMedianWith2DMatrix(){
		//Arra
		double[][] m = new double[][]{{0, 1, 1},  {2, 3, 2}, {1, 3, 2}, {4, 2, 2}};
		SimpleMatrix matrix = new SimpleMatrix(m);
		//Act
		SimpleMatrix result = CustomMatrixUtils.median(matrix);
//		Assert
		assertEquals(result.numRows(), 1);
		assertEquals(result.numCols(), matrix.numCols());
		assertArrayEquals(ArrayUtils.toObject(result.getMatrix().getData()), ArrayUtils.toObject(new double[] {1.5, 2.5, 2.0}));
 	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMedianOfArray(){
		double[] m = new double[]{0, 1, 1};
		
		double result = CustomMatrixUtils.median(m);
		
		assertEquals(1.0, result, 0.001);
	}
	
	@Test
	public void  testCrossPRoductVectors(){
		double[] a = new double[]{4, -2, 1};
		double[] b = new double[]{1, -1, 3};
		
		double[] result = CustomMatrixUtils.crossProduct(a, b);
		
		assertArrayEquals(new double[]{-5, -11, -2}, result, 0.000001);
	}
	
	@Test
	public void testeigenFunctions(){
		double[][] mat = new double[][]{{1,2,3},{3,1,2},{2,3,1}};
		
		SimpleMatrix s = CommonUtils.ejerot(new SimpleMatrix(mat));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testNullSpaceOfMatrix(){
		SimpleMatrix m = new SimpleMatrix(new double[][]
				{{1,2,3},{1,2,3},{1,2,3}});
		
		SimpleMatrix res = CustomMatrixUtils.nullSpace(m);

		SimpleMatrix multRes = m.mult(new SimpleMatrix(res));
		
		
		assertEquals(multRes.elementSum(), 0, 0.0001);
		
	}
	
	@Test
	public void testMedLand(){
		FileLoader loader = new FileLoader(new TpsInterpreter());
		ILoadedDocument doc = loader.Load("./resurces/prueba.tps"); 
		ArrayList<PCEntity> entities = doc.getEntitiesList();
		ArrayList<SimpleMatrix> matrix = new ArrayList<SimpleMatrix>();
		for(PCEntity entity : entities){
			SimpleMatrix matAux = new SimpleMatrix(entity.toMatrix());
			matrix.add(matAux);
		}
		double m = CommonUtils.medland(matrix.get(0), 1);
		System.out.println(m);
		
		
	}
	


}
