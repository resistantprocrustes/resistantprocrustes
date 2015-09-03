package com.rps.app.analysis.thread;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.ejml.simple.SimpleMatrix;

import com.rps.app.CopyOfGui;
import com.rps.app.analysis.ProcrustesCalculatorAdapter;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.example.Algorithms.ProcrustesCalculator;

public class ProcrustesAnalisysThread extends Thread {

	
	private JFrame currentWindow;
	private ProcrustesCalculatorAdapter calculator;
	private ArrayList<SampleSimpleElement> elems;
	private int tabIndex;

	public ProcrustesAnalisysThread(JFrame window, ProcrustesCalculatorAdapter calculator, ArrayList<SampleSimpleElement> specimens, int indexTab){
		this.currentWindow = window;
		this.calculator = calculator;
		this.elems = specimens;
		this.tabIndex = indexTab;
	}
	
	
	public void run(){
		ComposeSimpleElement result = calculator.calculate(elems);
		((CopyOfGui)currentWindow).addProcrustesAnalisys(result, calculator.getConfiguration(), this.tabIndex);
	}
}
