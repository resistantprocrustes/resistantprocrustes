package com.rps.app;

import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.rps.app.elements.Element3D;
import com.rps.app.elements.Element3DEntity;
import com.rps.app.elements.Element3DProcrustesResult;
import com.rps.engine3d.Scene3D;
import com.rps.renderer.Canvas3D;

public class customSelectionListener implements TreeSelectionListener {

	Canvas3D canvas;
	public customSelectionListener() {
		// TODO Auto-generated constructor stub
	}
	
	public customSelectionListener(Canvas3D nCanvas){
		this.canvas = nCanvas;
	}



	public void valueChanged(TreeSelectionEvent event) {
		Object obj = event.getNewLeadSelectionPath().getLastPathComponent();
		Object obj2= event.getOldLeadSelectionPath();
		if(obj instanceof Element3D){
			if(obj2 != null){
				((Element3D) ((TreePath)obj2).getLastPathComponent()).select(false);
			}
			Element3D el = (Element3D) obj;
			el.select(true);
			Scene3D scene = canvas.getSceneManager().createScene(true);
			canvas.setScene(scene);
			this.canvas.refresh();
		}
		
		
	}

}
