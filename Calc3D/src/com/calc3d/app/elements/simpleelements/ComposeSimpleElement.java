package com.calc3d.app.elements.simpleelements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.UIManager;

import com.example.loaders.PCEntity;

public class ComposeSimpleElement extends SimpleElement{

	LinkedHashMap<String, SimpleElement> elements;
	
	public ComposeSimpleElement(LinkedHashMap<String, SimpleElement> elems, String name){
		super(name);
		this.elements = elems;
	}

	public ComposeSimpleElement(String name) {
		super(name);
		this.elements = new LinkedHashMap<String, SimpleElement>();
	}

	public ComposeSimpleElement(){
		super();
		this.elements = new LinkedHashMap<String, SimpleElement>();
	}

	public ArrayList<? extends SimpleElement> getAllElements(){
		ArrayList<SimpleElement> aux = new ArrayList<SimpleElement>(elements.values());
		return aux;
	}

	public void addElement(String key, SimpleElement elem) {
		elements.put(key,elem);
	}
	
	public void addElement(SimpleElement elem){
		elements.put(elem.getName(), elem);
	}
	
	public void addAllElement(ArrayList< ? extends SimpleElement> elems) {
		for(int i=0; i<elems.size(); i++){
			SimpleElement elem = elems.get(i); 
			elements.put(elem.getName(), elem);
		}
		
	}
	
	public LinkedHashMap<String, SimpleElement> getElements(){
		return elements;
	}
	
	public void setElements(LinkedHashMap<String, SimpleElement> elements1){
		this.elements=elements1;
	}
	
	public SimpleElement getContainedElement(int index){
		ArrayList<SimpleElement> elems = new ArrayList<SimpleElement>(elements.values());
		return elems.get(index);
	}

	public SimpleElement getElementByKey(String key) {
		return elements.get(key);
	}

	public ArrayList<String> getAllKeys() {
		return new ArrayList<String>(elements.keySet());
	}

	public int size() {
		return elements.size();
	}
	
	
}
