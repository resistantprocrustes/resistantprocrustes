package com.rps.app.export;

public class ExportConfiguration {
	
	public final static int TPS_TYPE = 0;
	public final static int CSV_TYPE = 1;
	public static final int NTS_DISTANCE_TYPE = 2;
	public static final int NTS_TYPE = 3;
	private int type;
	private String source;
	
	public ExportConfiguration(int type, String source){
		this.type = type;
		this.source = source;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
