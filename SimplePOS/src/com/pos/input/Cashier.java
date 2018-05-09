package com.pos.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Cashier {
	double discrepancy;
	
	public Cashier(SystemInput systemInput, double discrepancy) {
		// TODO Auto-generated constructor stub
		this.discrepancy = discrepancy;

		boolean existingFile = false;
		String pattern = "MM-dd-yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		System.out.println(dateInString);

		String fileName = systemInput.getUserName()+"_" + dateInString + ".txt";
		File folder = new File("Cashier/");
		File[] listOfFiles = folder.listFiles();
		File fileToAdd=null;
		FileWriter fileWriter;
		for (File file : listOfFiles) {
				if (file.getName().equalsIgnoreCase(fileName)) {
					fileToAdd = file;
					existingFile = true;
					break;
				}
		}
		try {
		if(existingFile){
			
				fileWriter = new FileWriter(fileToAdd, true);
			
		}else{
			fileToAdd = new File("./Cashier/" + fileName );
			fileWriter = new FileWriter(fileToAdd);
		}
		BufferedWriter output = new BufferedWriter(fileWriter);
		output.write(systemInput.getUserName().toLowerCase()+" "+systemInput.getLogOnTime()+" "+systemInput.getLogOffTime()+" "+systemInput.getRegisterNumber()+" "+discrepancy);
		output.newLine();
		output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
