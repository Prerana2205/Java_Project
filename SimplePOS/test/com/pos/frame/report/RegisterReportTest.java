package com.pos.frame.report;

import javax.swing.table.DefaultTableModel;

import com.pos.frame.MainPageFrame;
import com.pos.input.SystemInput;

import junit.framework.TestCase;

public class RegisterReportTest extends TestCase{

	
	public void testRegisterOutput() {
			
				MainPageFrame mainFrame = new MainPageFrame("Prerana");
				SystemInput systemInput = new SystemInput();
				systemInput.setRegisterNumber("1");
				systemInput.setUserName("Prerana");
				systemInput.setLogOnDate();
				systemInput.setLogOnTime();
				systemInput.setTotalSalesAmount(1000.40);
				
				
				mainFrame.addDataToRegister(systemInput);
				
				systemInput.setRegisterNumber("1");
				systemInput.setUserName("Prerana");
				systemInput.setTotalSalesAmount(550.40);
				mainFrame.addDataToRegister(systemInput);
				System.out.println("ended");
				
		
	}
	
public void testRegisterReport(){
		
		RegisterReport inventory = new RegisterReport();
		inventory.addReportToTable(new DefaultTableModel());
	}
	
}
