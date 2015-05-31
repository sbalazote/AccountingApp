package com.accountingapp.factory;

public class FactoryProducer {

	public static AbstractFactoryReader getFactory(String choice){
		
		if(choice.equalsIgnoreCase("READER")){
			return new ReaderFactory();
		}
		return null;
		
	}
}
