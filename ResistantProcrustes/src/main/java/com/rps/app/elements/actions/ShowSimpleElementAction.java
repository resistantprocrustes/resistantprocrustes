package com.rps.app.elements.actions;

import com.rps.app.CopyOfGui;
import com.rps.app.TabsManager;
import com.rps.app.elements.simpleelements.SimpleElement;

public class ShowSimpleElementAction extends SimpleElementAction{

	public ShowSimpleElementAction(String name, String actionCommand,
			CopyOfGui gui, SimpleElement element) {
		super(name, actionCommand, gui, element);
	}

	
	public boolean isEnabled() {
		TabsManager tabsManager = model.getTabsManager();
		return tabsManager.getTabIndex(element.getName()) == -1;
	}

}
