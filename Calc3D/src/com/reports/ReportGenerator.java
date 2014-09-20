package com.reports;


/**
 * Abstract class used to generate reports of differents objects
 * 
 * @author Lucas
 *
 */
public abstract class ReportGenerator {

	/**
	 * Returns a string that representa a report for the target element
	 * @param target element that want to generate report
	 * @return a new report with String format
	 */
	public abstract String generateReport(Object target);
}
