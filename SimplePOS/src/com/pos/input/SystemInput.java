package com.pos.input;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInput {

	private String userName;
	private String registerNumber;
	private String logOnDate;
	private String logOffDate;
	private String logOnTime;
	private String logOffTime;
	private double totalSalesAmount;
	
	String datePattern = "MM-dd-yyyy";
	String timePattern = "HH:mm:ss";
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the registerNumber
	 */
	public String getRegisterNumber() {
		return registerNumber;
	}
	/**
	 * @param registerNumber the registerNumber to set
	 */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	/**
	 * @return the logOnDate
	 */
	public String getLogOnDate() {
		return logOnDate;
	}
	/**
	 * @param logOnDate the logOnDate to set
	 */
	public void setLogOnDate() {
		this.logOnDate = new SimpleDateFormat(datePattern).format(new Date());;
	}
	/**
	 * @return the logOffDate
	 */
	public String getLogOffDate() {
		this.logOffDate = new SimpleDateFormat(datePattern).format(new Date());;
		return logOffDate;
	}
	/**
	 * @return the logOnTime
	 */
	public String getLogOnTime() {
		return logOnTime;
	}
	/**
	 * @param logOnTime the logOnTime to set
	 */
	public void setLogOnTime() {
		this.logOnTime = new SimpleDateFormat(timePattern).format(new Date());;
	}
	/**
	 * @return the logOffTime
	 */
	public String getLogOffTime() {
		this.logOffTime = new SimpleDateFormat(timePattern).format(new Date());;
		return logOffTime;
	}
	/**
	 * @return the totalSalesAmount
	 */
	public double getTotalSalesAmount() {
		return totalSalesAmount;
	}
	/**
	 * @param totalSalesAmount the totalSalesAmount to set
	 */
	public void setTotalSalesAmount(double totalSalesAmount) {
		this.totalSalesAmount = this.totalSalesAmount + totalSalesAmount;
	}
	
	
	
	
	
}
