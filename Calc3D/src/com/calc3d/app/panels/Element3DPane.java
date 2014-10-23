package com.calc3d.app.panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.calc3d.app.dialogs.ColorDialog;
import com.calc3d.app.elements.Element3D;
import com.calc3d.app.elements.Element3DPoint;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

import java.awt.Label;
import java.awt.List;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.JComboBox;
/**
 * Landmark graphic panel editor
 * @author Lucas Marquez (lucasasecas@gmail.com)
 * @version 1.0.0
 * @since 	1.0.0
 */
public class Element3DPane extends JPanel implements ActionListener{
	private JTextField textFieldLabel;
	private JTextField textFieldTabTitle;
	private JLabel lblTabTitle;
	private JLabel lblLabel;
	private JLabel lblRadious;
	private JLabel lblBorderColor;
	private JLabel lblBorderWidth;
	private JLabel lblBackColor;
	private JSpinner spinnerBorderWidth;
	private JSpinner spinnerRadious;
	private JButton btnSelectBorderColor;
	private JButton btnSelectBackColor;
	private JSeparator separator;
	private ArrayList<Element3D> elements;
	private JComboBox comboBox;
	private ArrayList<Element3DPointModel> pointModels;
	
	
	
	public Element3DPane() {
		
		lblTabTitle = new JLabel("Tab title");
		
		lblLabel = new JLabel("Label");
		
		lblRadious = new JLabel("Radious");
		
		lblBorderWidth = new JLabel("Border width");
		
		lblBorderColor = new JLabel("Border Color");
		
		lblBackColor = new JLabel("Back Color");
		
		spinnerBorderWidth = new JSpinner();
		
		spinnerRadious = new JSpinner();
		
		textFieldLabel = new JTextField();
		textFieldLabel.setColumns(10);
		
		textFieldTabTitle = new JTextField();
		textFieldTabTitle.setColumns(10);
		
		btnSelectBorderColor = new JButton("Select");
		btnSelectBorderColor.addActionListener(this);
		btnSelectBorderColor.setActionCommand("bodercolor");
		btnSelectBackColor = new JButton("Select");
		btnSelectBackColor.addActionListener(this);
		btnSelectBackColor.setActionCommand("backcolor");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		
		separator = new JSeparator();
		
		JLabel lblConfiguration = new JLabel("Configuration");
		
		comboBox = new JComboBox();
		comboBox.addActionListener(this);
		comboBox.setActionCommand("changedelement");
		
		this.elements = new ArrayList<Element3D>();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 259, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTabTitle)
							.addGap(44)
							.addComponent(textFieldTabTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblConfiguration)
							.addGap(18)
							.addComponent(comboBox, 0, 113, Short.MAX_VALUE)
							.addGap(64))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBorderWidth)
										.addComponent(lblLabel)
										.addComponent(lblRadious))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(spinnerRadious, Alignment.LEADING)
											.addComponent(spinnerBorderWidth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBorderColor)
										.addComponent(lblBackColor))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSelectBackColor)
										.addComponent(btnSelectBorderColor))
									.addGap(18)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(25, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTabTitle)
						.addComponent(textFieldTabTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfiguration)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLabel)
						.addComponent(textFieldLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerRadious, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadious))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBorderWidth)
						.addComponent(spinnerBorderWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBorderColor)
								.addComponent(btnSelectBorderColor))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBackColor)
								.addComponent(btnSelectBackColor)))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		//ColorDialog colorDialog = new ColorDialog(colorDialog, preserveBackgroundColor, autoscrolls);
		
	}
	
	
	public Element3DPane(Element3D element){
		this();
		this.addElement(element);
	}
	
	public Element3DPane(ArrayList<Element3D> elements){
		super();
		for(Element3D elem : elements){
			this.addElement(elem);
		}
	}
	
	public void addElement(Element3D element){
		this.elements.add(element);
		this.comboBox.addItem(element.getName());
		this.pointModels.add(new Element3DPointModel((Element3DPoint)element));
	}
		
		
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command == "changedelement"){
			this.changeElement(this.comboBox.getSelectedIndex());
		}
	}
	
	private void changeElement(int index){
		if(index<2) return;
		index-=2;
		Element3DPointModel model = this.pointModels.get(index);
		
		this.spinnerRadious.setValue(model.getRadious());
		//this.textFieldLabel.setText(arg0);
	}
	
	public class Element3DPointModel{
		String id;
		String label;
		int radious;
		Color backColor;
		Color borderColor;
		boolean dirty;
		public Element3DPointModel(Element3DPoint point){
			id = point.getName();
			this.label = point.getText();
			this.radious = point.getRadius();
			this.backColor = point.getBackColor();
			this.borderColor = point.getLineColor();
			this.dirty=false;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public int getRadious() {
			return radious;
		}
		public void setRadious(int radious) {
			this.radious = radious;
		}
		public Color getBackColor() {
			return backColor;
		}
		public void setBackColor(Color backColor) {
			this.backColor = backColor;
		}
		public Color getBorderColor() {
			return borderColor;
		}
		public void setBorderColor(Color borderColor) {
			this.borderColor = borderColor;
		}
		public boolean isDirty() {
			return dirty;
		}
		public void setDirty(boolean dirty) {
			this.dirty = dirty;
		}
	}
}
