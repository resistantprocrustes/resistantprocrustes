package com.calc3d.app;

import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.calc3d.app.elements.Element3D;
import com.calc3d.app.elements.Element3DEntity;
import com.calc3d.app.elements.Element3DProcrustesResult;
import com.calc3d.engine3d.Scene3D;
import com.calc3d.renderer.Canvas3D;

public class customSelectionListener implements TreeSelectionListener {

	Canvas3D canvas;
	public customSelectionListener() {
		// TODO Auto-generated constructor stub
	}
	
	public customSelectionListener(Canvas3D nCanvas){
		this.canvas = nCanvas;
	}


	@Override
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
