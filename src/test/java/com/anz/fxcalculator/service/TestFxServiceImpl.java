package com.anz.fxcalculator.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.anz.fxcalculator.exception.FxCaluclatorException;
import com.anz.fxcalculator.service.FxServiceImpl;
import com.anz.fxcalculator.service.InitializerService;
import com.anz.fxcalculator.service.InitializerServiceImpl;

public class TestFxServiceImpl {
	
	InitializerService initializerService = null;
	
	@Before
    public void setUp() throws FxCaluclatorException {
		initializerService = new InitializerServiceImpl();
		initializerService.load(ClassLoader.getSystemResourceAsStream("currencymap.properties"),ClassLoader.getSystemResourceAsStream("currencydecimal.properties"));
    }
	
	@Test
	public void testResultDirect(){
		FxService executor = new FxServiceImpl();
		Assert.assertEquals("AUD 100.00 = USD 83.71", executor.execute("AUD 100.00 in USD"));
	}
	
	@Test
	public void testResult11(){
		FxService executor = new FxServiceImpl();
		Assert.assertEquals("AUD 100.00 = AUD 100.00", executor.execute("AUD 100.00 in AUD"));
	}
	
	@Test
	public void testResultCCY(){
		FxService executor = new FxServiceImpl();
		Assert.assertEquals("AUD 100.00 = DKK 505.76", executor.execute("AUD 100.00 in DKK"));
	}
	
	@Test
	public void testResultInverse(){
		FxService executor = new FxServiceImpl();
		Assert.assertEquals("JPY 100 = USD 0.83", executor.execute("JPY 100 in USD"));
	}

	@Test
	public void testResultInvalid(){
		FxService executor = new FxServiceImpl();
		Assert.assertEquals("Unable to find rate for KRW/FJD", executor.execute("KRW 1000.00 in FJD"));
	}
	
	@After
	public void destroy(){
		initializerService = null;
	}
}
