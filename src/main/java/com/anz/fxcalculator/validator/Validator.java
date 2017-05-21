package com.anz.fxcalculator.validator;

import org.apache.log4j.Logger;

import com.anz.fxcalculator.bean.Currency;
import com.anz.fxcalculator.exception.FxCaluclatorException;

/**
 * Validator class to validate the input
 * @author Manjunath
 *
 */
public class Validator {
	
	private static Logger logger = Logger.getLogger(Validator.class);

	/**
	 * Check if the given input is in valid format
	 * @param values
	 * @return true if valid
	 * @throws FxCaluclatorException
	 */
	public static boolean validate(String[] values) throws FxCaluclatorException{
		logger.debug("Validating input");
		boolean flag = false;
		if(values.length==4){
			try{
				Double.valueOf(values[1]);
			}catch(NumberFormatException exception){
				throw new FxCaluclatorException(1000, exception, "Invalid input format. Please enter in below format\n<ccy1> <amount1> in <ccy2>");
			}
			if(!values[2].equalsIgnoreCase("in")){
				throw new FxCaluclatorException(1001, "Invalid input format. Please enter in below format\n<ccy1> <amount1> in <ccy2>");
			}
			if(Currency.currencyMapper.containsKey(values[0]) && Currency.currencyMapper.containsKey(values[3])){
				flag = true;
			}
		}else{
			throw new FxCaluclatorException(1002, "Invalid input format. Please enter in below format\n<ccy1> <amount1> in <ccy2>");
		}
		return flag;
	}
}
