package com.accountingapp.utils;

public final class Constants  {
 
 /** FILA INICIAL */
 public static final int IVA_BILL_INITIAL_ROW_NUM = 5;
	
 /** fecha de comprobante */
 public static final int BILL_DATE_CELL_NUM = 3;
 
 /** tipo de comprobante */
 public static final int BILL_TYPE_CELL_NUM = 4;
 
 /** punto de venta */
 public static final int POINT_OF_SALE_CELL_NUM = 5;
 
 /** numero de comprobante */
 public static final int BILL_NUMBER_CELL_NUM = 6;

 /** numero de despacho de importacion */
 public static final int IMPORT_NUMBER_CELL_NUM = -1;
 
 /** codigo de documento vendedor */
 public static final int SELLER_ID_TYPE_CELL_NUM = -1;
 
 /** numero de identificacion del vendedor */
 public static final int SELLER_ID_NUMBER_CELL_NUM = 8;
 
 /** apellido y nombre del vendedor */
 public static final int SELLER_FULLNAME_CELL_NUM = 7;
 
 /** importe total operacion */
 public static final int TOTAL_PRICE_OPERATION_CELL_NUM = 15;
 
 /** importe total de conc. que no integral el precio neto gravado */
 public static final int TOTAL_PRICE_CONCEPTS_CELL_NUM = -1;
 
 /** importe operaciones exentas */
 public static final int TOTAL_PRICE_EXEMPT_OP_CELL_NUM = 14;
 
 /** importe de perc. o pagos a cta del iva */
 public static final int PRICE_OF_IVA_CELL_NUM = 13;
 
 /** importe de perc. a cta de impuestos nacionales */
 public static final int PRICE_OF_NATIONAL_TAXES_CELL_NUM = -1;
 
 /** importe de percepciones de ing. brutos */
 public static final int PRICE_OF_INGR_BRUT_TAX_CELL_NUM = 12;
 
 /** importe de percepciones de impuestos municipales */
 public static final int PRICE_OF_MUNICIPAL_TAX_CELL_NUM = -1;
 
 /** importe impuestos internos */
 public static final int PRICE_OF_INTERNAL_TAXES_CELL_NUM = 10;
 
 /** codigo de moneda */
 public static final int CURRENCY_CODE_CELL_NUM = -1;
 
 /** tipo de cambio */
 public static final int TYPE_OF_EXCHANGE_CELL_NUM = -1;
 
 /** cantidad de alicuotas de iva */
 public static final int QUANTITY_ALIC_IVA_CELL_NUM = -1;
 
 /** codigo de operacion */
 public static final int CODE_OPERATION_CELL_NUM = -1;
 
 /** credito fiscal computable */
 public static final int COMPUTABLE_TAX_CREDIT_CELL_NUM = -1;
 
 /** otros tributos */
 public static final int OTHERS_TRIBUTS_CELL_NUM = -1;
 
 /** cuit emisor */
 public static final int CUIT_ISSUER_CELL_NUM = -1;
 
 /** denominacion del emisor */
 public static final int DENOMINATION_ISSUER_CELL_NUM = -1;
 
 /** comision iva */
 public static final int IVA_COMMISSION_CELL_NUM = -1;

 // PRIVATE //

 /**
  The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
  and so on. Thus, the caller should be prevented from constructing objects of 
  this class, by declaring this private constructor. 
 */
 private Constants() {
	 throw new AssertionError();
 }
}