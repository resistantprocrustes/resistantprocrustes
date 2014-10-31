package com.example.Algorithms;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

public interface IProcrustesCalculator {

	public ArrayList<SimpleMatrix> execute(ArrayList<SimpleMatrix> entitiesMat); 
}
