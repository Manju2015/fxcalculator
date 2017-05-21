package com.anz.fxcalculator.service;

import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.anz.fxcalculator.bean.Currency;
import com.anz.fxcalculator.exception.FxCaluclatorException;


/**
 * This class loads the currencymap.properties and currencydecimal.properties
 * @author ramalinm
 *
 */
public class Test1InitializerServiceImpl {
	
	InitializerService initializerService = null;
	
	@Before
    public void setUp() throws FxCaluclatorException {
		initializerService = new InitializerServiceImpl();
    }

	@Test
	public void testLoadSize() {
		try {
			initializerService.load(ClassLoader.getSystemResourceAsStream("currencymapTest.properties"),ClassLoader.getSystemResourceAsStream("currencydecimalTest.properties"));
			Assert.assertEquals(1, Currency.currencyDecimalMap.size());
			Assert.assertEquals(2, Currency.currencyMapper.size());
		} catch (FxCaluclatorException e) {
			e.printStackTrace();
			
		}
	}

	@Test
	public void testLoadValue() {
		try {
			initializerService.load(ClassLoader.getSystemResourceAsStream("currencymapTest.properties"),ClassLoader.getSystemResourceAsStream("currencydecimalTest.properties"));
			Assert.assertEquals(Integer.valueOf(2), Currency.currencyDecimalMap.get("AUD"));
			Collection<Map<String, Double>> values = Currency.currencyMapper.values();
			Assert.assertNotNull(values);
			for(Map<String, Double> map : values){
				Assert.assertNotNull(map.values());
			}
		} catch (FxCaluclatorException e) {
			e.printStackTrace();
		}
	}
	
		
	
	@Test
	public void testIOException1() {
		try {
			initializerService.load(ClassLoader.getSystemResourceAsStream("mapcurrency.properties"),ClassLoader.getSystemResourceAsStream("decimalcurrency.properties"));
			
		} catch (FxCaluclatorException exe) {
			Assert.assertEquals("For input string: \"0.8371x\"",exe.getMessage());
			Assert.assertEquals(2000,exe.getErrorCode());
		}
	}
	
}