package com.rps.app.panels;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.rps.app.analysis.DialogConfiguration;
import com.rps.app.analysis.DistanceConfiguration;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.resources.Messages;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DistancePanel extends JPanel implements SimpleElementCreatePanel{
	
	public JRadioButton robRdBtn;
	public JRadioButton minSqeRdBtn;
	
	public DistancePanel() {
		this.robRdBtn = new JRadioButton(Messages.getString("dialog.distance.rd"));
		this.robRdBtn.setSelected(false);
		this.minSqeRdBtn = new JRadioButton(Messages.getString("dialog.distance.pd"));
		this.minSqeRdBtn.setSelected(true);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(minSqeRdBtn);
		btnGroup.add(robRdBtn);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(minSqeRdBtn)
						.addComponent(robRdBtn))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(minSqeRdBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(robRdBtn)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

	
	public boolean isDrawable() {
		
		return true;
	}

	
	public boolean isValidInput() {
		// TODO Auto-generated method stub
		return true;
	}

	
	public DialogConfiguration getConfiguration() {
		DistanceConfiguration configuration = new DistanceConfiguration(robRdBtn.isSelected() ? 0 : 1);
		return configuration;
		
	}
	

}
