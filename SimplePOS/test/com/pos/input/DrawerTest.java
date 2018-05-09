package com.pos.input;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class DrawerTest extends TestCase {

	public void testAddFile() {
		boolean existingFile = false;
		String pattern = "MM-dd-yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		System.out.println(dateInString);

		String fileName = "Prerana_" + dateInString + ".txt";
		File folder = new File("Drawer/");
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
			fileToAdd = new File("./Drawer/" + fileName );
			fileWriter = new FileWriter(fileToAdd);
		}
		BufferedWriter output = new BufferedWriter(fileWriter);
		output.write("05 66");
		output.newLine();
		output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testAddTotal(){
		Double sum =0.0;
		int count = 0;
		boolean existingFile = false;
		String pattern = "MM-dd-yyyy";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		System.out.println(dateInString);

		String fileName = "Prerana_" + dateInString + ".txt";
		File folder = new File("Drawer/");
		File[] listOfFiles = folder.listFiles();
		File fileToRead=null;
		for (File file : listOfFiles) {
				if (file.getName().equalsIgnoreCase(fileName)) {
					fileToRead = file;
					existingFile = true;
					break;
				}
		}
		try {
		String line;
        FileReader fileReader = new FileReader(fileToRead);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            String value = line.split(" ")[1];
            sum = sum + Double.valueOf(value);
            count = count +1;
        }

        System.out.println(count +" "+ sum);
        bufferedReader.close();} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
