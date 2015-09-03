package com.rps.app.reports;

import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.panels.ReportPnl;

public class ReportGenerator {
	
	private ReportPnl pnl;

	public ReportGenerator(ReportPnl rportpnl){
		this.pnl = rportpnl;
	}
	
	public void writeReport(String report){
		pnl.appendReport(report);
	}
	

}
