package com.erikasf.challenge.error;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

public class ErrorLog {
	private PrintWriter out;
	
	public ErrorLog() {
		try {
			File file = new File("Log.txt");
			if(file.exists()){
				out = new PrintWriter(new FileOutputStream(file, true));
			}else {
				out = new PrintWriter(file);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeError(Throwable error) {
		out.append("Date: " + new Date(System.currentTimeMillis()) + "  -  Message: " + error + " \n");
	}
	
	public void close() {
		if (out != null) {
            out.flush();
			out.close();
		}
	}
	
	public void allProcess(ErrorLog eLog, Throwable e) {
		
		eLog = new ErrorLog();
		eLog.writeError(e);
		eLog.close();
	}
}
