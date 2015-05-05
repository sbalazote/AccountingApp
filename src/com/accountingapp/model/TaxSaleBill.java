package com.accountingapp.model;

public class TaxSaleBill {

	private String billDate; //fecha de comprobante
	private String billType; //tipo de comprobante
	private String pointOfSale; //punto de venta
	private String billNumber; //numero de comprobante
	private String billEndNumber; //numero de comprobante hasta
	private String sellerIDType; //codigo de documento vendedor
	private String sellerIDNumber; //numero de identificacion del vendedor
	private String sellerFullName; //apellido y nombre del vendedor
	private String totalPriceOperation; //importe total operacion
	private String totalPriceConcepts; //importe total de conc. que no integral el precio neto gravado
	private String unCategorizePerceps; //percepciones no categorizadas
	private String totalPriceExemptOp; //importe operaciones exentas
	private String priceOfNationalTaxes; //importe de perc. a cta de impuestos nacionales
	private String priceOfIngrBrutTax; //importe de percepciones de ing. brutos
	private String priceOfMunicipalTax; //importe de percepciones de impuestos municipales
	private String priceOfInternalTaxes; // importe impuestos internos
	private String currencyCode; //codigo de moneda
	private String typeOfExchange; //tipo de cambio
	private String quantityAlicIva; //cantidad de alicuotas de iva
	private String codeOperation; //codigo de operacion
	private String othersTributs; //otros tributos
	private String duePayDate; //fecha de vencimiento de pago
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getPointOfSale() {
		return pointOfSale;
	}
	public void setPointOfSale(String pointOfSale) {
		this.pointOfSale = pointOfSale;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getBillEndNumber() {
		return billEndNumber;
	}
	public void setBillEndNumber(String billEndNumber) {
		this.billEndNumber = billEndNumber;
	}
	public String getSellerIDType() {
		return sellerIDType;
	}
	public void setSellerIDType(String sellerIDType) {
		this.sellerIDType = sellerIDType;
	}
	public String getSellerIDNumber() {
		return sellerIDNumber;
	}
	public void setSellerIDNumber(String sellerIDNumber) {
		this.sellerIDNumber = sellerIDNumber;
	}
	public String getSellerFullName() {
		return sellerFullName;
	}
	public void setSellerFullName(String sellerFullName) {
		this.sellerFullName = sellerFullName;
	}
	public String getTotalPriceOperation() {
		return totalPriceOperation;
	}
	public void setTotalPriceOperation(String totalPriceOperation) {
		this.totalPriceOperation = totalPriceOperation;
	}
	public String getTotalPriceConcepts() {
		return totalPriceConcepts;
	}
	public void setTotalPriceConcepts(String totalPriceConcepts) {
		this.totalPriceConcepts = totalPriceConcepts;
	}
	public String getUnCategorizePerceps() {
		return unCategorizePerceps;
	}
	public void setUnCategorizePerceps(String unCategorizePerceps) {
		this.unCategorizePerceps = unCategorizePerceps;
	}
	public String getTotalPriceExemptOp() {
		return totalPriceExemptOp;
	}
	public void setTotalPriceExemptOp(String totalPriceExemptOp) {
		this.totalPriceExemptOp = totalPriceExemptOp;
	}
	public String getPriceOfNationalTaxes() {
		return priceOfNationalTaxes;
	}
	public void setPriceOfNationalTaxes(String priceOfNationalTaxes) {
		this.priceOfNationalTaxes = priceOfNationalTaxes;
	}
	public String getPriceOfIngrBrutTax() {
		return priceOfIngrBrutTax;
	}
	public void setPriceOfIngrBrutTax(String priceOfIngrBrutTax) {
		this.priceOfIngrBrutTax = priceOfIngrBrutTax;
	}
	public String getPriceOfMunicipalTax() {
		return priceOfMunicipalTax;
	}
	public void setPriceOfMunicipalTax(String priceOfMunicipalTax) {
		this.priceOfMunicipalTax = priceOfMunicipalTax;
	}
	public String getPriceOfInternalTaxes() {
		return priceOfInternalTaxes;
	}
	public void setPriceOfInternalTaxes(String priceOfInternalTaxes) {
		this.priceOfInternalTaxes = priceOfInternalTaxes;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getTypeOfExchange() {
		return typeOfExchange;
	}
	public void setTypeOfExchange(String typeOfExchange) {
		this.typeOfExchange = typeOfExchange;
	}
	public String getQuantityAlicIva() {
		return quantityAlicIva;
	}
	public void setQuantityAlicIva(String quantityAlicIva) {
		this.quantityAlicIva = quantityAlicIva;
	}
	public String getCodeOperation() {
		return codeOperation;
	}
	public void setCodeOperation(String codeOperation) {
		this.codeOperation = codeOperation;
	}
	public String getOthersTributs() {
		return othersTributs;
	}
	public void setOthersTributs(String othersTributs) {
		this.othersTributs = othersTributs;
	}
	public String getDuePayDate() {
		return duePayDate;
	}
	public void setDuePayDate(String duePayDate) {
		this.duePayDate = duePayDate;
	}
	@Override
	public String toString() {
		return "TaxSaleBill [billDate=" + billDate + ", billType=" + billType + ", pointOfSale="
				+ pointOfSale + ", billNumber=" + billNumber + ", billEndNumber=" + billEndNumber + ", sellerIDType="
				+ sellerIDType + ", sellerIDNumber=" + sellerIDNumber + ", sellerFullName=" + sellerFullName
				+ ", totalPriceOperation=" + totalPriceOperation + ", totalPriceConcepts=" + totalPriceConcepts
				+ ", unCategorizePerceps=" + unCategorizePerceps + ", totalPriceExemptOp=" + totalPriceExemptOp
				+ ", priceOfNationalTaxes=" + priceOfNationalTaxes + ", priceOfIngrBrutTax=" + priceOfIngrBrutTax
				+ ", priceOfMunicipalTax=" + priceOfMunicipalTax + ", priceOfInternalTaxes=" + priceOfInternalTaxes
				+ ", currencyCode=" + currencyCode + ", typeOfExchange=" + typeOfExchange + ", quantityAlicIva="
				+ quantityAlicIva + ", codeOperation=" + codeOperation + ", othersTributs=" + othersTributs
				+ ", duePayDate=" + duePayDate + "]";
	}
	

}
