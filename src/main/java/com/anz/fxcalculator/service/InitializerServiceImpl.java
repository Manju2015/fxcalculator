package com.anz.fxcalculator.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.anz.fxcalculator.bean.Currency;
import com.anz.fxcalculator.exception.FxCaluclatorException;


/**
 * This class loads the currencymap.properties and currencydecimal.properties
 * @author Manjunath
 *
 */
public class InitializerServiceImpl implements InitializerService{

	private final static Logger logger = Logger.getLogger(InitializerServiceImpl.class);

	/**
	 * Holds the input currency
	 */

	/**
	 * This method loads currencyMapper and currencyDecimalMap with currencymap.properties and currencydecimal.properties feed respectively 
	 * @param currencyMap
	 * @param decimalMap
	 * @throws FxCaluclatorException
	 */
	public synchronized void load(InputStream currencyMap,InputStream decimalMap) throws FxCaluclatorException {

			Properties properties = new Properties();
			Set<String> currencySet = new HashSet<String>();
			Map<String, Map<String, Double>> currencyMapper = new HashMap<String, Map<String,Double>>();
			Map<String, Integer> currencyDecimalMap = new ConcurrentHashMap<String, Integer>();
			//Check if the file exists 
			if(currencyMap!=null && decimalMap!=null){
				try {
					//load currency feed
					properties.load(currencyMap);
					for (String key : properties.stringPropertyNames()) {
						String currency1 = key.substring(0,3);
						String currency2 = key.substring(3);
						String value = properties.getProperty(key);
						currencySet.add(currency1);
						currencySet.add(currency2);
						//add direct mapping
						addToMapper(currency2, currency1, Double.valueOf(value),currencyMapper);
						//add inverse mapping
						addToMapper(currency1, currency2, 1/Double.valueOf(value),currencyMapper);
					}
					
					updateAllValues(currencyMapper,currencySet);
					
					//load decimal values feed
					properties = new Properties();
					properties.load(decimalMap);
					for (String key : properties.stringPropertyNames()) {
						currencyDecimalMap.put(key, Integer.valueOf(properties.getProperty(key)));
					}
					
				} catch (NumberFormatException | IOException exe) {
					throw new FxCaluclatorException(2000,exe,exe.getMessage());
				}
			}else{
				throw new FxCaluclatorException(2001,"Feed files not found");
			}
			
			Currency.currencyMapper.putAll(currencyMapper);
			Currency.currencyDecimalMap.putAll(currencyDecimalMap);
			logger.info(currencyMapper);
			logger.info(currencyDecimalMap);
		
	}

	/**
	 * update the currencyMapper with all values
	 * @param currencySet 
	 * @param currencyMapper 
	 */
	private void updateAllValues(Map<String, Map<String, Double>> currencyMapper, Set<String> currencySet) {

		while(!criteria(currencyMapper,currencySet)){

			Map<String, Map<String, Double>> addNewParent = new HashMap<String, Map<String,Double>>();
			//retrieve child map
			for(String currency:currencyMapper.keySet()){
				Map<String, Double> map = currencyMapper.get(currency);
				if(logger.isDebugEnabled()){
					logger.debug("Starting Parent Currency "+currency);
					logger.debug("Initial child Map for "+currency +" "+map);
				}
				Map<String, Double> newEntries = new HashMap<String, Double>();
				//Lookout out its value as parent
				for(String key:map.keySet()){
					//Get its child nodes
					if(currencyMapper.get(key)!=null){
						Map<String, Double> childMap = currencyMapper.get(key);
						if(logger.isDebugEnabled()){
							logger.debug("Retrieved map for "+key +" "+childMap); 
						}
						//check what all entries are there
						//Get its inverse value
						//Calculate Direct value 
						for(String key1:childMap.keySet()){
							if(!map.containsKey(key1) && !key1.equalsIgnoreCase(currency)){
								newEntries.put(key1, childMap.get(key1)*map.get(key));
							}
						}

					}
				}
				if(logger.isDebugEnabled()){
					logger.debug("Adding new entries for "+ currency +" "+newEntries);
				}
				for(String key:newEntries.keySet()){
					if(!currency.equalsIgnoreCase(key)){
						map.putIfAbsent(key, newEntries.get(key));
					}
				}
				if(logger.isDebugEnabled()){
					logger.debug("New map for "+ currency +" "+map);
				}
			}
			for(String key:addNewParent.keySet()){
				currencyMapper.putIfAbsent(key, addNewParent.get(key));
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("Final Map "+currencyMapper);
			logger.debug("Final keySet "+currencySet);
		}

	}

	/**
	 * Criteria to check if all the Mapping is done.
	 * @param currencySet 
	 * @param currencyMapper 
	 * @return true if completed
	 */
	private boolean criteria(Map<String, Map<String, Double>> currencyMapper, Set<String> currencySet) {
		boolean flag = true;
		if(currencySet.size() == currencyMapper.size()){
			for(Map<String, Double> map:currencyMapper.values()){
				if((currencySet.size()-1)!=map.size()){
					flag = false;
					break;
				}
			}
		}else{
			flag = false;
		}
		return flag;
	}

	/**
	 * This method adds the mapping of currency2 mapping to currecy1 with value
	 * @param currency1
	 * @param currency2
	 * @param value
	 * @param currencyMapper 
	 */
	private void addToMapper(String currency1,
			String currency2, Double value, Map<String, Map<String, Double>> currencyMapper) {
		if(currencyMapper.get(currency2)!= null){
			currencyMapper.get(currency2).put(currency1, value);
		}else{
			Map<String,Double> internal = new HashMap();
			internal.put(currency1, value);
			currencyMapper.put(currency2, internal);
		}
	}
}