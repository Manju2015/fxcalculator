package com.anz.fxcalculator.main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.anz.fxcalculator.exception.FxCaluclatorException;
import com.anz.fxcalculator.service.FxService;
import com.anz.fxcalculator.service.FxServiceImpl;
import com.anz.fxcalculator.service.InitializerService;
import com.anz.fxcalculator.service.InitializerServiceImpl;

/**
 * Class initiates the application
 * @author Manjunath R
 *
 */
public class Main {

	private final static Logger logger = Logger.getLogger(Main.class);


	public static void main(String[] args) throws FxCaluclatorException {
		try{
			//Load the feed files
			InitializerService initializerService = new InitializerServiceImpl();
			
			initializerService.load(new FileInputStream("currencymap.properties"),new FileInputStream("currencydecimal.properties"));
			logger.debug("Started application");
			FxService service = new FxServiceImpl();
			//To read input from console
			Scanner scanner = new Scanner(System.in);
			System.out.println("Input formats. \n1. For exchange rate: <ccy1> <amount1> in <ccy2>");
			System.out.println("2. To update currency rate: Reload exchange rate");
			System.out.println("To quit enter q");
			while (true) {
				System.out.print("%> ");
				String input = scanner.nextLine();

				if ("q".equals(input)) {
					System.out.println("Exit!");
					break;
				}
				//call reload when exchange rate changes
				else if("Reload exchange rate".equalsIgnoreCase(input.trim())){
					initializerService.load(new FileInputStream("currencymap.properties"),new FileInputStream("currencydecimal.properties"));
					logger.debug("Reload complete");
				}else{
					System.out.println(service.execute(input));
				}
			}
			scanner.close();
		}catch(FxCaluclatorException | FileNotFoundException exe){
			System.out.println(exe.getMessage());
		}

	}


}
