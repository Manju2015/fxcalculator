package com.anz.fxcalculator.service;

/**
 * Executor to convert the currency
 * @author Manjunath
 *
 */
public interface FxService {
	
	/**
	 * This method looks up the currencyMapper and returns appropriate value.
	 * @param input
	 * @return
	 */
	public String execute(String input);

}
