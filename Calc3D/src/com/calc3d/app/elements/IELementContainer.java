package com.calc3d.app.elements;

public interface IELement3DCollectible{
	Element3DCollection collection = new Element3DCollection();
	
	public void add(Element3D elem);
	public void delete(int index);
	public Element3D get(int index);
	public void set(int index, Element3D elem);

}
