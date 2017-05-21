package com.anz.fxcalculator.service;

import java.io.InputStream;

import com.anz.fxcalculator.exception.FxCaluclatorException;

public interface InitializerService {

	public void load(InputStream currencyMap,InputStream decimalMap) throws FxCaluclatorException;
	
}
