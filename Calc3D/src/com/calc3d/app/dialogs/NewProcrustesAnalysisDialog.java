package com.calc3d.app.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.calc3d.app.CopyOfGui;
import com.calc3d.app.analysis.AnalysisConfiguration;
import com.calc3d.app.elements.dataset.DataSet;
import com.calc3d.app.elements.simpleelements.ComposeSimpleElement;
import com.calc3d.app.elements.simpleelements.SimpleElement;
import com.example.loaders.PCEntity;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
/**
 * 
 * @author Lucas Marquez
 * 
 *
 */
	
public class NewProcrustesAnalysisDialog extends JDialog implements ActionListener {
	
	JPanel configurationPnl;
	JLabel lblName;
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public JRadioButton rdbtnMinimusSquareFit;
	public JRadioButton rdbtnRobustFit;
	public boolean canceled = false;
	private JButton btnOk;
	private JButton btnCancel;
	
	public NewProcrustesAnalysisDialog(Window owner) {
		super(owner, "Add new ", ModalityType.APPLICATION_MODAL);
		this.setSize(300, 300);
		configurationPnl = new JPanel();
		lblName = new JLabel("Name");
		
		getContentPane().add(configurationPnl, BorderLayout.CENTER);
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		rdbtnMinimusSquareFit = new JRadioButton("Minimus Square Fit");
		rdbtnMinimusSquareFit.setSelected(true);
		rdbtnMinimusSquareFit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnRobustFit = new JRadioButton("Robust Fit");
		GroupLayout gl_configurationPnl = new GroupLayout(configurationPnl);
		
		gl_configurationPnl.setHorizontalGroup(
			gl_configurationPnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_configurationPnl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_configurationPnl.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnRobustFit)
						.addComponent(rdbtnMinimusSquareFit)
						.addGroup(gl_configurationPnl.createSequentialGroup()
							.addComponent(lblName)
							.addGap(46)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(220, Short.MAX_VALUE))
		);
		gl_configurationPnl.setVerticalGroup(
			gl_configurationPnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_configurationPnl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_configurationPnl.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(rdbtnMinimusSquareFit)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnRobustFit)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnMinimusSquareFit);
		btnGroup.add(rdbtnRobustFit);
		
		configurationPnl.setLayout(gl_configurationPnl);
		
		JPanel buttonsPnl = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonsPnl.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonsPnl, BorderLayout.SOUTH);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		btnOk.setActionCommand("OK");
		buttonsPnl.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("CANCEL");
		buttonsPnl.add(btnCancel);

		
	}

	private AnalysisConfiguration generateConfiguration(ComposeSimpleElement dataset) {
		AnalysisConfiguration configuration = new AnalysisConfiguration();
		configuration.setElements((ArrayList<SimpleElement>) ((ComposeSimpleElement)dataset.getElementByKey("specimens")).getAllElements());
		configuration.setName(textField_2.getText());
		int type = this.rdbtnMinimusSquareFit.isSelected() ? AnalysisConfiguration.MIN_SQUARES_FIT : AnalysisConfiguration.ROBUST_FIT;
		configuration.setType(type);
		return configuration;
		
	}

	
	@Override
	public void actionPerformed(ActionEvent command) {
		if(command.getActionCommand()=="OK"){
			this.canceled=false;
			this.setVisible(false);
		}else if(command.getActionCommand()=="CANCEL"){
			this.canceled=false;
			this.setVisible(false);
		}
		
	}

	public static AnalysisConfiguration show(CopyOfGui owner, ComposeSimpleElement dataset) {
		NewProcrustesAnalysisDialog dialog = new NewProcrustesAnalysisDialog(owner);
		dialog.setLocationRelativeTo(owner);
		dialog.setVisible(true);
		if(! dialog.canceled){
			return dialog.generateConfiguration(dataset);
		}
		return null;
					
	}


}
