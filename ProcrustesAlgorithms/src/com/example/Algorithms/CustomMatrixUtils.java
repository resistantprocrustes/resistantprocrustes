
package com.example.Algorithms;

import java.lang.reflect.Array;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.SingularOps;
import org.ejml.simple.SimpleBase;
import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.SimpleSVD;
import org.apache.commons.math3.stat.descriptive.moment.*;
/**
 * 
 * @author Lucas Marquez
 *	Email: lucasasaecas@gmailcom 		
 *
 */


public class CustomMatrixUtils {

	public static double[][] rowToArray(SimpleMatrix extractVector) {
		int rows = extractVector.numRows();
		int cols = extractVector.numCols();
		double[][] aux = new double[rows][cols];
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				aux[i][j] = extractVector.get(i,j);
			}
		}
		return aux;
	}
	

	/**
	 * 
	 * @param matrix
	 * @return 
	 */
	public static SimpleMatrix median(SimpleMatrix matrix){
		double[][] result = new double[1][matrix.numCols()];
		Median median = new Median();
		for(int i=0; i<matrix.numCols(); i++){
			double[] column = matrix.extractVector(false, i).getMatrix().getData();
			double value = median.evaluate(column);
			result[0][i] = value;
		}
		
		return new SimpleMatrix(result);
	}


	public static double median(double[] m) {
		Median median = new Median();
		double result = median.evaluate(m);
		return median.evaluate(m);
	}


	public static SimpleMatrix Mean(SimpleMatrix a) {
		Mean mean = new Mean();
		double[][] result = new double[1][a.numCols()];
		for(int i=0; i<a.numCols(); i++){
			double[] aux = a.extractVector(false, i).getMatrix().getData();
			result[0][i] = mean.evaluate(aux);
		}
		return new SimpleMatrix(result);
	}

	public static SimpleMatrix Mean2(SimpleMatrix a) {
		Mean mean = new Mean();
		double[][] result = new double[a.numRows()][1];
		for(int i=0; i<a.numRows(); i++){
			result[i][0] = mean.evaluate(a.extractVector(true, i).getMatrix().getData());
		}
		return new SimpleMatrix(result);
	}

	public static SimpleMatrix sindiagonal(SimpleMatrix B) {
		int f = B.numRows();
		int c = B.numCols()-1;
		SimpleMatrix A = MatrixConstructor.create(0, f, c);
		
		for(int i=0; i<f; i++){
			for(int j=0; j<c; j++){
				if(i<=j){
					A.set(i, j, B.get(i, j+1));
				}
				else
					A.set(i, j, B.get(i, j));
			}
		}
		return A;
	}


	public static double[] crossProduct(double[] a, double[] b) {
		Vector3D x = new Vector3D(a);
		Vector3D result = x.crossProduct(new Vector3D(b));
		return result.toArray();
	}


	public static SimpleMatrix nullSpace(SimpleMatrix m) {
		SimpleSVD svd = m.svd();
		SimpleMatrix res =  svd.nullSpace();
		SimpleMatrix aux = m.mult(res);
		return res;
	}


	public static SimpleMatrix powElements(SimpleMatrix transpose, int n) {
		DenseMatrix64F mat = transpose.getMatrix();
		for(int i=0; i<mat.getNumElements(); i++){
			mat.set(i, Math.pow(mat.get(i), n));
		}
		return new SimpleMatrix(mat);
	}


	public static SimpleMatrix sumRows(SimpleMatrix a) {
		double[][] aux = new double[a.numRows()][1];
		for(int i=0; i<a.numRows(); i++){
			for(int j=0; j<a.numCols(); j++){
				aux[i][0] += a.get(i, j); 
			}
		}
		// TODO Auto-generated method stub
		return new SimpleMatrix(aux);
	}
	
	public static SimpleMatrix sumCols(SimpleMatrix a) {
		double[][] aux = new double[1][a.numCols()];
		for(int i=0; i<a.numRows(); i++){
			for(int j=0; j<a.numCols(); j++){
				aux[0][j] += a.get(i, j); 
			}
		}
		// TODO Auto-generated method stub
		return new SimpleMatrix(aux);
	}


	public static SimpleMatrix repeatHorMat(SimpleMatrix a, int n) {
		double[][] res = new double[a.numRows()][a.numCols() * n];
		for(int i=0; i<a.numRows(); i++){
			for(int j=0; j<a.numCols() * n; j++){
				int col = j % a.numCols();
				res[i][j] = a.get(i, col);
			}
		}
		
		return new SimpleMatrix(res);
		
	}


	public static SimpleMatrix sqrt(SimpleMatrix a) {
		double[][] res = new double[a.numRows()][a.numCols()];
		for(int i=0; i<a.numRows(); i++){
			for(int j=0; j<a.numCols(); j++){
				Complex c = new Complex(a.get(i, j));
				res[i][j] = c.sqrt().getReal();
			}
		}
		
		return new SimpleMatrix(res);
	
	
	}


	public static SimpleMatrix createDiag(double[] data) {
		int size = data.length;
		double[][] a = new double[size][size];
		
		for(int i =0; i<size; i++)
			for(int j=0; j<size; j++)
				a[i][j] = i == j ? data[i] : 0; 
		return new SimpleMatrix(a);
	}


	public static SimpleMatrix repeatVerMat(SimpleMatrix a,
			int n) {
		double[][] res = new double[a.numRows() * n][a.numCols()];
		for(int i=0; i<a.numRows() * n; i++){
			for(int j=0; j<a.numCols(); j++){
				int fil = i % a.numRows();
				res[i][j] = a.get(fil, j);
			}
		}
		
		return new SimpleMatrix(res);
	}


	public static SimpleMatrix checkNans(SimpleMatrix q) {
		SimpleMatrix a = new SimpleMatrix(q.getMatrix());
		for(int i=0; i<q.numRows(); i++){
			for(int j=0; j<q.numCols(); j++){
				if(new Double(q.get(i,j)).isNaN())
					a.set(i,j,0);
			}
		}
		return a;
	}


	public static SimpleMatrix deleteColumn(SimpleMatrix z, int ii) {
		// TODO Auto-generated method stub
		double[][] res = new double[z.numRows()][z.numCols()-1];
		for(int i=0; i<z.numRows(); i++){
			for(int j=0; j<z.numRows(); j++){
				if(j<ii)
					res[i][j] = z.get(i,j);
				if(j>ii)
					res[i][j-1] = z.get(i,j);
			}
		}
		return new SimpleMatrix(res);
	}

}
