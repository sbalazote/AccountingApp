package com.accountingapp.utils;

import java.util.HashMap;
import java.util.Map;

public class AccountingDataUtils {

	public Map<String,String> typeOfBillMap = null;
	
	public AccountingDataUtils(){
		
		typeOfBillMap = new HashMap<String,String>();
		
		typeOfBillMap.put("A", "001");
		typeOfBillMap.put("B", "006");
		typeOfBillMap.put("C", "011");
		typeOfBillMap.put("NCA", "003");
		typeOfBillMap.put("NCC", "013");
		typeOfBillMap.put("RC", "004");
		typeOfBillMap.put("SPA", "017");
		typeOfBillMap.put("SPB", "018");
		typeOfBillMap.put("TKA", "081");
		typeOfBillMap.put("TKB", "082");
	}

	public  Map<String, String> getTypeOfBillMap() {
		return typeOfBillMap;
	}
}
