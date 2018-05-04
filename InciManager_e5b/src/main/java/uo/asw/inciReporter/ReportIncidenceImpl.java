package uo.asw.inciReporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Incidence;

@Component
public class ReportIncidenceImpl implements ReportIncidence {
	
	private static final String fileName = "reports.log";
	
	@Override
	public void reportIncidence(Incidence incidence) {		
		PrintWriter writer = null;
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = formatter.format(new Date());
			
			writer = new PrintWriter(new FileWriter(fileName, true));
			writer.println(now + " [ERROR] Error sending incidence with identifier: " + incidence.getIdentifier()
					+ ". The agent data isn't valid.");
		    
		} catch (IOException e) {
		   System.err.println("Writter error");
		} finally {
			if(writer != null)
				writer.close();
		}
		
	}
}
