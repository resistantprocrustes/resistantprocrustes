package com.rps.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;

import com.rps.app.CopyOfGui.TreeTableModel;
import com.rps.app.elements.Element3D;
import com.rps.app.elements.actions.SimpleElementAction;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.resources.Messages;

public class LeftTableSelectionListener implements TreeSelectionListener, MouseListener {

	JLabel editorPane;
	JXTreeTable table;
	ActionListener actionListener;
	
	
	
	public LeftTableSelectionListener(JLabel label, JXTreeTable treeTable, ActionListener window){
		editorPane = label;
		this.table = treeTable;
		actionListener = window;
		
	}
	

	public void valueChanged(TreeSelectionEvent event) {
		Object obj = event.getNewLeadSelectionPath().getLastPathComponent();
		if(obj instanceof SimpleElement){
			  editorPane.setText(commonUtils.getobject3DInfoHTML(((SimpleElement) obj)));
			  editorPane.updateUI();

		}

	}

	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount()==2){
			TreeTableModel model = (TreeTableModel) table.getTreeTableModel();
			int i=table.getSelectedRow();
			if (i<0)return;
			 
			TreePath path = table.getPathForRow(i);
			SimpleElement node = (SimpleElement) path.getLastPathComponent();
			
			for(SimpleElementAction ac : node.getAllActions()){
				if(ac.getActionCommand() == "showpanel"){
					actionListener.actionPerformed(new ActionEvent(this, 1, "showpanel"));
				}
			}
		}else{
		
			TreeTableModel model = (TreeTableModel) table.getTreeTableModel();
			 int i=table.getSelectedRow();
			 if (i<0)return;
			 
			TreePath path = table.getPathForRow(i);
			SimpleElement node = (SimpleElement) path.getLastPathComponent();
	
			if(e.isMetaDown()){
				SimpleElementAction[] actions = node.getAllActions();
				JPopupMenu menu = new JPopupMenu();
				for(SimpleElementAction action : actions){
					JMenuItem item = new JMenuItem(action.getName());
					item.addActionListener(actionListener);
					item.setActionCommand(action.getActionCommand());
					item.setEnabled(action.isEnabled());
					menu.add(item);
				}
				
				
				menu.show(e.getComponent() ,e.getY(), e.getY() );
				
			}
		}
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
