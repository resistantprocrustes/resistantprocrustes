package com.rps.app.export;

import com.rps.app.elements.simpleelements.SimpleElement;

public interface IExporter {
	public void export(SimpleElement element, String source);
}
