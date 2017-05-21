package com.anz.fxcalculator.service;

import org.apache.log4j.Logger;

import com.anz.fxcalculator.bean.Currency;
import com.anz.fxcalculator.exception.FxCaluclatorException;
import com.anz.fxcalculator.validator.Validator;

/**
 * Convert the currency
 * @author Manjunath
 *
 */
public class FxServiceImpl implements FxService{

	private final static Logger logger = Logger.getLogger(FxServiceImpl.class);

	/**
	 * This method looks up the currencyMapper and returns appropriate value. 
	 */
	@Override
	public String execute(String input) {
		logger.debug("Input String "+input);
		String result = null;
		//check for format
		String[] values = input.split(" ");
		try{
			if(Validator.validate(values)){
				//calculate
				//1:1 mapping
				if(values[0].equalsIgnoreCase(values[3])){
					result = result(values[0], values[3], values[1], Double.valueOf(values[1]));
				}
				//Direct mapping
				else if(Currency.currencyMapper.get(values[0])!=null && Currency.currencyMapper.get(values[0]).get(values[3])!=null){
					//Look up for given currency map
					//entry for mapping currency exist
					result = result(values[0], values[3], values[1], Double.valueOf(values[1])*(Currency.currencyMapper.get(values[0]).get(values[3])));
				}
			}else{
				result = "Unable to find rate for "+values[0]+"/"+values[3];
			}
		}catch(FxCaluclatorException exe){
			result = exe.getMessage();
		}
		
		logger.debug("Result String "+result);
		return result;


	}

	/**
	 * Returns concatenated result string 
	 * @param currency1
	 * @param currency2
	 * @param input
	 * @param value
	 * @return
	 */
	private String result(String currency1, String currency2, String input, Double value){
		String strDouble = "";
		if(Currency.currencyDecimalMap.get(currency2)!=null){
			strDouble = String.format("%."+Currency.currencyDecimalMap.get(currency2)+"f", value); 
		}else{
			strDouble = String.format("%.2f", value);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(currency1);
		buffer.append(" ");
		buffer.append(input);
		buffer.append(" = ");
		buffer.append(currency2);
		buffer.append(" ");
		buffer.append(strDouble);
		return buffer.toString();

	}
}
