package com.anz.fxcalculator.validator;

import org.junit.Assert;
import org.junit.Test;

import com.anz.fxcalculator.exception.FxCaluclatorException;
import com.anz.fxcalculator.validator.Validator;


public class TestValidator {
	
	@Test
	public void testNumberFormatException(){
		String[] values = "AUD xx in USD".split(" ");
		try{
			Validator.validate(values);
		}catch(FxCaluclatorException exe){
			Assert.assertEquals(1000, exe.getErrorCode());
			Assert.assertEquals("Invalid input format. Please enter in below format\n<ccy1> <amount1> in <ccy2>", exe.getMessage());
		}
	}
	
	@Test
	public void testInvalidFormat(){
		String[] values = "AUD 100 of USD".split(" ");
		try{
			Validator.validate(values);
		}catch(FxCaluclatorException exe){
			Assert.assertEquals(1001, exe.getErrorCode());
			Assert.assertEquals("Invalid input format. Please enter in below format\n<ccy1> <amount1> in <ccy2>", exe.getMessage());
		}
	}
	
	@Test
	public void testInvalidFormat2(){
		String[] values = "AUD in USD".split(" ");
		try{
			Validator.validate(values);
		}catch(FxCaluclatorException exe){
			Assert.assertEquals(1002, exe.getErrorCode());
			Assert.assertEquals("Invalid input format. Please enter in below format\n<ccy1> <amount1> in <ccy2>", exe.getMessage());
		}
	}
	
	@Test
	public void testInvalidCurrency(){
		String[] values = "XXX 100 in YYY".split(" ");
		try {
			Assert.assertFalse(Validator.validate(values));
		} catch (FxCaluclatorException exe) {
			
		}
	}
	
	@Test
	public void testValidFormat(){
		String[] values = "AUD 100 in USD".split(" ");
		try{
			Assert.assertTrue(Validator.validate(values));
		}catch(FxCaluclatorException exe){
			exe.printStackTrace();
		}
	}

}
