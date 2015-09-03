package com.rps.app.contextcommands;

import com.rps.app.elements.simpleelements.SimpleElement;

public abstract class ContextMenuCommand {
	
	SimpleElement element;
	private String label;
	
	public ContextMenuCommand(SimpleElement element){
		this.element = element;
	}
	
	public abstract void execute();

	public void setLabel(String string) {
		this.label = string;
		
	}
}
