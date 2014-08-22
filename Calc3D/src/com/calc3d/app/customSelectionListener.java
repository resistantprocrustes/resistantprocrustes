package com.calc3d.app;

import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.calc3d.app.elements.Element3D;
import com.calc3d.app.elements.Element3DEntity;
import com.calc3d.app.elements.Element3DProcrustesResult;

public class customSelectionListener implements TreeSelectionListener {

	private JButton _button;
	
	public customSelectionListener(JButton button){
		_button = button;
	}
	
	
	@Override
	public void valueChanged(TreeSelectionEvent event) {
		Object obj = event.getPath().getLastPathComponent();
		
		if(obj instanceof Element3DProcrustesResult){
			_button.setEnabled(true);
		}
		else
			_button.setEnabled(false);
		
	}

}
