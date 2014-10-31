package com.example.Algorithms.projections;

import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.ejml.simple.SimpleMatrix;


public interface ICalcProjection {
	public ArrayList<Vector3D> execute(SimpleMatrix distances, SimpleMatrix seeds);

}
