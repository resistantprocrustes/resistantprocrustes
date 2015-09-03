package com.rps.app.panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import com.rps.app.analysis.AnalysisConfiguration;
import com.rps.app.analysis.DialogConfiguration;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.resources.Messages;
import com.rps.utils.PrefixTextField;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;

public class AnalysisPane extends JPanel implements SimpleElementCreatePanel, ActionListener{
	private JLabel lblName;
	private PrefixTextField textField;
	private PrefixTextField textField_2;
	private JRadioButton rdbtnMinimusSquareFit;
	private JRadioButton rdbtnRobustFit;
	private JButton btnOk;
	private JButton btnCancel;
	private JCheckBox chckbxShowConsensus;
	
	public AnalysisPane() {
		
		lblName = new JLabel(Messages.getString("dialog.addpcanalysis.tabname"));
		
		textField_2 = new PrefixTextField("");
		textField_2.setEnabled(false);
		textField_2.setColumns(12);
		rdbtnMinimusSquareFit = new JRadioButton(Messages.getString("dialog.addpcanalysis.glsp"));
		rdbtnMinimusSquareFit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnRobustFit = new JRadioButton(Messages.getString("dialog.addpcanalysis.grp"));
		
		chckbxShowConsensus = new JCheckBox("Show consensus");
		GroupLayout gl_configurationPnl = new GroupLayout(this);
		gl_configurationPnl.setHorizontalGroup(
			gl_configurationPnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_configurationPnl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_configurationPnl.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rdbtnRobustFit)
						.addComponent(chckbxShowConsensus)
						.addGroup(gl_configurationPnl.createSequentialGroup()
							.addComponent(lblName)
							.addGap(57)
							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(rdbtnMinimusSquareFit))
					.addContainerGap(119, Short.MAX_VALUE))
		);
		gl_configurationPnl.setVerticalGroup(
			gl_configurationPnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_configurationPnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnMinimusSquareFit)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnRobustFit)
					.addGap(18)
					.addGroup(gl_configurationPnl.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(chckbxShowConsensus)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnMinimusSquareFit);
		btnGroup.add(rdbtnRobustFit);
		rdbtnMinimusSquareFit.addActionListener(this);
		rdbtnMinimusSquareFit.setActionCommand("rdb1");
		rdbtnRobustFit.addActionListener(this);
		rdbtnRobustFit.setActionCommand("rdb2");
		this.setLayout(gl_configurationPnl);
		
		JPanel buttonsPnl = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonsPnl.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.add(buttonsPnl, BorderLayout.SOUTH);
		
	}
	
	public void setName(String name){
		super.setName(name);
		textField_2.setText(name);
	}
	
	
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean isValidInput() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public DialogConfiguration getConfiguration() {
		AnalysisConfiguration configuration = new AnalysisConfiguration();
		//configuration.setElements((ArrayList<SimpleElement>) ((ComposeSimpleElement)dataset.getElementByKey("specimens")).getAllElements());
		
		int type = this.rdbtnMinimusSquareFit.isSelected() ? AnalysisConfiguration.MIN_SQUARES_FIT : AnalysisConfiguration.ROBUST_FIT;
		String nName = type==AnalysisConfiguration.MIN_SQUARES_FIT?"GLSP":"GRP";
		System.out.println(textField_2.getFullText());
		configuration.setTabTitle(textField_2.getFullText());
		configuration.setName(textField_2.getFullText());
		configuration.setType(type);
		configuration.setShowConsensus(this.chckbxShowConsensus.isSelected());
		return configuration;
	}
	
	

	
	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();
		switch(command){
		case "rdb1":
			textField_2.setEnabled(true);
			textField_2.setPrefix("GLSP");
			break;
		
		case "rdb2":
			textField_2.setEnabled(true);
			textField_2.setPrefix("GRP");
			break;
		
		}
		textField_2.updateUI();
	}
}
