package com.calc3d.app;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.calc3d.renderer.Canvas3D;

public class TabsManager {

	JTabbedPane tabs;
	ArrayList<JPanel> canvasList;
	
	public TabsManager(JTabbedPane tabs){
		this.tabs = tabs;
		this.canvasList = new ArrayList<JPanel>();
	}
	
	public void newTab(JSplitPane canvas, String title){
		tabs.add(title, canvas);
	}

}
