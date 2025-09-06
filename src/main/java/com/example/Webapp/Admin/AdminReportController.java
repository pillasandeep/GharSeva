package com.example.Webapp.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Service.AdminService;
import com.example.Webapp.Util.DownloadUtil;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.*;
@Controller
public class AdminReportController {

	@Autowired
	AdminService bookingReportService;
	List<Object[]> report1=new ArrayList<>();
	String monthduplicate="";
	String yearduplicate="";
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
		        monthduplicate=month;
		        yearduplicate=year;
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
			    report1.addAll(report);
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
			    report1.addAll(report);
			return "reports"; 
		 }
	}
		

		@GetMapping("/reportdownload")
		public void downloadToday(HttpServletResponse response) throws Exception {
			// List<BookingEntity> filter = bookingReportService.(LocalDate.now());

		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition", "attachment; filename=today_bookings.pdf");

		    String[] headers = {
		     "Serial No","Service Name" ," Number of Services Booked" , " Total Amount (₹) "
		    };
		    if(monthduplicate!=null)
		    {
		    DownloadUtil.exportTable(
		        response.getOutputStream(),
		        "Monthly Report" + monthduplicate+"/"+yearduplicate,
		        headers,
		        report1
		    );
		    }
		    else
		    {
		    	DownloadUtil.exportTable(
				        response.getOutputStream(),
				       "Annual Booking Reports  "+" "+ yearduplicate ,
				        headers,
				        report1
				    );
		    }
		    report1=null;
		    	
		}
	
	
	
	
}
