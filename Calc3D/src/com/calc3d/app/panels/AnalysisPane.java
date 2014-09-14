package com.calc3d.app.panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;

public class AnalysisPane extends JPanel{
	private JTextField textField;
	public AnalysisPane() {
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		JLabel lblTitulo = new JLabel("Nombre");
		
		JLabel lblTipoDeAnalisis = new JLabel("Tipo de analisis");
		
		JLabel lblDataset = new JLabel("Dataset");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitulo)
								.addComponent(lblDescripcion)
								.addComponent(lblTipoDeAnalisis))
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(textArea)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 139, Short.MAX_VALUE)))
						.addComponent(lblDataset))
					.addContainerGap(182, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitulo)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTipoDeAnalisis)
							.addGap(48)
							.addComponent(lblDataset))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		
		JRadioButton rdbtnRobusto = new JRadioButton("Robusto");
		panel.add(rdbtnRobusto);
		
		JRadioButton rdbtnProcrustes = new JRadioButton("Cuadrados Minimos");
		panel.add(rdbtnProcrustes);
		setLayout(groupLayout);
	}
}
