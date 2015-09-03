package com.rps.app.dialogs;

import javax.swing.JDialog;

import com.rps.app.CopyOfGui;
import com.rps.app.elements.Element3D;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;

public class AddProjectionDialog extends JDialog {
	public AddProjectionDialog() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Settings = new JPanel();
		Settings.setToolTipText("Settings");
		tabbedPane.addTab("edicion", null, Settings, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("proyecicon", null, panel_1, null);
	}

	public static Element3D show(CopyOfGui gui, Element3D element) {
		
		return null;
	}

}
