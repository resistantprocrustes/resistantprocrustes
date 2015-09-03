package com.rps.app.panels;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Listmaker extends JPanel implements ActionListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1809315708538614775L;
	/** The PropertyChangeEvent fired when the OK button is clicked. */
	public static final String PROP_OK_BUTTON = "ok-button-clicked";
	/** The PropertyChangeEvent fired when the Cancel button is clicked. */
	public static final String PROP_CANCEL_BUTTON = "cancel-button-clicked";
	/** The PropertyChangeEvent fired when either the OK or the Cancel button is clicked. */
	public static final String PROP_OKCANCEL_BUTTON = "ok-or-cancel-button-clicked";
	/** Specifies that the GUI should add no sort button to lists. */
	public static final int SORT_NONE = 0; 
	/** Specifies that the GUI should add a sort button to list1 only. */
	public static final int SORT_LIST1 = 1; 
	/** Specifies that the GUI should add a sort button to list2 only. */
	public static final int SORT_LIST2 = 2; 
	/** Specifies that the GUI should add sort buttons to both list1 and list2. */
	public static final int SORT_BOTH = 3;
	
	private JList list1;
	private JList list2;
	private DefaultListModel model1;
	private DefaultListModel model2;		
	private JButton buttonTransfer;
	private JButton buttonTransferall;
	private JButton buttonDelete;
	private JButton buttonMoveup;
	private JButton buttonMovedown;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JButton buttonSort1;
	private JButton buttonSort2;
		
	private boolean duplicatesAllowed;
	private Dialog dialog;
	
	private Object[] userChoice = null;

	
	/**
	 * Private constructor. To build a Listmaker panel component,
	 * call {@link Listmaker#ListSelector(JLabel, Object[], Object[], boolean, int)} instead. 
	 * @param label  a label that is shown on the top of the dialog. May also be <code>null</code>, 
	 *               in which case an empty label is used    
	 * @param items1  the list of items to put on list1  
	 * @param items2  the list of items to put on list2. May also be <code>null</code>, 
	 *                which specifies an empty list 
	 * @param duplicatesAllowed  if <code>true</code>, list2 is allowed to contain more than one 
	 *                           instance of the same item (i.e. duplicates) from list1
	 * @param sortType  specifies whether the GUI should include a sort button on top of lists: 
	 *                  SORT_NONE, SORT_LIST1, SORT_LIST2, or SORT_BOTH
	 * @param dialog  if the Listmaker is used as a dialog, the modal dialog purporting to this
	 *                Listmaker. If the Listmaker is shown as a JPanel component of an 
	 *                user-created frame, this value is <code>null</code>   
	 * @wbp.parser.constructor
	 */
	private Listmaker(JLabel label, Object[] items1, Object[] items2,
					    boolean duplicatesAllowed, int sortType, Dialog dialog) {
		if (label == null) {
			label = new JLabel();
		}
		this.duplicatesAllowed = duplicatesAllowed;
		this.dialog = dialog;
		
		// List initialisation 
		model1 = new DefaultListModel();
		for (int i = 0; i < items1.length; i++) {
			model1.addElement(items1[i]);
		}
		list1 = new JList(model1);
		model2 = new DefaultListModel();
		if (items2 != null && items2.length > 0) {
			for (int i = 0; i < items2.length; i++) {
				model2.addElement(items2[i]);
			}
		}
		list2 = new JList(model2);
		// Sets the lists width and creates the lists GUI
		String width = getLongestString(concatenate(items1, items2)) + "0000";
		list1.setPrototypeCellValue(width);
		list2.setPrototypeCellValue(width);		
		list1.addListSelectionListener(this);
		list2.addListSelectionListener(this);
		JScrollPane scroll1 = new JScrollPane(list1);
		JScrollPane scroll2 = new JScrollPane(list2);
		scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		buttonSort1 = new JButton();
		buttonSort2 = new JButton();
		buttonSort1.addActionListener(this);
		buttonSort2.addActionListener(this);
		buttonSort1.setToolTipText("Sort this list");
		buttonSort2.setToolTipText("Sort this list");
		
		JPanel commandpanel = new JPanel();
		commandpanel.setLayout(new GridLayout(6, 1, 0, 7));
		buttonTransfer = new JButton(" > ");
		buttonTransfer.addActionListener(this);
		buttonTransfer.setToolTipText("Add the selected element(s)");
		buttonTransferall = new JButton(" >> "); 
		buttonTransferall.addActionListener(this);
		buttonTransferall.setToolTipText("Add the whole list");
		buttonDelete = new JButton(" X ");
		buttonDelete.addActionListener(this);
		buttonDelete.setToolTipText("Remove the selected element(s)");
		buttonMoveup = new JButton(" Move up ");
		buttonMoveup.addActionListener(this);
		buttonMoveup.setToolTipText("Move up the selected element(s)");
		buttonMovedown = new JButton(" Move down ");
		buttonMovedown.addActionListener(this);
		buttonMovedown.setToolTipText("Move down the selected element(s)");
		commandpanel.add(buttonTransfer);
		commandpanel.add(buttonTransferall);
		JLabel space = new JLabel();
		Dimension dimension = new Dimension(1, 10);
		space.setMinimumSize(dimension);
		space.setMaximumSize(dimension);
		commandpanel.add(space);
		commandpanel.add(buttonDelete);
		commandpanel.add(buttonMoveup);
		commandpanel.add(buttonMovedown);
		checkEnablingButtons();

		buttonOK = new JButton("                                   OK                                   ");
		buttonOK.addActionListener(this);
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);
		JPanel okpanel = new JPanel();
		okpanel.add(buttonOK);
		okpanel.add(buttonCancel);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; c.gridwidth = 3; c.ipadx = 0; c.ipady = 0;
		c.fill = GridBagConstraints.BOTH; c.weightx = 0.0; c.weighty = 0.0;
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new EmptyBorder(5, 15, 10, 15));
		add(label, c);
		if (sortType == SORT_BOTH || sortType == SORT_LIST1) {
			c.gridx = 0; c.gridy = 1; c.gridwidth = 1; c.ipadx = 0; c.ipady = 0;
			c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.0; c.weighty = 0.0;
			add(buttonSort1, c);
		}
		if (sortType == SORT_BOTH || sortType == SORT_LIST2) {		
			c.gridx = 2; c.gridy = 1; c.gridwidth = 1; c.ipadx = 0; c.ipady = 0;
			c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.0; c.weighty = 0.0; 
			add(buttonSort2, c);
		}
		c.gridx = 0; c.gridy = 2; c.gridwidth = 1; c.ipadx = 3; c.ipady = 0;
		c.fill = GridBagConstraints.VERTICAL; c.weightx = 0.0; c.weighty = 0.5; 
//		add(scroll1, c);
		c.gridx = 1; c.gridy = 2; c.gridwidth = 1; c.ipadx = 0; c.ipady = 0;
		c.fill = GridBagConstraints.NONE; c.weightx = 0.0; c.weighty = 0.0; 
		commandpanel.setBorder(new EmptyBorder(10, 15, 20, 15));
		add(commandpanel, c);
		c.gridx = 2; c.gridy = 2; c.gridwidth = 1; c.ipadx = 3; c.ipady = 0; 		
		c.fill = GridBagConstraints.VERTICAL; c.weightx = 0.0; c.weighty = 0.5; 
//		add(scroll2, c);
		c.gridx = 0; c.gridy = 3; c.gridwidth = 3; c.ipadx = 0; c.ipady = 0; 
		c.fill = GridBagConstraints.BOTH; c.weightx = 0.0; c.weighty = 0.0; 
		okpanel.setBorder(new EmptyBorder(10, 0, 0, 0));
//		add(okpanel, c);
		setBorder(new EmptyBorder(5, 20, 5, 20));
		
		setVisible(true);
	}

	
	/**
	 * Builds a Listmaker component panel. 
	 * @param label  a label that is shown on the top of the dialog. May also be <code>null</code>, 
	 *               in which case an empty label is used    
	 * @param items1  the list of items to put on list1  
	 * @param items2  the list of items to put on list2. May also be <code>null</code>, 
	 *                which specifies an empty list 
	 * @param duplicatesAllowed  if <code>true</code>, list2 is allowed to contain more than one 
	 *                           instance of the same item (i.e. duplicates) from list1
	 * @param sortType  specifies whether the GUI should include a sort button on top of lists: 
	 *                  SORT_NONE, SORT_LIST1, SORT_LIST2, or SORT_BOTH
	 */
	public Listmaker(JLabel label, Object[] items1, Object[] items2,
			   boolean duplicatesAllowed, int sortType) {
		this(label, items1, items2, duplicatesAllowed, sortType, null);
	}
		

	/**
	 * Brings up a Listmaker dialog. 
	 * @param frame  the parent JFrame of the dialog. If <code>null</code>, a new JFrame is created,
	 *               and disposed of after the user's choice
	 * @param label  a label that is shown on the top of the dialog. May also be <code>null</code>, 
	 *               in which case an empty label is used 
	 * @param title  the title of the dialog   
	 * @param items1  the list of items to put on list1  
	 * @param items2  the list of items to put on list2. May also be <code>null</code>, 
	 *                which specifies an empty list 
	 * @param duplicatesAllowed  if <code>true</code>, list2 is allowed to contain more than one 
	 *                           instance of the same item (i.e. duplicates) from list1
	 * @param sortType  specifies whether the GUI should include a sort button on top of lists: 
	 *                  SORT_NONE, SORT_LIST1, SORT_LIST2, or SORT_BOTH
	 * @return the array of items chosen by the user in list2 (may also be empty) if user clicked Ok, 
	 *         or <code>null</code> if user clicked Cancel 
	 */
	public static Object[] showListmakerDialog(JFrame frame, JLabel label, String title, 
												 Object[] items1, Object[] items2, 
												 boolean duplicatesAllowed, int sortType) {
		boolean nullframe = (frame == null);
		JFrame newframe = new JFrame();
		Dialog dialog = new Dialog((nullframe? newframe : frame), title, true);
		Listmaker listbuilder = new Listmaker(label, items1, items2, 
												  duplicatesAllowed, sortType, dialog);
		dialog.add(listbuilder);
		dialog.pack();
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension window = dialog.getSize();	 
	    dialog.setLocation((screen.width - window.width) / 2, (screen.height - window.height) / 2);

		dialog.setVisible(true);
		if (nullframe) {
			newframe.dispose();
		}
		return listbuilder.getUserChoice();
	}

	
	public final void actionPerformed(ActionEvent e) {
		
		if (buttonTransfer.equals(e.getSource())) {		
			Object[] selected = list1.getSelectedValues();
			add(list2, model2, selected);
		}
		
		else if (buttonTransferall.equals(e.getSource())) {		
			Object[] all = model1.toArray();
			model2.clear();
			add(list2, model2, all);
			list2.setSelectedIndex(0);
			list2.ensureIndexIsVisible(0);
		}
		
		else if (buttonDelete.equals(e.getSource())) {		
			Object nullObject = new Object();
			int[] selectedIndices = list2.getSelectedIndices();
			int finalIndex = list2.getMinSelectionIndex();
			// Deletion is done in two loops: first replace each element to delete 
			// with nullObject, then remove all nullObjects. This because, 
			// in case of multiple selection, object's indices would change after each removal. 
			for (int i = 0; i < selectedIndices.length; i++) {
				model2.set(selectedIndices[i], nullObject);
			}
			for (int j = 0; j < selectedIndices.length; j++) {
				model2.removeElement(nullObject);
			}
			list2.setSelectedIndex(finalIndex);
			list2.ensureIndexIsVisible(finalIndex);
		}
		
		else if (buttonMoveup.equals(e.getSource())) {		
			if (list2.getMinSelectionIndex() <= 0) {
				// Exits method if the first element (which cannot be moved up further) is selected, 
				// or if none is selected.
				return;
			}
			int[] selectedIndices = list2.getSelectedIndices();
			for (int i = 0; i < selectedIndices.length; i++) {
				swap(model2, selectedIndices[i], selectedIndices[i] - 1);
			}
			// Adjusts the selection 
			list2.clearSelection();
			for (int i = 0; i < selectedIndices.length; i++) {
				selectedIndices[i]--;
			}
			list2.setSelectedIndices(selectedIndices);
			list2.ensureIndexIsVisible(list2.getMinSelectionIndex());
		}
		
		else if (buttonMovedown.equals(e.getSource())) {		
			if ((list2.getMaxSelectionIndex() == model2.size() - 1) || 
				(list2.getMaxSelectionIndex() < 0)) {
				// Exits method if the last element (which cannot be moved down further) is selected, 
				// or if none is selected.
				return;
			}
			int[] selectedIndices = list2.getSelectedIndices();
			for (int i = selectedIndices.length - 1; i >= 0; i--) {
				swap(model2, selectedIndices[i], selectedIndices[i] + 1);
			}
			// Adjusts the selection 
			list2.clearSelection();
			for (int i = 0; i < selectedIndices.length; i++) {
				selectedIndices[i]++;
			}
			list2.setSelectedIndices(selectedIndices);
			list2.ensureIndexIsVisible(list2.getMaxSelectionIndex());
		}
		
		else if (buttonOK.equals(e.getSource())) {
			userChoice = model2.toArray();
			if (dialog != null) {
				dialog.dispose();
			}
			firePropertyChange(PROP_OK_BUTTON, null, null);
			firePropertyChange(PROP_OKCANCEL_BUTTON, null, null);
		}

		else if (buttonCancel.equals(e.getSource())) {
			userChoice = null;
			if (dialog != null) {
				dialog.dispose();
			}
			firePropertyChange(PROP_CANCEL_BUTTON, null, null);
			firePropertyChange(PROP_OKCANCEL_BUTTON, null, null);
		}

		else if (buttonSort1.equals(e.getSource())) {
			sort(list1, model1);
		}

		else if (buttonSort2.equals(e.getSource())) {
			sort(list2, model2);
		}
		
		checkEnablingButtons();		
	}

	
	public final void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			checkEnablingButtons();
		}
	}
	
	
	/**
	 * Gets the user's choice. This is equal to:
	 * <ul>
	 * <li>the content of list2 after the user has validated it by 
	 * pressing the <i>Ok</i> button</li>
	 * <li><code>null</code> if the user clicked on the <i>Cancel</i> button.</li>
	 * </ul>
	 * The user's choice value is updated after every click of the user on <i>Ok</i> or <i>Cancel</i>. 
	 * It is therefore recommended to call this method only after having listened to a 
	 * {@link PropertyChangeEvent} PROP_OK_BUTTON, PROP_CANCEL_BUTTON, or PROP_OKCANCEL_BUTTON.
	 * @return  the content of list2, or <code>null</code>
	 * @see #PROP_OK_BUTTON
	 * @see #PROP_CANCEL_BUTTON
	 * @see #PROP_OKCANCEL_BUTTON 
	 */
	public final Object[] getUserChoice() {
		return userChoice;
	}
	
	
	/**
	 * Swaps the place of two elements in the list that uses the specified <code>model</code>.
	 * @param model  the model used by the list whose elements are going to be swapped 
	 * @param indexA  the index of the element to put at <code>indexB</code>
	 * @param indexB  the index of the element to put at <code>indexA</code>
	 */
	private final void swap(DefaultListModel model, int indexA, int indexB) {
		Object element = model.get(indexA);
		model.set(indexA, model.get(indexB));
		model.set(indexB, element);
	}
	
	
	/**
	 * Sorts the content of a JList. 
	 * Note that, if <code>duplicatesAllowed</code> is <code>false</code>, calling this method
	 * will destroy all duplicates (if any) in the sorted list.
	 * @param list  the list to be sorted
	 * @param model  the model used by <code>list</code>
	 */
	private final void sort(JList list, DefaultListModel model) {
		Object selected = list.getSelectedValue();
		Object[] sorted = model.toArray();
		Arrays.sort(sorted);
		model.clear();
		add(list, model, sorted);
		list.setSelectedValue(selected, true);
	}
	
	
	/**
	 * Adds the specified array to <code>list</code>. 
	 * The items are added after the (last) selected element in <code>list</code>, or at the end 
	 * if nothing is selected; the latter includes the case in which <code>list</code> is empty.
	 * @param list  the list to which add <code>array</code>
	 * @param model  the model of <code>list</code>
	 * @param array  the array of items to add to <code>list</code>
	 */
	private final void add(JList list, DefaultListModel model, Object[] array) {
		int insertIndex = list.getMaxSelectionIndex();
		int finalIndex;
		int i;
		int addedElements = 0;

		for (i = 0; i < array.length; i++) {
			if (duplicatesAllowed || !model.contains(array[i])) {
				if (insertIndex == -1) {
					model.addElement(array[i]);	
				} else {
					model.add(insertIndex + 1 + addedElements, array[i]);	
				}
				addedElements++;			
			}
		}
		
		finalIndex = (insertIndex == -1)? model.size() - 1 : insertIndex + addedElements;				
		list.setSelectedIndex(finalIndex);
		list.ensureIndexIsVisible(finalIndex);
	}
	

	/**
	 * Checks which button must be enabled/disabled, depending on the internal Listmaker's state.
	 */
	private final void checkEnablingButtons() {
		buttonTransfer.setEnabled(list1.getSelectedIndex() != -1);
		if (!duplicatesAllowed && model2.contains(list1.getSelectedValue())) {
			buttonTransfer.setEnabled(false);
		}
		buttonDelete.setEnabled(list2.getSelectedIndex() != -1);
		buttonMoveup.setEnabled(list2.getMinSelectionIndex() > 0);
		buttonMovedown.setEnabled(list2.getSelectedIndex() != -1 && 
								  list2.getMaxSelectionIndex() != model2.size() - 1);
	}

	
	/**
	 * Given an array of Objects, finds the Object having the longest String representation. 
	 * @param array  the array to examine
	 * @return  the longest String representation of any Object contained in the <code>array</code>
	 */
	public final static String getLongestString(Object[] array) {
		String longestString = "";
		for (int i = 0; i < array.length; i++) {
			if ((array[i].toString()).length() > longestString.length()) {
				longestString = array[i].toString();
			}
		}
		return longestString;
	}
	
	
	/**
	 * Concatenates two arrays.
	 * @param array1  a non-<code>null</code> array
	 * @param array2  an array (may be <code>null</code>)
	 * @return the array obtained by concatenating <code>array1</code> with <code>array2</code>  
	 */
	public final static Object[] concatenate(Object[] array1, Object[] array2) {
		int length1 = array1.length;
		int length2 = (array2 == null? 0 : array2.length);
		Object[] array = new Object[length1 + length2];
		System.arraycopy(array1, 0, array, 0, length1);
		if (array2 != null) {
			System.arraycopy(array2, 0, array, length1, length2);
		}
		return array;		
	}
	
	
}