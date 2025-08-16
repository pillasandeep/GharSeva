package com.example.Webapp.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Webapp.Service.AdminService;

import java.util.*;
@Controller
public class AdminReportController {

	@Autowired
	AdminService bookingReportService;
	@GetMapping("/reports")
	public String reportsPageLoad(Model model)
	{
		return "reports";
	}
		@PostMapping("/reports")
	public String loadReport(@RequestParam(required=false) String type,
							 @RequestParam(required=false)String month,
							 @RequestParam(required=false) String year,Model model)
	{
		
		
		List<Object[]> report=null;
		 if ("monthly".equalsIgnoreCase(type) && month != null && year != null) {
		        report = bookingReportService.getMonthlyCatReport(Integer.parseInt(month),Integer.parseInt(year));
		        model.addAttribute("reportTitle", "Monthly Report for " + month + "/" + year);
		    }
		 if("monthly".equalsIgnoreCase(type))
			{
			 double grandTotal = 0;
			 int servicecount=0;
			 if (report != null) {
			     for (Object[] row : report) {
			         if (row[2] != null && row[1]!=null) {
			             grandTotal += ((Number) row[2]).doubleValue();
			             servicecount += ((Number)row[1]).intValue();
			         }
			     }
			 }
			 System.out.println(grandTotal);
			    model.addAttribute("report", report);
			    model.addAttribute("selectedType", type);
			    model.addAttribute("month", month);
			    model.addAttribute("year", year);
			    model.addAttribute("grandtotal",grandTotal);
			    model.addAttribute("servicecount",servicecount);
			return "reports";
	}
		 else
		 {
			 //List<Object[]> report=null;
			 if ("annual".equalsIgnoreCase(type) && year != null) {
			        report = bookingReportService.getYearlyCatReport(Integer.parseInt(year));
			        model.addAttribute("reportTitle", "Annual Report for "+ year);
			    }
			 double grandTotal = 0;
			 int servicecount=0;
			 if (report != null) {
			     for (Object[] row : report) {
			         if (row[2] != null && row[1]!=null) {
			             grandTotal += ((Number) row[2]).doubleValue();
			             servicecount += ((Number)row[1]).intValue();
			         }
			     }
			 }
			 System.out.println(grandTotal);
			    model.addAttribute("report", report);
			    model.addAttribute("selectedType", type);
			   // model.addAttribute("month", month);
			    model.addAttribute("year", year);
			    model.addAttribute("grandtotal",grandTotal);
			    model.addAttribute("servicecount",servicecount);
			return "reports"; 
		 }
	}
//	@GetMapping("/reports")
//	public String LoadYear(Model model)
//	{
//		return "reports1";
//	}
//	@PostMapping("/reports")
//	public String loadYearlyReport(@RequestParam(required=false) String type,
//									@RequestParam(required=false) String year,Model model)
//	{
//		List<Object[]> report=null;
//		 if ("annual".equalsIgnoreCase(type) && year != null) {
//		        report = bookingReportService.getYearlyCatReport(Integer.parseInt(year));
//		        model.addAttribute("reportTitle", "Annual Report for "+ year);
//		    }
//		 double grandTotal = 0;
//		 int servicecount=0;
//		 if (report != null) {
//		     for (Object[] row : report) {
//		         if (row[2] != null && row[1]!=null) {
//		             grandTotal += ((Number) row[2]).doubleValue();
//		             servicecount += ((Number)row[1]).intValue();
//		         }
//		     }
//		 }
//		 System.out.println(grandTotal);
//		    model.addAttribute("report", report);
//		    model.addAttribute("selectedType", type);
//		   // model.addAttribute("month", month);
//		    model.addAttribute("year", year);
//		    model.addAttribute("grandtotal",grandTotal);
//		    model.addAttribute("servicecount",servicecount);
//		return "reports";
//	}
//	
	
	
	
	
	
	
}
