package com.accountingapp.test;

import static org.junit.Assert.*;

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
	};
	
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
}
