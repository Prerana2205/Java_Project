package com.pos.frame.report;

import javax.swing.table.DefaultTableModel;

import junit.framework.TestCase;

public class InventoryReportTest extends TestCase{
	
	public void testInventoryReport(){
		
		InventoryReport inventory = new InventoryReport();
		inventory.addReportToTable(new DefaultTableModel());
	}

}
