package com.rps.app.panels;

import com.rps.app.analysis.DialogConfiguration;
import com.rps.app.elements.simpleelements.SimpleElement;

public interface SimpleElementCreatePanel {
	boolean drawable = false;
	int PROJECTION_PANEL = 1;
	
	public boolean isDrawable();
	public boolean isValidInput();
	public DialogConfiguration getConfiguration();
	public void setName(String name);

}
