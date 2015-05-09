package com.accountingapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.accountingapp.model.TaxPurchaseBill;
import com.accountingapp.model.TaxSaleBill;

public class XLSReader {
	
	public List<TaxPurchaseBill> objectListBuy = new ArrayList<TaxPurchaseBill>();
	public List<TaxSaleBill> objectListSell = new ArrayList<TaxSaleBill>();
	boolean initTicketGroup = false;
	float ticketGroupAmount = 0.0f;
	float BTypeBillGroupAmount = 0.0f;
	int ticketGroupFrom = 0, ticketGroupTo = 0, BTypeBillGroupFrom = 0, BTypeBillGroupTo = 0;
	
	
	public void readFile(File xlsFile){
		HSSFWorkbook workbook = null;
		
		try {
			FileInputStream file = new FileInputStream(xlsFile);

			//Get the workbook instance for XLS file 
			workbook = new HSSFWorkbook(file);
			
			
			//Obtengo las paginas de compras y ventas.
			HSSFSheet taxSalesSheet = workbook.getSheetAt(0);
			HSSFSheet taxPurchasesSheet = workbook.getSheetAt(1);

			System.out.println(taxPurchasesSheet.getLastRowNum());
			System.out.println(taxPurchasesSheet.getPhysicalNumberOfRows());
			System.out.println(taxPurchasesSheet.getSheetName());
			
			boolean IvaBillBuyValidData = true;
			HSSFRow row = null;
			int IvaBillBuyCurrentRowNum = Constants.TAX_PURCHASES_INITIAL_ROW_NUM;
			int taxSalesTicketsCurrentRowNum = Constants.TAX_SALES_TICKETS_INITIAL_ROW_NUM;
			boolean taxSalesATypeBillValidData = true;
			int taxSalesATypeBillCurrentRowNum = Constants.TAX_SALES_A_TYPE_BILL_INITIAL_ROW_NUM;
			boolean taxSalesBTypeBillValidData = true;
			int taxSalesBTypeBillCurrentRowNum = Constants.TAX_SALES_B_TYPE_BILL_INITIAL_ROW_NUM;
			
			// Leo las facturas de compras.
			while (IvaBillBuyValidData) {
				System.out.println(IvaBillBuyCurrentRowNum);
				row = taxPurchasesSheet.getRow(IvaBillBuyCurrentRowNum);
				IvaBillBuyValidData = readTaxPurchase(row);
				IvaBillBuyCurrentRowNum++;
			}
			
			
			// Leo la el mes-año al comienzo de la tabla de tickets.
			row = taxSalesSheet.getRow(Constants.TAX_SALES_TICKETS_INITIAL_ROW_NUM-1);
			Cell cell = row.getCell(Constants.TAX_SALE_TICKETS_MONTH_DATE_CELL_NUM);
			Date monthYear = cell.getDateCellValue();
			String ticketDate = CalendarUtils.getInstance().getLastDayOfMonth(monthYear);
			
			// Leo los tickets de ventas.
			for (int i = taxSalesTicketsCurrentRowNum; i < (taxSalesTicketsCurrentRowNum+31); i++) {
				row = taxSalesSheet.getRow(i);
				readTaxSaleTicket(row, ticketDate);
			}
			
			// Leo las facturas A de ventas.
			while (taxSalesATypeBillValidData) {
				row = taxSalesSheet.getRow(taxSalesATypeBillCurrentRowNum);
				taxSalesATypeBillValidData = readTaxSaleATypeBill(row);
				taxSalesATypeBillCurrentRowNum++;
			}

			// Leo las facturas B de ventas.
			while (taxSalesBTypeBillValidData) {
				row = taxSalesSheet.getRow(taxSalesBTypeBillCurrentRowNum);
				taxSalesBTypeBillValidData = readTaxSaleBTypeBill(row, ticketDate);
				taxSalesBTypeBillCurrentRowNum++;
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

	/**
	 * Este metodo procesa todos los TICKETs.
	 * 
	 * @param row
	 *            La fila leida de la planilla de calculo.
	 */
	private void readTaxSaleTicket(HSSFRow row, String ticketDate) {
		TaxSaleBill ivaBillObject = new TaxSaleBill();
		DecimalFormat decformat = new DecimalFormat("0.00");
		
		// Leo tickets por grupos hasta leer el mes completo.
		Cell cell = row.getCell(Constants.TAX_SALE_TICKET_TYPE_CELL_NUM);

		if ((cell.getCellType() == Cell.CELL_TYPE_BLANK) && (initTicketGroup)) {
			// aca lei un grupo de tickets.
			ivaBillObject.setBillDate(ticketDate);
			ivaBillObject.setBillType("TKB");
			ivaBillObject.setPointOfSale("0002");
			ivaBillObject.setBillNumber(String.valueOf(ticketGroupFrom));
			ivaBillObject.setBillEndNumber(String.valueOf(ticketGroupTo));
			ivaBillObject.setBuyerIDType("99");
			ivaBillObject.setBuyerIDNumber("");
			if (ticketGroupAmount > 1000.0f) {
				ivaBillObject.setBuyerFullName("VENTA GLOBAL DIARIA");
			} else {
				ivaBillObject.setBuyerFullName("");
			}
			ivaBillObject.setTotalPriceOperation(decformat.format(Math.abs(ticketGroupAmount)));
			ivaBillObject.setTotalPriceConcepts("");
			ivaBillObject.setUnCategorizePerceps("");
			ivaBillObject.setTotalPriceExemptOp("");
			ivaBillObject.setPriceOfNationalTaxes("");
			ivaBillObject.setPriceOfIngrBrutTax("");
			ivaBillObject.setPriceOfMunicipalTax("");
			ivaBillObject.setPriceOfInternalTaxes("");
			ivaBillObject.setCurrencyCode("");
			ivaBillObject.setTypeOfExchange("");
			ivaBillObject.setQuantityAlicIva("");
			ivaBillObject.setCodeOperation("");
			ivaBillObject.setOthersTributs("");
			ivaBillObject.setDuePayDate("0");
			objectListSell.add(ivaBillObject);
			System.out.println(ivaBillObject.toString());
			initTicketGroup = false;
			ticketGroupAmount = 0.0f;
			ticketGroupFrom = 0;
			ticketGroupTo = 0;
		} else if ((cell.getCellType() != Cell.CELL_TYPE_BLANK) && (!initTicketGroup)) {
			initTicketGroup = true;
			cell = row.getCell(Constants.TAX_SALE_TICKET_NUMBER_CELL_NUM);
			ticketGroupFrom = (int) cell.getNumericCellValue();
			cell = row.getCell(Constants.TAX_SALE_TICKET_TOTAL_CELL_NUM);
			ticketGroupAmount += cell.getNumericCellValue();
		} else if ((cell.getCellType() != Cell.CELL_TYPE_BLANK) && (initTicketGroup)) {
			cell = row.getCell(Constants.TAX_SALE_TICKET_NUMBER_CELL_NUM);
			ticketGroupTo = (int) cell.getNumericCellValue();
			cell = row.getCell(Constants.TAX_SALE_TICKET_TOTAL_CELL_NUM);
			ticketGroupAmount += cell.getNumericCellValue();
		}
	}

	/**
	 * Este metodo procesa las facturas A.
	 * 
	 * @param row
	 *            La fila leida de la planilla de calculo.
	 * @return true si la factura es valida; false en caso contrario.
	 */
	private boolean readTaxSaleATypeBill(HSSFRow row) {
		TaxSaleBill ivaBillObject = new TaxSaleBill();
		DecimalFormat decformat = new DecimalFormat("0.00");

		// Leo una factura.
		Cell cell = row.getCell(Constants.TAX_SALE_A_TYPE_BILL_CELL_NUM);
		// si la primer celda que indica tipo de documento esta en blanco termine de leer.
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return false;
		} else {
			//fecha de comprobante
			cell = row.getCell(Constants.TAX_SALE_A_TYPE_BILL_DATE_CELL_NUM);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");//"EEE MMM dd HH:mm:ss z yyyy"
			String billDate = df.format(cell.getDateCellValue());
			ivaBillObject.setBillDate(billDate);
			
			//tipo de comprobante
			// TODO que va aca?
			String billType = "A";
			ivaBillObject.setBillType(billType);

			//punto de venta
			String pointOfSale = "0001";
			ivaBillObject.setPointOfSale(pointOfSale);
			
			//numero de comprobante
			cell = row.getCell(Constants.TAX_SALE_A_TYPE_BILL_NUMBER_CELL_NUM);
			String billNumber = cell.getStringCellValue();
			ivaBillObject.setBillNumber(billNumber);
			ivaBillObject.setBillEndNumber(billNumber);
			
			//numero de identificacion del comprador
			cell = row.getCell(Constants.TAX_SALE_A_TYPE_BILL_BUYER_ID_NUMBER_CELL_NUM);
			String buyerIDNumber = cell.getStringCellValue();
			ivaBillObject.setBuyerIDNumber(buyerIDNumber);

			//apellido y nombre del comprador
			cell = row.getCell(Constants.TAX_SALE_A_TYPE_BILL_BUYER_FULLNAME_CELL_NUM);
			String buyerFullName = cell.getStringCellValue();
			ivaBillObject.setBuyerFullName(buyerFullName);

			//importe total operacion
			cell = row.getCell(Constants.TAX_SALE_A_TYPE_BILL_TOTAL_PRICE_OPERATION_CELL_NUM);
			float totalPriceOperation = (float) cell.getNumericCellValue();
			ivaBillObject.setTotalPriceOperation(decformat.format(Math.abs(totalPriceOperation)));

			ivaBillObject.setBuyerIDType("");
			ivaBillObject.setTotalPriceConcepts("");
			ivaBillObject.setUnCategorizePerceps("");
			ivaBillObject.setTotalPriceExemptOp("");
			ivaBillObject.setPriceOfNationalTaxes("");
			ivaBillObject.setPriceOfIngrBrutTax("");
			ivaBillObject.setPriceOfMunicipalTax("");
			ivaBillObject.setPriceOfInternalTaxes("");
			ivaBillObject.setCurrencyCode("");
			ivaBillObject.setTypeOfExchange("");
			ivaBillObject.setQuantityAlicIva("");
			ivaBillObject.setCodeOperation("");
			ivaBillObject.setOthersTributs("");
			ivaBillObject.setDuePayDate("");
			objectListSell.add(ivaBillObject);
			System.out.println(ivaBillObject.toString());
			return true;
		}
	}
	
	/**
	 * Este metodo procesa las facturas B. 
	 * @param row La fila leida de la planilla de calculo.
	 * @return true si la factura es valida; false en caso contrario.
	 */
	private boolean readTaxSaleBTypeBill(HSSFRow row, String billDate) {
		DecimalFormat decformat = new DecimalFormat("0.00");
		// Leo una factura.
		Cell cell = row.getCell(Constants.TAX_SALE_B_TYPE_BILL_CELL_NUM);
		// si la primer celda que indica tipo de documento esta en blanco termine de leer.
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			TaxSaleBill ivaBillObject = new TaxSaleBill();
			//fecha de comprobante
			ivaBillObject.setBillDate(billDate);
			
			//tipo de comprobante
			// TODO que va aca?
			String billType = "B";
			ivaBillObject.setBillType(billType);

			//punto de venta
			String pointOfSale = "0001";
			ivaBillObject.setPointOfSale(pointOfSale);
			
			//numero de comprobante
			cell = row.getCell(Constants.TAX_SALE_B_TYPE_BILL_NUMBER_CELL_NUM);
			ivaBillObject.setBillNumber(String.valueOf(BTypeBillGroupFrom));
			ivaBillObject.setBillEndNumber(String.valueOf(BTypeBillGroupTo));
			
			//numero de identificacion del comprador
			ivaBillObject.setBuyerIDNumber("");

			//apellido y nombre del comprador
			if (BTypeBillGroupAmount > 1000.0f) {
				ivaBillObject.setBuyerFullName("VENTA GLOBAL DIARIA");
			} else {
				ivaBillObject.setBuyerFullName("");
			}

			//importe total operacion
			ivaBillObject.setTotalPriceOperation(decformat.format(Math.abs(BTypeBillGroupAmount)));

			ivaBillObject.setBuyerIDType("99");
			ivaBillObject.setTotalPriceConcepts("");
			ivaBillObject.setUnCategorizePerceps("");
			ivaBillObject.setTotalPriceExemptOp("");
			ivaBillObject.setPriceOfNationalTaxes("");
			ivaBillObject.setPriceOfIngrBrutTax("");
			ivaBillObject.setPriceOfMunicipalTax("");
			ivaBillObject.setPriceOfInternalTaxes("");
			ivaBillObject.setCurrencyCode("");
			ivaBillObject.setTypeOfExchange("");
			ivaBillObject.setQuantityAlicIva("");
			ivaBillObject.setCodeOperation("");
			ivaBillObject.setOthersTributs("");
			ivaBillObject.setDuePayDate("0");
			objectListSell.add(ivaBillObject);
			System.out.println(ivaBillObject.toString());
			return false;
		} else {
			cell = row.getCell(Constants.TAX_SALE_B_TYPE_BILL_NUMBER_CELL_NUM);
			if (BTypeBillGroupFrom == 0) {
				BTypeBillGroupFrom = (int) cell.getNumericCellValue();
			}
			BTypeBillGroupTo = (int) cell.getNumericCellValue();
			cell = row.getCell(Constants.TAX_SALE_B_TYPE_BILL_TOTAL_PRICE_OPERATION_CELL_NUM);
			BTypeBillGroupAmount += cell.getNumericCellValue();
			return true;
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

		TaxPurchaseBill ivaBillObject = new TaxPurchaseBill();

		//fecha de comprobante
		Cell cell = row.getCell(Constants.TAX_PURCHASE_BILL_DATE_CELL_NUM);
		// si la primer celda que indica fecha esta en blanco termine de leer.
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return false;
		} else {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");//"EEE MMM dd HH:mm:ss z yyyy"
			String billDate = df.format(cell.getDateCellValue());
			ivaBillObject.setBillDate(billDate);
			DecimalFormat decformat = new DecimalFormat("0.00");

			//tipo de comprobante
			cell = row.getCell(Constants.TAX_PURCHASE_BILL_TYPE_CELL_NUM);
			String billType = cell.getStringCellValue();
			ivaBillObject.setBillType(billType);

			//punto de venta
			cell = row.getCell(Constants.TAX_PURCHASE_POINT_OF_SALE_CELL_NUM);
			String pointOfSale = cell.getStringCellValue();
			ivaBillObject.setPointOfSale(pointOfSale);

			//numero de comprobante
			cell = row.getCell(Constants.TAX_PURCHASE_BILL_NUMBER_CELL_NUM);
			String billNumber = cell.getStringCellValue();
			ivaBillObject.setBillNumber(billNumber);

			//numero de despacho de importacion
			cell = row.getCell(Constants.TAX_PURCHASE_IMPORT_NUMBER_CELL_NUM);
			ivaBillObject.setImportNumber("");

			//codigo de documento vendedor
			cell = row.getCell(Constants.TAX_PURCHASE_SELLER_ID_TYPE_CELL_NUM);
			ivaBillObject.setSellerIDType("");

			//numero de identificacion del vendedor
			cell = row.getCell(Constants.TAX_PURCHASE_SELLER_ID_NUMBER_CELL_NUM);
			String sellerIDNumber = cell.getStringCellValue();
			ivaBillObject.setSellerIDNumber(sellerIDNumber);

			//apellido y nombre del vendedor
			cell = row.getCell(Constants.TAX_PURCHASE_SELLER_FULLNAME_CELL_NUM);
			String sellerFullName = cell.getStringCellValue();
			ivaBillObject.setSellerFullName(sellerFullName);

			//importe total operacion
			cell = row.getCell(Constants.TAX_PURCHASE_TOTAL_PRICE_OPERATION_CELL_NUM);
			String totalPriceOperation = decformat.format(Math.abs(cell.getNumericCellValue()));
			ivaBillObject.setTotalPriceOperation(totalPriceOperation);

			//importe total de conc. que no integral el precio neto gravado
			cell = row.getCell(Constants.TAX_PURCHASE_TOTAL_PRICE_CONCEPTS_CELL_NUM);
			ivaBillObject.setTotalPriceConcepts("");

			//importe operaciones exentas
			cell = row.getCell(Constants.TAX_PURCHASE_TOTAL_PRICE_EXEMPT_OP_CELL_NUM);
			
			String totalPriceExemptOp = decformat.format(Math.abs(cell.getNumericCellValue()));
			ivaBillObject.setTotalPriceExemptOp(totalPriceExemptOp);

			//importe de perc. o pagos a cta del iva
			cell = row.getCell(Constants.TAX_PURCHASE_PRICE_OF_IVA_CELL_NUM);
			String priceOfIVA = decformat.format(Math.abs(cell.getNumericCellValue()));
			ivaBillObject.setPriceOfIVA(priceOfIVA);

			//importe de perc. a cta de impuestos nacionales
			cell = row.getCell(Constants.TAX_PURCHASE_PRICE_OF_NATIONAL_TAXES_CELL_NUM);
			ivaBillObject.setPriceOfNationalTaxes("");

			//importe de percepciones de ing. brutos
			cell = row.getCell(Constants.TAX_PURCHASE_PRICE_OF_INGR_BRUT_TAX_CELL_NUM);
			String priceOfIngrBrutTax = decformat.format(Math.abs(cell.getNumericCellValue()));
			ivaBillObject.setPriceOfIngrBrutTax(priceOfIngrBrutTax);

			//importe de percepciones de impuestos municipales
			cell = row.getCell(Constants.TAX_PURCHASE_PRICE_OF_MUNICIPAL_TAX_CELL_NUM);
			ivaBillObject.setPriceOfMunicipalTax("");

			// importe impuestos internos
			cell = row.getCell(Constants.TAX_PURCHASE_PRICE_OF_INTERNAL_TAXES_CELL_NUM);
			String priceOfInternalTaxes = decformat.format(Math.abs(cell.getNumericCellValue()));
			ivaBillObject.setPriceOfInternalTaxes(priceOfInternalTaxes);

			//codigo de moneda
			cell = row.getCell(Constants.TAX_PURCHASE_CURRENCY_CODE_CELL_NUM);
			ivaBillObject.setCurrencyCode("");

			//tipo de cambio
			cell = row.getCell(Constants.TAX_PURCHASE_TYPE_OF_EXCHANGE_CELL_NUM);
			ivaBillObject.setTypeOfExchange("");

			//cantidad de alicuotas de iva
			cell = row.getCell(Constants.TAX_PURCHASE_QUANTITY_ALIC_IVA_CELL_NUM);
			ivaBillObject.setQuantityAlicIva("");

			//codigo de operacion
			cell = row.getCell(Constants.TAX_PURCHASE_CODE_OPERATION_CELL_NUM);
			ivaBillObject.setCodeOperation("");

			//credito fiscal computable
			cell = row.getCell(Constants.TAX_PURCHASE_COMPUTABLE_TAX_CREDIT_CELL_NUM);
			ivaBillObject.setComputableTaxCredit("");

			//otros tributos
			cell = row.getCell(Constants.TAX_PURCHASE_OTHERS_TRIBUTS_CELL_NUM);
			ivaBillObject.setOthersTributs("");

			//cuit emisor
			cell = row.getCell(Constants.TAX_PURCHASE_CUIT_ISSUER_CELL_NUM);
			ivaBillObject.setCuitIssuer("");

			//denominacion del emisor
			cell = row.getCell(Constants.TAX_PURCHASE_DENOMINATION_ISSUER_CELL_NUM);
			ivaBillObject.setDenominationIssuer("");

			//comision iva
			cell = row.getCell(Constants.TAX_PURCHASE_IVA_COMMISSION_CELL_NUM);
			ivaBillObject.setIvaCommission("");

			objectListBuy.add(ivaBillObject);
			
			System.out.println(ivaBillObject.toString());

			return true;
		}
	}

	public List<TaxPurchaseBill> getObjectListBuy() {
		return objectListBuy;
	}

	public List<TaxSaleBill> getObjectListSell() {
		return objectListSell;
	}
	
	/*public static void main(String[] args){
		
		XLSReader read = new XLSReader();
		read.readFile();
	}*/
}
