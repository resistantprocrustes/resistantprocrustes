package com.calc3d.app;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXTreeTable;

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
	
	public JXTreeTable getCurrentTreeTable(){
		try{
			JSplitPane split = ((JSplitPane)tabs.getSelectedComponent());
			JScrollPane c = (JScrollPane)split.getComponent(1);
			JXTreeTable result = (JXTreeTable) c.getViewport().getView();
			return result;
		}catch(Exception e){
			
		}
		return null;
	}
	
	public Canvas3D getCurrentCanvas(){
		try{
			JSplitPane split = ((JSplitPane)tabs.getSelectedComponent());
			Canvas3D c = (Canvas3D) split.getComponent(0);

			return c;
		}catch(Exception e){
			
		}
		return null;
	}

	public String getCurrentTitle() {
		return this.tabs.getTitleAt(tabs.getSelectedIndex());
	}
	
	public void setCurrentTitle(String title){
		this.tabs.setTitleAt(tabs.getSelectedIndex(), title);
		tabs.updateUI();
	}
	


}
