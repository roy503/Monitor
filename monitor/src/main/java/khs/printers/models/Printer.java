package khs.printers.models;

import java.time.Month;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Printer {
	
	private static int total = 0;
	private String address, name, serial, location;
	private int K1, K2, cyan, magenta, yellow, black, monoPrints, monoCopies, colourPrints, colourCopies;
	@JsonProperty(value = "Reports")
	private PrinterReports[] reports = new PrinterReports[12];
	
	public Printer() {
		address = "";
		name = "";
		serial = "";
		location = "";
		K1 = 0;
		K2 = 0;
		cyan = 0;
		magenta = 0;
		yellow = 0;
		black = 0;
		monoPrints = 0;
		monoCopies = 0;
		colourPrints = 0;
		colourCopies = 0;
		total++;
		for (int i = 0; i < 12; i++) {
			Month month = Month.of(i+1);
			reports[i] = new PrinterReports(month.toString());
		}
	}
	
	public PrinterReports getReport(int month) {
		return reports[month];
	}
	public void setReport(int month, PrinterReports report) {
		reports[month] = report;
	}
	public void setReports(int month, String monthName, int monoPrints, int monoCopies, int colourPrints, int colourCopies) {
		reports[month] = new PrinterReports(monthName,monoPrints,monoCopies,colourPrints,colourCopies);
	}

	public int getMonoPrints() {
		return monoPrints;
	}

	public void setMonoPrints(int blackPrints) {
		this.monoPrints = blackPrints;
	}

	public int getMonoCopies() {
		return monoCopies;
	}

	public void setMonoCopies(int blackCopies) {
		this.monoCopies = blackCopies;
	}

	public int getColourPrints() {
		return colourPrints;
	}

	public void setColourPrints(int colourPrints) {
		this.colourPrints = colourPrints;
	}

	public int getColourCopies() {
		return colourCopies;
	}

	public void setColourCopies(int colourCopies) {
		this.colourCopies = colourCopies;
	}

	public static int getTotal() {
		return total;
	}

	public static void setTotal(int total) {
		Printer.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getK1() {
		return K1;
	}

	public void setK1(int k1) {
		K1 = k1;
	}

	public int getK2() {
		return K2;
	}

	public void setK2(int k2) {
		K2 = k2;
	}

	public int getCyan() {
		return cyan;
	}

	public void setCyan(int cyan) {
		this.cyan = cyan;
	}

	public int getMagenta() {
		return magenta;
	}

	public void setMagenta(int magenta) {
		this.magenta = magenta;
	}

	public int getYellow() {
		return yellow;
	}

	public void setYellow(int yellow) {
		this.yellow = yellow;
	}

	public int getBlack() {
		return black;
	}

	public void setBlack(int black) {
		this.black = black;
	}

}

