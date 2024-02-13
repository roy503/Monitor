package khs.printers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import khs.printers.models.Printer;
import khs.printers.models.ReportFile;
import khs.printers.services.MonitorService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://10.214.200.39:8081")
public class MonitorController {
	
	@Autowired
	private MonitorService monitorService;
	
    @GetMapping("/GetReport")
    public ResponseEntity<Map<String,List<Printer>>>  tonerLevels() {
        return new ResponseEntity<Map<String, List<Printer>>>(monitorService.getReport(), HttpStatus.OK);
    }
    
    @GetMapping("/Nav")
    public ResponseEntity<List<ReportFile>> reportDates() {
    	return new ResponseEntity<List<ReportFile>>(monitorService.getReports(), HttpStatus.OK);
    }  
}
