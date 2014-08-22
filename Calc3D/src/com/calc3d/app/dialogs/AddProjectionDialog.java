package com.calc3d.app.dialogs;

import javax.swing.JDialog;

import com.calc3d.app.Gui;
import com.calc3d.app.elements.Element3D;
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

	public static Element3D show(Gui gui, Element3D element) {
		
		return null;
	}

}
