package com.calc3d.app.panels;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.calc3d.app.analysis.DialogConfiguration;
import com.calc3d.app.analysis.DistanceConfiguration;
import com.calc3d.app.elements.simpleelements.ComposeSimpleElement;
import com.calc3d.app.elements.simpleelements.SimpleElement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DistancePanel extends JPanel implements SimpleElementCreatePanel{
	
	public JRadioButton robRdBtn;
	public JRadioButton minSqeRdBtn;
	
	public DistancePanel() {
		this.robRdBtn = new JRadioButton("Robust Distance");
		this.robRdBtn.setSelected(true);
		this.minSqeRdBtn = new JRadioButton("Minimus Square Distance");
		this.minSqeRdBtn.setSelected(false);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(robRdBtn);
		btnGroup.add(minSqeRdBtn);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(minSqeRdBtn)
						.addComponent(robRdBtn))
					.addContainerGap(289, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(robRdBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(minSqeRdBtn)
					.addContainerGap(236, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

	@Override
	public boolean isDrawable() {
		
		return true;
	}

	@Override
	public boolean isValidInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public DialogConfiguration getConfiguration() {
		DistanceConfiguration configuration = new DistanceConfiguration(robRdBtn.isSelected() ? 0 : 1);
		return configuration;
		
	}
	

}
