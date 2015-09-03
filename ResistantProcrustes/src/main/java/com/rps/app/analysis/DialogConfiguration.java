package com.rps.app.analysis;

import java.io.Serializable;

import com.rps.app.Globalsettings;
import com.rps.app.Preferences;
import com.rps.app.elements.Element3D;

public class DialogConfiguration implements Serializable{

	String tabTitle;
	Preferences graphPreferences = Globalsettings.getSettings(); 
	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}
	
	public String getTabTitle(){
		return this.tabTitle;
	}
	
	public Preferences getGraphPreferences(){
		return graphPreferences;
	}
	
	public void setGraphPreferences(Preferences preferences){
		this.graphPreferences = preferences;
	}

}
