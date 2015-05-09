package com.accountingapp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.accountingapp.exc.IncorrectDataException;
import com.accountingapp.model.TaxPurchaseBill;
import com.accountingapp.model.TaxSaleBill;
import com.accountingapp.process.BillFormatter;

public class TXTWriter {

	public XLSReader xlsReader;
	public String pathToWrite = null;
	
	
	public TXTWriter(String path,XLSReader xlsReader){
		pathToWrite = path;
		this.xlsReader = xlsReader;
				
	}
	
	public void writeToTXTIvaBuy() throws IncorrectDataException{
		
		List<TaxPurchaseBill> listIvaBillObject = this.xlsReader.getObjectListBuy();
	
		File fileToWrite = new File(pathToWrite);
		FileWriter fwriter = null;
		BufferedWriter bw = null;
		StringBuffer sb = null;
		try {
			fwriter = new FileWriter(fileToWrite);
			bw = new BufferedWriter(fwriter);			
			sb = new StringBuffer("");
			
			for(TaxPurchaseBill opb : listIvaBillObject){
				
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
				sb.append(BillFormatter.getAnyPrice(opb.getIvaCommission()));
				
				bw.write(sb.toString());
				bw.write("\n");
				bw.flush();
				sb.delete(0, sb.length());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void writeToTXTIvaSell() throws IncorrectDataException, ParseException{
		
		List<TaxSaleBill> listIvaSellBillObject = xlsReader.getObjectListSell();
		
		File fileToWrite = new File(pathToWrite);
		FileWriter fwriter = null;
		BufferedWriter bw = null;
		StringBuffer sb = null;
		
		try {
			fwriter = new FileWriter(fileToWrite);
			bw = new BufferedWriter(fwriter);			
			sb = new StringBuffer("");
		
			for(TaxSaleBill opb : listIvaSellBillObject){
				
				sb.append(BillFormatter.getBillDate(opb.getBillDate()));
				sb.append(BillFormatter.getBillType(opb.getBillType()));
				sb.append(BillFormatter.getPointOfSale(opb.getPointOfSale()));
				sb.append(BillFormatter.getBillNumber(opb.getBillNumber()));
				sb.append(BillFormatter.getBillNumber(opb.getBillEndNumber()));
				sb.append(BillFormatter.getSellerIDType(opb.getBuyerIDType()));
				sb.append(BillFormatter.getSellerID(opb.getBuyerIDNumber()));
				sb.append(BillFormatter.getSellerName(opb.getBuyerFullName()));			
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
				
				if(!"".equals(opb.getDuePayDate())){
					sb.append(BillFormatter.getBillDate(opb.getDuePayDate()));
				}else{
					sb.append(BillFormatter.getDuePayDate(opb.getBillDate()));
				}
				 
				bw.write(sb.toString());
				bw.write("\n");
				bw.flush();
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
