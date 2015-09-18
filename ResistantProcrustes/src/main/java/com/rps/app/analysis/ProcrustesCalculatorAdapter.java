package com.rps.app.analysis;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.ejml.simple.SimpleMatrix;
import org.jdesktop.swingx.autocomplete.ListAdaptor;

import com.rps.app.analysis.thread.ProcrustesAnalisysWorker;
import com.rps.app.elements.dataset.DataSet;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.resources.Messages;
import com.example.Algorithms.CM;
import com.example.Algorithms.ProcrustesCalculator;
import com.example.Algorithms.Robusto;
import com.example.loaders.PCEntity;
import com.procrustes.Utils.Commons;

public class ProcrustesCalculatorAdapter {

	AnalysisConfiguration configuration;
	private ArrayList<Observer> subscriber;

	public ProcrustesCalculatorAdapter() {
	}

	public void setConfiguration(AnalysisConfiguration configuration2) {
		this.configuration = configuration2;
		this.subscriber = new ArrayList<Observer>();
	}

	public ComposeSimpleElement calculate(ArrayList<SampleSimpleElement> elems) {
		ProcrustesCalculator calculator = getCalculator();
		for (Observer o : this.subscriber) {
			((Observable) calculator).addObserver(o);
		}
		ArrayList<SimpleMatrix> elements = new ArrayList<SimpleMatrix>();
		for (int i = 0; i < elems.size(); i++) {
			SampleSimpleElement entity = elems.get(i);
			elements.add(new SimpleMatrix(entity.toMatrix()));
		}
		ArrayList<SimpleMatrix> result = calculator.execute(elements);
		// String prefix = configuration.getType() ==
		// AnalysisConfiguration.MIN_SQUARES_FIT ? "GLSP-" : "GRP-";
		ComposeSimpleElement dataset = new ComposeSimpleElement(
				configuration.getName());

		dataset.addElement(Commons.toPCEntity(result, elems));
		if (result.size() != 0) {
			SimpleMatrix consensus = result.get(result.size() - 1);
			dataset.addElement(new SampleSimpleElement("Consensus", consensus));
		}
		return dataset;

	}

	private ProcrustesCalculator getCalculator() {
		if (configuration.getType() == AnalysisConfiguration.MIN_SQUARES_FIT)
			return new CM();
		else if (configuration.getType() == AnalysisConfiguration.ROBUST_FIT)
			return new Robusto();
		return null;
	}

	public AnalysisConfiguration getConfiguration() {

		return this.configuration;
	}

	public void addSubscriber(ProcrustesAnalisysWorker procrustesAnalisysWorker) {
		this.subscriber.add(procrustesAnalisysWorker);

	}

}