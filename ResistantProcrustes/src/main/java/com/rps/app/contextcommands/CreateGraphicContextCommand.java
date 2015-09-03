package com.rps.app.contextcommands;

import javax.swing.JFrame;

import com.rps.app.CopyOfGui;
import com.rps.app.Globalsettings;
import com.rps.app.analysis.DialogConfiguration;
import com.rps.app.elements.Element3D;
import com.rps.app.elements.Element3DFactory;
import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.resources.Messages;



public class CreateGraphicContextCommand extends ContextMenuCommand {

	private JFrame window; 
	private int element3DType;
	
	public CreateGraphicContextCommand(JFrame window, SimpleElement element, int type) {
		super(element);
		this.window = window;
		super.setLabel(Messages.getString("contextcommand.creatgraphic.label"));
	}
	
	
	public void execute() {
			Element3D element3D = Element3DFactory.generate(this.element, this.element3DType);
			CopyOfGui gui = ((CopyOfGui)window);
			DialogConfiguration conf = new DialogConfiguration();
			conf.setGraphPreferences(Globalsettings.getSettings());
			conf.setTabTitle(this.element.getName());
			gui.addElement3D(element3D, conf);
	}

	
}
