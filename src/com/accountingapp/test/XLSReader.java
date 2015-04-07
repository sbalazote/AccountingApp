package com.accountingapp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.accountingapp.obj.IvaBillObject;
import com.accountingapp.utils.Constants;

public class XLSReader {
	
	public void readFile(){
		HSSFWorkbook workbook = null;
		
		try {

			FileInputStream file = new FileInputStream(new File("C:\\Users\\Usuario\\Desktop\\IVAbarbaraFEBRERO2015.xls"));

			//Get the workbook instance for XLS file 
			workbook = new HSSFWorkbook(file);
			
			
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(1);

			System.out.println(sheet.getLastRowNum());
			System.out.println(sheet.getPhysicalNumberOfRows());
			System.out.println(sheet.getSheetName());
			
			
			//Iterate through each rows from first sheet
			//Iterator<Row> rowIterator = sheet.iterator();
			//while(rowIterator.hasNext()) {
			//	Row row = rowIterator.next();

				//For each row, iterate through each columns
				//Iterator<Cell> cellIterator = row.cellIterator();
				boolean validData = true;
				HSSFRow row = null;
				int IvaBillCurrentRowNum = Constants.IVA_BILL_INITIAL_ROW_NUM;
				while (validData) {
					System.out.println(IvaBillCurrentRowNum);
					row = sheet.getRow(IvaBillCurrentRowNum);
					validData = readTaxPurchase(row);
					IvaBillCurrentRowNum++;
				}
				
			file.close();
			FileOutputStream out = 
					new FileOutputStream(new File("C:\\Users\\Usuario\\Desktop\\output.xls"));
			workbook.write(out);
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Mapeo numero de celda -> nombre del campo:
	 * 
	 * 3 -> Fecha (Formato AAAAMMDD)
	 * 4 -> TipoDoc
	 * 5 -> N°SUC
	 * 6 -> N°EMISION
	 * 7 -> Nombre Proveedor
	 * 8 -> CUIT
	 * 9 -> Neto FC
	 * 10 -> Inter.
	 * 11 -> IVA Fact.
	 * 12 -> Ingr. Br.
	 * 13 -> IVA
	 * 14 -> exent.
	 * 15 -> TOTAL FC
	 * 16 -> IVA %
	 * 17 -> Proveedor
	 * 18 -> Cuit
	 */
	public boolean readTaxPurchase(HSSFRow row) {

		IvaBillObject ivaBillObject = new IvaBillObject();

		//fecha de comprobante
		Cell cell = row.getCell(Constants.BILL_DATE_CELL_NUM);
		// si la primer celda que indica fecha esta en blanco termine de leer.
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return false;
		} else {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");//"EEE MMM dd HH:mm:ss z yyyy"
			String billDate = df.format(cell.getDateCellValue());
			ivaBillObject.setBillDate(billDate);


			//tipo de comprobante
			cell = row.getCell(Constants.BILL_TYPE_CELL_NUM);
			String billType = cell.getStringCellValue();
			ivaBillObject.setBillType(billType);

			//punto de venta
			cell = row.getCell(Constants.POINT_OF_SALE_CELL_NUM);
			String pointOfSale = cell.getStringCellValue();
			ivaBillObject.setPointOfSale(pointOfSale);

			//numero de comprobante
			cell = row.getCell(Constants.BILL_NUMBER_CELL_NUM);
			String billNumber = cell.getStringCellValue();
			ivaBillObject.setBillNumber(billNumber);

			//numero de despacho de importacion
			cell = row.getCell(Constants.IMPORT_NUMBER_CELL_NUM);
			ivaBillObject.setImportNumber("");

			//codigo de documento vendedor
			cell = row.getCell(Constants.SELLER_ID_TYPE_CELL_NUM);
			ivaBillObject.setSellerIDType("");

			//numero de identificacion del vendedor
			cell = row.getCell(Constants.SELLER_ID_NUMBER_CELL_NUM);
			String sellerIDNumber = cell.getStringCellValue();
			ivaBillObject.setSellerIDNumber(sellerIDNumber);

			//apellido y nombre del vendedor
			cell = row.getCell(Constants.SELLER_FULLNAME_CELL_NUM);
			String sellerFullName = cell.getStringCellValue();
			ivaBillObject.setSellerFullName(sellerFullName);

			//importe total operacion
			cell = row.getCell(Constants.TOTAL_PRICE_OPERATION_CELL_NUM);
			String totalPriceOperation = Double.toString(cell.getNumericCellValue());
			ivaBillObject.setTotalPriceOperation(totalPriceOperation);

			//importe total de conc. que no integral el precio neto gravado
			cell = row.getCell(Constants.TOTAL_PRICE_CONCEPTS_CELL_NUM);
			ivaBillObject.setTotalPriceConcepts("");

			//importe operaciones exentas
			cell = row.getCell(Constants.TOTAL_PRICE_EXEMPT_OP_CELL_NUM);
			String totalPriceExemptOp = Double.toString(cell.getNumericCellValue());
			ivaBillObject.setTotalPriceExemptOp(totalPriceExemptOp);

			//importe de perc. o pagos a cta del iva
			cell = row.getCell(Constants.PRICE_OF_IVA_CELL_NUM);
			String priceOfIVA = Double.toString(cell.getNumericCellValue());
			ivaBillObject.setPriceOfIVA(priceOfIVA);

			//importe de perc. a cta de impuestos nacionales
			cell = row.getCell(Constants.PRICE_OF_NATIONAL_TAXES_CELL_NUM);
			ivaBillObject.setPriceOfNationalTaxes("");

			//importe de percepciones de ing. brutos
			cell = row.getCell(Constants.PRICE_OF_INGR_BRUT_TAX_CELL_NUM);
			String priceOfIngrBrutTax = Double.toString(cell.getNumericCellValue());
			ivaBillObject.setPriceOfIngrBrutTax(priceOfIngrBrutTax);

			//importe de percepciones de impuestos municipales
			cell = row.getCell(Constants.PRICE_OF_MUNICIPAL_TAX_CELL_NUM);
			ivaBillObject.setPriceOfMunicipalTax("");

			// importe impuestos internos
			cell = row.getCell(Constants.PRICE_OF_INTERNAL_TAXES_CELL_NUM);
			String priceOfInternalTaxes = Double.toString(cell.getNumericCellValue());
			ivaBillObject.setPriceOfInternalTaxes(priceOfInternalTaxes);

			//codigo de moneda
			cell = row.getCell(Constants.CURRENCY_CODE_CELL_NUM);
			ivaBillObject.setCurrencyCode("");

			//tipo de cambio
			cell = row.getCell(Constants.TYPE_OF_EXCHANGE_CELL_NUM);
			ivaBillObject.setTypeOfExchange("");

			//cantidad de alicuotas de iva
			cell = row.getCell(Constants.QUANTITY_ALIC_IVA_CELL_NUM);
			ivaBillObject.setQuantityAlicIva("");

			//codigo de operacion
			cell = row.getCell(Constants.CODE_OPERATION_CELL_NUM);
			ivaBillObject.setCodeOperation("");

			//credito fiscal computable
			cell = row.getCell(Constants.COMPUTABLE_TAX_CREDIT_CELL_NUM);
			ivaBillObject.setComputableTaxCredit("");

			//otros tributos
			cell = row.getCell(Constants.OTHERS_TRIBUTS_CELL_NUM);
			ivaBillObject.setOthersTributs("");

			//cuit emisor
			cell = row.getCell(Constants.CUIT_ISSUER_CELL_NUM);
			ivaBillObject.setCuitIssuer("");

			//denominacion del emisor
			cell = row.getCell(Constants.DENOMINATION_ISSUER_CELL_NUM);
			ivaBillObject.setDenominationIssuer("");

			//comision iva
			cell = row.getCell(Constants.IVA_COMMISSION_CELL_NUM);
			ivaBillObject.setIvaCommission("");

			System.out.println(ivaBillObject.toString());

			return true;
		}
	}
	
	public static void main(String[] args){
		
		XLSReader read = new XLSReader();
		read.readFile();
	}
}