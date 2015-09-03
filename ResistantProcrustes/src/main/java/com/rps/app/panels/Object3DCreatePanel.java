package com.rps.app.panels;

import javax.swing.JPanel;

import com.rps.app.elements.Element3D;

public abstract class Object3DCreatePanel extends JPanel implements InputPanel{

	public String errorMsg="";
public abstract Element3D getObject3D() ;

public abstract void setObject3D(Element3D object3D) ;

}
