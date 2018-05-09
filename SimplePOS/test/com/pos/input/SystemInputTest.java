package com.pos.input;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInputTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String format = "HH:mm:ss";
		String time = new SimpleDateFormat(format).format(new Date());
		System.out.println(time);
	}

}
