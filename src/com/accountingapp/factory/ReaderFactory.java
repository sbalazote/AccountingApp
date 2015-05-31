package com.accountingapp.factory;

public class ReaderFactory extends AbstractFactoryReader {

	@Override
	public Reader getReader(String readerType) {
		
		if(readerType == null){
			return null;
		}
		if(readerType.equalsIgnoreCase("COMPRASGESTION")){
			return new ComprasGestionReader();
		}else if(readerType.equalsIgnoreCase("VENTASGESTION")){
			return new VentasGestionReader();
		}else if(readerType.equalsIgnoreCase("COMPRASADM")){
			return new ComprasAdminReader();
		}else if(readerType.equalsIgnoreCase("VENTASADM")){
			return new VentasAdminReader();
		}
		return null;
	}
}
