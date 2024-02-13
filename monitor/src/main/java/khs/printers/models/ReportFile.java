package khs.printers.models;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportFile {
	
	private String year;
	@JsonProperty(value = "Months")
	ArrayList<String> months;
	
	public ReportFile(String year, ArrayList<String> months) {
		this.year = year;
		this.months = months;
	}
	
	public ArrayList<String> getMonths() {
		return months;
	}

	public void addMonth(ArrayList<String> months) {
		this.months = months;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	

}
