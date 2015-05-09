package com.accountingapp.process;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.accountingapp.exc.IncorrectDataException;
import com.accountingapp.utils.AccountingDataUtils;

public class BillFormatter {

	public static String getBillDate(String date) {
		
		String[] splitteddate = date.split("/");
		String billDate= splitteddate[2] + splitteddate[1] + splitteddate[0];
		
		return billDate;
	}
	
	public static String getDuePayDate(String date) throws ParseException {
		
		if (!date.equals("00000000")) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date billOfdate = formatter.parse(date);
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(billOfdate.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 10);
		Date dateAddedTen = new Date(cal.getTimeInMillis());
		String dateToString = formatter.format(dateAddedTen);
		String[] splitteddate = dateToString.split("/");
		String billDate= splitteddate[2] + splitteddate[1] + splitteddate[0];
		return billDate;
		} else {
			return "00000000";
		}
		
		
	}
	
	public static String getBillType(String type){
		
		AccountingDataUtils ad = new AccountingDataUtils();
		return ad.getTypeOfBillMap().get(type);
	}
	
	public static String getPointOfSale(String pointSale){
		
		int size = 5;
		for(int i=0; i < size - pointSale.length() ; i++);
			pointSale= "0" + pointSale ;
		
		return pointSale;
	}
	
	public static String getBillNumber(String billNumber){
			
		if(billNumber.contains("-"))
			billNumber=billNumber.split("-")[1];
		
		billNumber = String.format("%20s", billNumber ).replace(' ', '0');
		
		return billNumber;
		
	}
	
	public static String getImportNumber(String importNumber){
	
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

	public static String getSellerIDType(String id){
		//Note: For now, we will assume that the default value will be 80, for any change or a bussiness rule, we will implement the code here
		if(id.isEmpty())
			id="80";	
		return id;
	}
	
	public static String getSellerID(String idSeller) throws IncorrectDataException{

		int size = 20;
		StringBuffer sb = new StringBuffer("");

		if(!idSeller.isEmpty()){
			String[] idSellerSplitted = idSeller.split("-");
			try {
				idSeller = idSellerSplitted[0] + idSellerSplitted[1] + idSellerSplitted[2];
				if ((idSellerSplitted[0].length() < 2) || (idSellerSplitted[1].length() < 8) || (idSellerSplitted[2].length() < 1)) {
					throw new IncorrectDataException("Número de identificación del vendedor inválido. Por favor, revise la planilla.");	
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				throw new IncorrectDataException("Número de identificación del vendedor inválido. Por favor, revise la planilla.");
			}
			for(int i=0; i < size - idSeller.length();i++)
				sb.append("0");
			sb.append(idSeller);
		}else{
			for(int i=0; i < size;i++)
				sb.append(' ');
		}
		return sb.toString();
	}
	
	public static String getSellerName(String name){
		
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
			name = sbSellerName.toString();
		}
		
		return name;
	}
	
	public static String getAnyPrice(String price){
		
		StringBuffer sb = new StringBuffer("");
		int size=0;
		if(!price.isEmpty()){
			size = 13;
			String[] priceSplitted = price.split(",");
			String integerPart = priceSplitted[0];
			String decimalPart = priceSplitted[1];
						
			for(int i=0; i < size - integerPart.length(); i++){
				sb.append("0");
			}
			sb.append(integerPart).append(decimalPart);
			
		}else{
			size=15;
			for(int i=0; i < size; i++){
				sb.append("0");
			}
		}
				
		return sb.toString();
	}
	
	public static String getCurrencyCode(String code){
		/*By default value we will assume de string PES, 
		 * recieving a blank as a parameter, 
		 * if not, we would add the code here
		 */
		String currencyCode = null;
		if(code.isEmpty())
			currencyCode = "PES";
		
		return currencyCode;
	}
	
	public static String getTypeExchange(String number){
	
		int integerPartSize = 4;
		int decimalPartSize = 6;
		String exchange = null;
		if(number.isEmpty())
			exchange = "1,00";
		
		String[] exchangeSplitted = exchange.split(",");
		String exchangeIntPart = exchangeSplitted[0];
		String exchangeDecPart = exchangeSplitted[1];
		StringBuffer sbInt = new StringBuffer();
		StringBuffer sbDec = new StringBuffer();
		for(int i=0; i < integerPartSize - exchangeIntPart.length(); i++){
			sbInt.append("0");
		}
		
		for(int j=0; j < decimalPartSize - exchangeDecPart.length(); j++){
			sbDec.append("0");
		}
		
		return sbInt.append(exchangeIntPart).append(exchangeDecPart).append(sbDec).toString();
		
	}
	
	public static String getQuantityOfAlicIva(String quantity){
		/*
		 * This quantity depends on the quantity of different iva tax is applied to the same bill
		 * I've seen 3 different types of iva 10,5%; 21,00% adn 27,00%
		 * By a default value, we would assume
		 * 
		 */
		if (quantity.isEmpty())
			quantity="1";
		
		return quantity;
	}

	public static String getOperationCode(String code){
		
		String opCode = null;
		if(code.isEmpty())
			opCode = "0";
		
		return opCode;
	}
	
	/* NOTE: Iva computable, otros tributos e iva comision usan el metodo
	 * getAnyPrice()
    */
	
	public static String getCUITIssuer(String cuit) throws IncorrectDataException{
		
		int size = 11;
		String cuitIssuer = null;
		
		if(!cuit.isEmpty()){
			
			String[] cuitIssuerSplitted = cuit.split("-");
			if ((cuitIssuerSplitted[0].length() < 2) || (cuitIssuerSplitted[1].length() < 8) || (cuitIssuerSplitted[2].length() < 1)) {
				throw new IncorrectDataException("Número de identificación del vendedor inválido. Por favor, revise la planilla.");	
			}
			cuitIssuer = cuitIssuerSplitted[0] + cuitIssuerSplitted[1] + cuitIssuerSplitted[2];
		
		}else{
			
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i < size ;i++)
				sb.append("0");
			
			cuitIssuer = sb.toString();
		}
		
		return cuitIssuer;
	}
	
	public static String getDenominationIssuer(String denomination){
		
		int size = 30;
		StringBuffer sb = new StringBuffer("");
		String denominationIssuer = null;
		
		if(denomination.isEmpty()){
			for(int i=0; i < size; i++)
				sb.append(' ');
			denominationIssuer = sb.toString();	
		}else{
			StringBuffer sbdenom = new StringBuffer(denomination);
			for(int j=0; j < size-denomination.length(); j++){
				sb.append(' ');
			}	
			sbdenom.append(sb);
			denominationIssuer = sbdenom.toString();
		}
	
		return denominationIssuer;
	}
		
}
