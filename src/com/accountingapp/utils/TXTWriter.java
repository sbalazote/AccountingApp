package com.accountingapp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.accountingapp.exc.IncorrectDataException;
import com.accountingapp.obj.IvaBillBuyObjectReader;
import com.accountingapp.obj.IvaBillSellObjectReader;
import com.accountingapp.process.BillFormatter;

public class TXTWriter {

	public XLSReader xlsReader;
	public String pathToWrite = null;
	
	
	public TXTWriter(String path){
		pathToWrite = path;
				
	}
	
	public void writeToTXTIvaBuy() throws IncorrectDataException{
		
		List<IvaBillBuyObjectReader> listIvaBillObject = xlsReader.getObjectListBuy();
	
		File fileToWrite = new File(pathToWrite);
		FileWriter fwriter = null;
		BufferedWriter bw = null;
		StringBuffer sb = null;
		try {
			fwriter = new FileWriter(fileToWrite);
			bw = new BufferedWriter(fwriter);			
			sb = new StringBuffer("");
			
			for(IvaBillBuyObjectReader opb : listIvaBillObject){
				
				sb.append(BillFormatter.getBillDate(opb.getBillDate()));
				sb.append(BillFormatter.getBillType(opb.getBillType()));
				sb.append(BillFormatter.getPointOfSale(opb.getPointOfSale()));
				sb.append(BillFormatter.getBillNumber(opb.getBillNumber()));
				sb.append(BillFormatter.getImportNumber(opb.getImportNumber()));
				sb.append(BillFormatter.getSellerIDType(opb.getSellerIDType()));
				sb.append(BillFormatter.getSellerID(opb.getSellerIDNumber()));
				sb.append(BillFormatter.getSellerName(opb.getSellerFullName()));			
				sb.append(BillFormatter.getAnyPrice(opb.getTotalPriceOperation()));
				sb.append(BillFormatter.getAnyPrice(opb.getTotalPriceConcepts()));
				sb.append(BillFormatter.getAnyPrice(opb.getTotalPriceExemptOp()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfIVA()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfNationalTaxes()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfIngrBrutTax()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfMunicipalTax()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfInternalTaxes()));
				sb.append(BillFormatter.getCurrencyCode(opb.getCurrencyCode()));
				sb.append(BillFormatter.getTypeExchange(opb.getTypeOfExchange()));
				sb.append(BillFormatter.getQuantityOfAlicIva(opb.getQuantityAlicIva()));
				sb.append(BillFormatter.getOperationCode(opb.getCodeOperation()));
				sb.append(BillFormatter.getAnyPrice(opb.getComputableTaxCredit()));
				sb.append(BillFormatter.getAnyPrice(opb.getOthersTributs()));
				sb.append(BillFormatter.getCUITIssuer(opb.getCuitIssuer()));
				sb.append(BillFormatter.getDenominationIssuer(opb.getDenominationIssuer()));
				sb.append(BillFormatter.getAnyPrice(opb.getBillType()));
				
				bw.write(sb.toString());
				sb.delete(0, sb.length());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeToTXTIvaSell() throws IncorrectDataException{
		
		List<IvaBillSellObjectReader> listIvaSellBillObject = xlsReader.getObjectListSell();
		
		File fileToWrite = new File(pathToWrite);
		FileWriter fwriter = null;
		BufferedWriter bw = null;
		StringBuffer sb = null;
		
		try {
			fwriter = new FileWriter(fileToWrite);
			bw = new BufferedWriter(fwriter);			
			sb = new StringBuffer("");
		
			for(IvaBillSellObjectReader opb : listIvaSellBillObject){
				
				sb.append(BillFormatter.getBillDate(opb.getBillDate()));
				sb.append(BillFormatter.getBillType(opb.getBillType()));
				sb.append(BillFormatter.getPointOfSale(opb.getPointOfSale()));
				sb.append(BillFormatter.getBillNumber(opb.getBillNumber()));
				sb.append(BillFormatter.getBillNumber(opb.getBillEndNumber()));
				sb.append(BillFormatter.getSellerIDType(opb.getSellerIDType()));
				sb.append(BillFormatter.getSellerID(opb.getSellerIDNumber()));
				sb.append(BillFormatter.getSellerName(opb.getSellerFullName()));			
				sb.append(BillFormatter.getAnyPrice(opb.getTotalPriceOperation()));
				sb.append(BillFormatter.getAnyPrice(opb.getTotalPriceConcepts()));
				sb.append(BillFormatter.getAnyPrice(opb.getUnCategorizePerceps()));
				sb.append(BillFormatter.getAnyPrice(opb.getTotalPriceExemptOp()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfNationalTaxes()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfIngrBrutTax()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfMunicipalTax()));
				sb.append(BillFormatter.getAnyPrice(opb.getPriceOfInternalTaxes()));
				sb.append(BillFormatter.getCurrencyCode(opb.getCurrencyCode()));
				sb.append(BillFormatter.getTypeExchange(opb.getTypeOfExchange()));
				sb.append(BillFormatter.getQuantityOfAlicIva(opb.getQuantityAlicIva()));
				sb.append(BillFormatter.getOperationCode(opb.getCodeOperation()));
				sb.append(BillFormatter.getAnyPrice(opb.getOthersTributs()));
				sb.append(BillFormatter.getBillDate(opb.getDuePayDate()));
				
				bw.write(sb.toString());
				sb.delete(0, sb.length());
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
