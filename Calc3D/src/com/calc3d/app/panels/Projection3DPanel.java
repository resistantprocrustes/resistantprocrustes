package com.calc3d.app.panels;

import java.awt.Window;

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

public class Projection3DPanel extends Object3DCreatePanel implements ActionListener {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Element3DProjection p;
	private JTextField textField;
	private JTextField textField_1;
	
	public Projection3DPanel(Element3DProjection element) {
		p = element;
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTipoDeProyeccion = new JLabel("Tipo de proyeccion");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton button_1 = new JButton(">>");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTipoDeProyeccion)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(button_1, 0, 50, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(41))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTipoDeProyeccion))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(button)
								.addGap(13)
								.addComponent(button_1))
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		
		JRadioButton rdbtnRobusto = new JRadioButton("Robusto");
		rdbtnRobusto.addActionListener(this);
		buttonGroup.add(rdbtnRobusto);
		rdbtnRobusto.setActionCommand("rdButtonRob");
		panel.add(rdbtnRobusto);
		
		JRadioButton rdbtnCuadradosMinimos = new JRadioButton("Cuadrados minimos");
		buttonGroup.add(rdbtnCuadradosMinimos);
		rdbtnCuadradosMinimos.addActionListener(this);
		rdbtnCuadradosMinimos.setActionCommand("rdButtonCM");
		rdbtnCuadradosMinimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(rdbtnCuadradosMinimos);
		setLayout(groupLayout);
		
		
		
	}

	@Override
	public boolean isValidInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void showInvalidInputMessage(Window owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Element3D getObject3D() {
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	public void setObject3D(Element3D object3d) {
		p = (Element3DProjection)object3d;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "rdButtonRob" ){
			p.typeOp = p.ROBUSTO;
		}else if(e.getActionCommand() == "rdButtonCM" ){
			p.typeOp = p.CM;
		}
		
	}
}
