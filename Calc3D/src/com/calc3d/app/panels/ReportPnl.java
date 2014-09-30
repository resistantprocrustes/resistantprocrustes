package com.calc3d.app.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.JToolBar;
import javax.swing.border.Border;

public class ReportPnl extends JPanel {

	private JScrollPane scroll;
	private JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	public ReportPnl() {
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		scroll = new JScrollPane(textArea);
		
		add(scroll, BorderLayout.CENTER);

	}
	
	public void appendReport(String report){
		textArea.append(report);
	}

}
