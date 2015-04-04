package com.accountingapp.process;

import java.util.ArrayList;
import java.util.List;

import com.accountingapp.obj.IvaBillObject;
import com.accountingapp.utils.AccountingDataUtils;

public class BillFormatter {

	private String getBillDate(String date){
		
		String[] splitteddate = date.split("/");
		String billDate = splitteddate[2] + splitteddate[1] + splitteddate[0];
		
		return billDate;
	}
	
	private String getBillType(String type){
		
		AccountingDataUtils ad = new AccountingDataUtils();
		return ad.getTypeOfBillMap().get(type);
	}
	
	private String getPointOfSale(String pointSale){
		
		int tamanio = 5;
		for(int i=0; i < tamanio - pointSale.length() ; i++);
			pointSale= "0" + pointSale ;
		
		return pointSale;
	}
	
	private String getBillNumber(String pointSale, String billNumber){
		
		int tamanio = 20;	
		for(int i=0; i < tamanio - billNumber.length() ; i++);
			billNumber= "0" + pointSale ;
	
		return billNumber;
		
	}
	
	private String getImportNumber(String importNumber){
	
		int tamanio = 16;
		StringBuffer sb = new StringBuffer("");
		if(importNumber.isEmpty()){
			for(int i=0; i < tamanio; i++){
				sb.append(' ');
			}	
		}else{
			for(int j=0; j < tamanio-importNumber.length(); j++){
				sb.append("0");
			}	
			sb.append(importNumber);
		}
	
		return sb.toString();
	}


	public List<IvaBillObject> createIvaBillObjectList(){

		List<IvaBillObject> ivaBillObjList = new ArrayList<IvaBillObject>();
		
		//TODO: Iterate for each reader object and then give the corresponding format to the new object
		IvaBillObject ivaBillObj = new IvaBillObject();
		
		
		
		ivaBillObjList.add(ivaBillObj);
		return ivaBillObjList;
		
	}
}
