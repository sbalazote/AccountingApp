package com.accountingapp.process;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.accountingapp.exc.IncorrectDataException;
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
		
		int size = 5;
		for(int i=0; i < size - pointSale.length() ; i++);
			pointSale= "0" + pointSale ;
		
		return pointSale;
	}
	
	private String getBillNumber(String pointSale, String billNumber){
		
		int size = 20;	
		for(int i=0; i < size - billNumber.length() ; i++);
			billNumber= "0" + pointSale ;
	
		return billNumber;
		
	}
	
	private String getImportNumber(String importNumber){
	
		int size = 16;
		StringBuffer sb = new StringBuffer("");
		if(importNumber.isEmpty()){
			for(int i=0; i < size; i++){
				sb.append(' ');
			}	
		}else{
			for(int j=0; j < size-importNumber.length(); j++){
				sb.append("0");
			}	
			sb.append(importNumber);
		}
	
		return sb.toString();
	}

	private String getsellerIDType(){
		//Note: For now, we will assume that the default value will be 80, for any change or a bussiness rule, we will implement the code here
		return "80";
	}
	
	private String getSellerID(String idSeller) throws IncorrectDataException{
		
		int size = 20;
		String[] idSellerSplitted = idSeller.split("-");
		if(idSellerSplitted[1].length() < 8){
			throw new IncorrectDataException();	
		}
		idSeller = idSellerSplitted[0] + idSellerSplitted[1] + idSellerSplitted[2];
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < size - idSeller.length();i++)
			sb.append("0");
		sb.append(idSeller);
		
		return sb.toString();

	}
	
	private String getSellerName(String name){
		
		int size = 30;
		StringBuffer sbSellerName = new StringBuffer(name);
		
		if(name.length() > 30){
			name = name.substring(0, 30);
		}else{		
			StringBuffer sbBlanks = new StringBuffer("");
			for(int i =0; i < size-sbSellerName.length();i++){
				sbBlanks.append(' ');
			}
			sbSellerName.append(sbBlanks);
		}
		
		return sbSellerName.toString();
	}
	
	private String getAnyPrice(String price){
		
		int size = 13;
		String[] priceSplitted = price.split(",");
		String integerPart = priceSplitted[0];
		String decimalPart = priceSplitted[1];
		
		if (integerPart.contains(".")){
			String[] integerPartSplitted = integerPart.split("\\.");
			integerPart= integerPartSplitted[0]+integerPartSplitted[1];
		}
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < size - integerPart.length(); i++){
			sb.append("0");
		}
		sb.append(integerPart).append(decimalPart);
		
		return sb.toString();
		
	}
	
	/*Falta darle formato a:
	 * codigo de moneda
	 * tipo de cambio
	 * cantidad de alicuotas iva
	 * codigo de operacion
	 * credito fiscal computable
	 * otros tributos
	 * cuit emisor/corredor
	 * denominacion del emisor
	 */
	
	public List<IvaBillObject> createIvaBillObjectList(){

		List<IvaBillObject> ivaBillObjList = new ArrayList<IvaBillObject>();
		
		//TODO: Iterate for each reader object and then give the corresponding format to the new object
		IvaBillObject ivaBillObj = new IvaBillObject();
		
		
		
		ivaBillObjList.add(ivaBillObj);
		return ivaBillObjList;
		
	}
}
