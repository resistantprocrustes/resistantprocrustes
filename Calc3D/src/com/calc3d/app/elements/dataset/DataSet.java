package com.calc3d.app.elements.dataset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import com.example.loaders.PCEntity;

public class DataSet {

	HashMap<String, Object> elements;
	private String name = "dataset";
	public DataSet(){
		elements = new HashMap<String, Object>();
	}
	
	public DataSet(HashMap<String, Object> elems){
		elements = elems;
	}
	
	public void addElement(String key, Object element){
		elements.put(key, element);
	}

	public ArrayList<Object> getAllElements() {
		return (ArrayList<Object>) elements.values();
	}

	public ArrayList<String> getAllElementNames() {
		Set<String> elemns = elements.keySet();
		ArrayList<String> aux = new ArrayList<String>();
		aux.addAll(elemns);
		return aux;
	}

	public Object getElement(String key) {
		return elements.get(key);
	}

	public void setName(String substring) {
		this.name = substring;
		
	}

	public String getName() {
		
		return name;
	}
	
	

}
