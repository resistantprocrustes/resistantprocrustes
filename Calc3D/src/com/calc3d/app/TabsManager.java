package com.calc3d.app;

import java.awt.Canvas;
import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXTreeTable;

import com.calc3d.renderer.Canvas3D;

public class TabsManager implements Serializable {

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
	
	public Canvas3D getCanvasAt(int index){
		try{
			JSplitPane split = ((JSplitPane)tabs.getComponent(index));
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
		int selected = tabs.getSelectedIndex();
		this.tabs.setTitleAt(tabs.getSelectedIndex(), title);
		tabs.updateUI();
	}

	public JTabbedPane getTabs() {
		return this.tabs;
	}

	public void setTabs(JTabbedPane tabs) {
		this.tabs = tabs;
		
	}

	public int getCountOfTabs() {
		return tabs.getComponentCount();
	}

	public String getTitleAt(int i) {
		return this.tabs.getTitleAt(i);
	}
	


}
