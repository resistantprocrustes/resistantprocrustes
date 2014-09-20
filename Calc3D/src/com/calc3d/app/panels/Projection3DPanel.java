package com.calc3d.app.panels;

import java.awt.Window;

import com.calc3d.app.analysis.DialogConfiguration;
import com.calc3d.app.analysis.ProjectionConfiguration;
import com.calc3d.app.elements.Element3D;
import com.calc3d.app.elements.Element3DProjection;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class Projection3DPanel extends JPanel implements SimpleElementCreatePanel {


	private JRadioButton rdbtnRobust;
	private JRadioButton rdbtnLeastSqe;
	private JRadioButton rdbtn2D;
	private JRadioButton rdbtn3D;


	public Projection3DPanel() {
		
		rdbtnRobust = new JRadioButton("Robust");
		
		rdbtnLeastSqe = new JRadioButton("Least Square");
		
		JLabel lblProjectionType = new JLabel("Projection type:");
		
		JLabel lblDimensions = new JLabel("Dimensions:");
		
		rdbtn2D = new JRadioButton("2D");
		rdbtn3D = new JRadioButton("3D");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProjectionType)
						.addComponent(lblDimensions))
					.addGap(91)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtn3D)
						.addComponent(rdbtn2D)
						.addComponent(rdbtnRobust)
						.addComponent(rdbtnLeastSqe))
					.addContainerGap(163, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnRobust)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnLeastSqe))
						.addComponent(lblProjectionType))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDimensions)
						.addComponent(rdbtn2D))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtn3D)
					.addContainerGap(174, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		ButtonGroup bGroup = new ButtonGroup();
		rdbtnLeastSqe.setSelected(true);
		rdbtnRobust.setSelected(false);
		bGroup.add(rdbtnRobust);
		bGroup.add(rdbtnLeastSqe);
		
		ButtonGroup b2 = new ButtonGroup();
		b2.add(rdbtn2D);
		b2.add(rdbtn3D);
	}



	@Override
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public DialogConfiguration getConfiguration() {
		ProjectionConfiguration configuration = new ProjectionConfiguration(rdbtnLeastSqe.isSelected()?ProjectionConfiguration.LEAST_SQR_PROJETION : ProjectionConfiguration.ROBUST_PROJECTION);
		configuration.setDimensions(rdbtn2D.isSelected() ? 2 : 3);
		return configuration;
	}
}
