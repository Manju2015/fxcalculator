package com.anz.fxcalculator.exception;

import org.junit.Assert;
import org.junit.Test;

import com.anz.fxcalculator.exception.FxCaluclatorException;

public class TestFxCaluclatorException extends Exception {

	@Test
	public void testConstructor1(){
		FxCaluclatorException exe = new FxCaluclatorException(111,new Exception(),"Test Message");
		Assert.assertEquals(111, exe.getErrorCode());
		Assert.assertEquals("Test Message", exe.getMessage());
	}

	@Test
	public void testConstructor2(){
		FxCaluclatorException exe = new FxCaluclatorException(111,"Test Message");
		Assert.assertEquals(111, exe.getErrorCode());
		Assert.assertEquals("Test Message", exe.getMessage());
	}
	
	@Test
	public void testConstructor3(){
		FxCaluclatorException exe = new FxCaluclatorException(111,new Exception());
		Assert.assertEquals(111, exe.getErrorCode());
	}
	
}
