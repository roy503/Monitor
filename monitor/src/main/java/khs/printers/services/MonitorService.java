package khs.printers.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import khs.printers.models.Printer;
import khs.printers.models.PrinterDeserializer;
import khs.printers.models.ReportFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonitorService {

	public Map<String,List<Printer>> getReport() {
		File[] fileList = fileList();
		Map<String,List<Printer>> map = new HashMap<String, List<Printer>>();
		for (File file : fileList) {
			String fileName = file.getName();
			String year = fileName.substring(0, fileName.length()-5);
			List<Printer> printerList = new ArrayList<Printer>();
			printerList = mapJSON(file);
			map.put(year, printerList);
		}
		return map;
	}
	// Get months used in report? Or use -1 for uninitialized printer data?
	
	//TODO
	public List<ReportFile> getReports(){
		File[] fileList = fileList();
		List<ReportFile> list = null;
		List<List<Printer>> printerListList = new ArrayList<List<Printer>>();
		if (fileList.length > 0) {
			for (int i = 0; i < fileList.length; i++) {
				
				for (File file : fileList) {
					List<Printer> printerList = new ArrayList<Printer>();
					printerList = mapJSON(file);
					printerListList.add(printerList);
				}
				list = new ArrayList<ReportFile>();
				//for each file
				for(int j = 0; j < fileList.length; j++) {
					ArrayList<String> months = new ArrayList<String>();
					//for each month
					for(int k = 0; k < 12; k++) {
						//get first printer of each report
						if (printerListList.get(j).get(14).getReport(k).getMonoPrints() == -1) {
							// if printer is uninitialized, do nothing					
						}
						else {
							// if printer is initialized, add month
							months.add(java.time.Month.of(k+1).toString());
						}
					}
					String fileName = fileList[j].getName();
					ReportFile reportFile = new ReportFile(fileName.substring(0,fileName.length()-5),months);
					list.add(reportFile);
				}
			}
		}
		return list;	
	}
	
	private File[] fileList() {
		// get list of files in /printers/reports
		String path = getParentDirectoryFromJar();
		File f = new File(path + "/reports");
		File[] fileList = new File[0];
		if (f.isDirectory()) {
			fileList = f.listFiles();
			if (fileList.length > 0) {
				Arrays.sort(fileList, Collections.reverseOrder());
			}
		}
		return fileList;
	}
	
	private List<Printer> mapJSON(File file) {
		List<Printer> list = new ArrayList<Printer>();
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Printer.class, new PrinterDeserializer());
		mapper.registerModule(module);
		try {
			list = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Printer.class));
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private String getParentDirectoryFromJar() {
	    String dirtyPath = getClass().getResource("").toString();
	    String jarPath = dirtyPath.replaceAll("^.*file:/", ""); //removes file:/ and everything before it
	    jarPath = jarPath.replaceAll("jar!.*", "jar"); //removes everything after .jar, if .jar exists in dirtyPath
	    jarPath = jarPath.replaceAll("%20", " "); //necessary if path has spaces within
	    if (!jarPath.endsWith(".jar")) { // this is needed if you plan to run the app using Spring Tools Suit play button. 
	        jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
	    }
	    String directoryPath = Paths.get(jarPath).getParent().toString(); //Paths - from java 8
	    return directoryPath;
	}
}
