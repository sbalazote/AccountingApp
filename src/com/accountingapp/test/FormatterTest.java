package com.accountingapp.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

public class FormatterTest {

	@Test
	public void test() {
		
		String date = "26/02/2015";
		String[] splitteddate = date.split("/");
		String dateformatted = splitteddate[2] + splitteddate[1] + splitteddate[0];
		assertEquals("20150226", dateformatted);
	}
	
	@Test
	public void testPtoVenta(){
		
		int tamanio = 5;
		String puntoVta= "0001";
		for(int i=0; i < tamanio - puntoVta.length() ; i++);
			puntoVta= "0" + puntoVta ;
		
		assertEquals("00001", puntoVta);
		System.out.println(puntoVta);
	}
	
	@Test
	public void testNumeroFactura(){
		
		int tamanio = 20;
		String numfactura= "00013301";
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < tamanio - numfactura.length() ; i++)
			sb.append("0");
		
		assertEquals("00000000000000013301".length(), sb.append(numfactura).toString().length());
		
		
	}
	
	@Test
	public void testImportacionEmpty(){
		
		int tamanio = 16;
		String importNumber = "";
		StringBuffer sb = new StringBuffer(importNumber);
		for(int i=0; i < tamanio; i++){
			sb.append(' ');
		}	
				
		assertEquals("                ",sb.toString());
	};

	@Test
	public void testImportacionNotEmpty(){
		
		int tamanio = 16;
		String importNumber = "AX4C67";
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < tamanio-importNumber.length(); i++){
			sb.append("0");
		}	
		sb.append(importNumber);		
		assertEquals("0000000000AX4C67",sb.toString());
	}
	
	@Test
	public void testIDVendedor() throws Exception {
		int tamanio = 20;
		String idVendedor= "20-24819102-4";
		String[] idVendedorSplitted = idVendedor.split("-");
		if(idVendedorSplitted[1].length() < 8){
			throw new Exception ("Tamanio de cuit invalido");	
		}
		String fullIdVendedor = idVendedorSplitted[0] + idVendedorSplitted[1] + idVendedorSplitted[2];
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < tamanio - fullIdVendedor.length();i++)
			sb.append("0");
		sb.append(fullIdVendedor);
		
		assertEquals("00000000020248191024",sb.toString());
				
	}
	
	@Test
	public void testNombreVendedor(){
		int size = 30;
		String name="Cerveceria";
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

		assertEquals(30, name.length());
	}
	
	@Test
	public void testImporte(){
		
		int size = 13;
		String importe ="64,77";
		String[] priceSplitted = importe.split(",");
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
		
		assertEquals("000000000006477",sb.toString());	
	}
	
	@Test
	public void testTipoCambio(){
		
		int integerPartSize = 4;
		int decimalPartSize = 6;
		String exchange = "1,00";
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
		
		String result = sbInt.append(exchangeIntPart).append(exchangeDecPart).append(sbDec).toString();
		assertEquals("0001000000".length(),result.length());	
	}
	
	@Test
	public void testNullDate() throws ParseException{
		String date="28/02/2015";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date billOfdate = formatter.parse(date);
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(billOfdate.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 10);
		Date dateAddedTen = new Date(cal.getTimeInMillis());
		String dateToString = formatter.format(dateAddedTen);
	
		System.out.println(dateToString);
	}
		
}
