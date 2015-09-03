package com.example.Algorithms.projections;

import java.io.ObjectInputStream.GetField;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.simple.SimpleMatrix;

import com.example.Algorithms.CustomMatrixUtils;
import com.example.utils.CommonUtils;

public class ProjectionUtils {

	public static SimpleMatrix distAllPairsL2(SimpleMatrix x) {
		SimpleMatrix Q = x.transpose().mult(x);
		int n = Q.numRows();
		SimpleMatrix a = CustomMatrixUtils.powElements(x.transpose(), 2);
		a = CustomMatrixUtils.sumRows(a);
		SimpleMatrix normx = CustomMatrixUtils.repeatHorMat(a, n);
		
		SimpleMatrix aux = Q.scale(-2).plus(normx).plus(normx.transpose());
		SimpleMatrix K = CustomMatrixUtils.sqrt(aux);

		SimpleMatrix K1 = CustomMatrixUtils.createDiag(K.extractDiag().getMatrix().data); 
		
		return K.minus(K1);
		
	}

	public static SimpleMatrix computeIntersections(SimpleMatrix x, int ii,
			SimpleMatrix distances) {
		int k = x.numRows();
		int n = x.numCols();
		
		SimpleMatrix V = x.minus(CustomMatrixUtils.repeatHorMat(x.extractVector(false, ii), n));
		DenseMatrix64F aux = new DenseMatrix64F(V.numRows(), V.numCols());
		CommonOps.elementMult(V.getMatrix(), V.getMatrix(), aux ); 
		SimpleMatrix Daux = CustomMatrixUtils.sqrt(CustomMatrixUtils.sumCols(new SimpleMatrix(aux))).transpose();
		SimpleMatrix divAux1 = distances.extractVector(true, ii).transpose();
		aux = new DenseMatrix64F(divAux1.numRows(), Daux.numCols());
		CommonOps.elementDiv(divAux1.getMatrix(), Daux.getMatrix(), aux);
		SimpleMatrix Q = new SimpleMatrix(aux);
		Q = CustomMatrixUtils.checkNans(Q);
		
		divAux1 = CustomMatrixUtils.repeatVerMat(Q.transpose(), k);
		aux = new DenseMatrix64F(V.numRows(), divAux1.numCols());
		CommonOps.elementMult(V.getMatrix(), divAux1.getMatrix(), aux);
		SimpleMatrix Z = x.minus(new SimpleMatrix(aux));
		return CustomMatrixUtils.deleteColumn(Z, ii);
	}

}
