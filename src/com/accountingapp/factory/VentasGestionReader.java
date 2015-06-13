package com.accountingapp.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.accountingapp.model.TaxPurchaseBill;
import com.accountingapp.model.TaxSaleBill;
import com.accountingapp.utils.Constants;

public class VentasGestionReader extends Reader {

	@Override
	public void readFile(File xlsFile) {
			
		HSSFWorkbook workbook = null;
		HSSFRow row = null;
		
		try {
			FileInputStream file = new FileInputStream(xlsFile);

			//Get the workbook instance for XLS file 
			workbook = new HSSFWorkbook(file);
			
			
			//Obtengo la pagina de ventas.
			HSSFSheet taxSalesSheet = workbook.getSheetAt(0);
			boolean IvaBillSaleValidData = true;
			int IvaBillSaleCurrentRowNum = 2; //constante cambiar
			
			while (IvaBillSaleValidData) {
				System.out.println(IvaBillSaleCurrentRowNum);
				row = taxSalesSheet.getRow(IvaBillSaleCurrentRowNum);
				IvaBillSaleValidData = readTaxPurchase(row);
				IvaBillSaleCurrentRowNum++;
			}
			
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean readTaxPurchase(HSSFRow row){
		
		TaxSaleBill ivaBillSaleObject = new TaxSaleBill();

		//fecha de comprobante
	
		Cell cell = row.getCell(1);
		// si la primer celda que indica fecha esta en blanco termine de leer.
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return false;
		} else {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");//"EEE MMM dd HH:mm:ss z yyyy"
			String billDate = df.format(cell.getDateCellValue());
			ivaBillSaleObject.setBillDate(billDate);
	
			//tipo de comprobante
			cell = row.getCell(Constants.TAX_PURCHASE_BILL_TYPE_CELL_NUM);
			String billType = cell.getStringCellValue();
			ivaBillSaleObject.setBillType(billType);

		}
		
		return true;
	}

}
