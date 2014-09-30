package com.calc3d.app.reports;

import com.calc3d.app.elements.simpleelements.SimpleElement;
import com.calc3d.app.panels.ReportPnl;

public class ReportGenerator {
	
	private ReportPnl pnl;

	public ReportGenerator(ReportPnl rportpnl){
		this.pnl = rportpnl;
	}
	
	public void writeReport(String report){
		pnl.appendReport(report);
	}
	

}
