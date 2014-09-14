package com.calc3d.app.elements.simpleelements;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import com.calc3d.app.elements.Element3D;

public abstract class SimpleElement {

	String name;
	ImageIcon icon;

	public SimpleElement(String name2) {
		this.name = name2;
	}

	public SimpleElement() {}

	public String getName() {
		return name;
	}
	
	public void setName(String nName){
		name = nName;
	}
	
	public void setIcon(ImageIcon icon){
		this.icon = icon;
	}
	
	public ImageIcon getIcon(){
		return icon;
	}

}
