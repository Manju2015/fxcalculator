package com.anz.fxcalculator.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Currency {
	
	/**
	 * Holds the currency mapping 
	 */
	public static Map<String, Map<String, Double>> currencyMapper = new ConcurrentHashMap<String, Map<String,Double>>();
	/**
	 * Holds the decimal value for currency
	 */
	public static Map<String, Integer> currencyDecimalMap = new ConcurrentHashMap<String, Integer>();

}
