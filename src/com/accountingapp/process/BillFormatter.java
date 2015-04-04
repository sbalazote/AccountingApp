package com.accountingapp.process;

import java.util.List;

import com.accountingapp.obj.IvaBillObject;

public class BillFormatter {

	private String getDateFormatted(String date){
		
		String[] splitteddate = date.split("/");
		String dateformatted = splitteddate[2] + splitteddate[1] + splitteddate[0];
		
		return dateformatted;
	}

	
	public List<IvaBillObject> createIvaBillObject(){
	
		return null;
		
	}
}
