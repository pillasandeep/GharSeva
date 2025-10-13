package com.example.Webapp.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import com.example.Webapp.Util.DownloadUtil;
import com.example.Webapp.Entity.AdminEntity;
import com.example.Webapp.Entity.BookingEntity;
import com.example.Webapp.Entity.TechBookingEntity;
import com.example.Webapp.Entity.TechnicianEntity;
import com.example.Webapp.Repository.BookingRepository;
import com.example.Webapp.Repository.TechBookingRepository;
import com.example.Webapp.Repository.TechnicianRepository;
import com.example.Webapp.Service.AdminService;
import com.example.Webapp.Util.DownloadUtil;
//import com.example.Webapp.Util.DownloadUtil;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
@Controller
public class AdminDashBoardController {
//	@Autowired
	AdminService adminservice;
	TechBookingRepository techbookrepo;
	TechnicianRepository techrepo;
	BookingRepository bookrepo;
//	@Autowired
//	TechBookingEntity tech;
	AdminDashBoardController(AdminService admin,
							TechBookingRepository tech,
							TechnicianRepository techrepo,
							BookingRepository bookrepo
							)
	{
		this.adminservice=admin;
		this.techbookrepo=tech;
		this.techrepo=techrepo;
		this.bookrepo=bookrepo;
	}
	List<BookingEntity> pending;
	List<Object[]> assigned;
	List<Object[]> completed;
	@GetMapping("/admindashboard")
	public String  LoadDashBoard(Model model,HttpSession session)
	{
		if(session.getAttribute("admin")!=null)
		{
			pending=adminservice.todayBookingsDuplicate(LocalDate.now(),"Pending");
			model.addAttribute("todaysBookings", pending);
			assigned=adminservice.todayBookings(LocalDate.now(),"Assigned");
			model.addAttribute("assignedBookings", assigned);
			completed=adminservice.todayBookings(LocalDate.now(),"Completed");
			for(Object i[]:completed)
			{
				System.out.println(i[0]+" "+i[1]+" "+i[4]);
			}
			model.addAttribute("completedBookings", completed);
			return "admindashboard";
		}
		else
			return "redirect:/adminlogin";
		
		//return "redirect:/admindashboard";
	}
	@GetMapping("/adminlogin")
	public String LoadAdmin(Model model)
	{
		model.addAttribute("admin",new AdminEntity());
		//model.addAttribute("customer",new CustomerEntity());
		//model.addAttribute("error","");
		model.addAttribute("error1","");

		return "adminlogin";
	}
	@PostMapping("/adminlogin")
	public String LoginAdmin(@ModelAttribute AdminEntity admin,Model model,HttpSession session)
	{
		boolean c=adminservice.ValidateAdmin(admin.getPhonenumber(),admin.getPassword());
		if(c)
		{
			session.setAttribute("admin",admin.getPhonenumber());
			model.addAttribute("message", "Login successful.");
			// model.addAttribute("admin", new AdminEntity());       // reset form if needed
		       // model.addAttribute("customer", new CustomerEntity());
		        return "redirect:/admindashboard";
		}
		else
		{
			model.addAttribute("error1","Invalid Password or Phonenumber");
			model.addAttribute("admin",new AdminEntity());
			//model.addAttribute("customer",new CustomerEntity());

			return "adminlogin";
		}
	}
	@GetMapping("/logout1")
	public String logOut(HttpSession session)
	{
		System.out.println(session.getAttribute("admin"));
		session.invalidate();
		return "redirect:/adminlogin";
	}
	@PostMapping("/changeState")
	public String changeState(@RequestParam long id,
							 @RequestParam long techid)
	{

		System.out.println(techid+" "+id);
		  TechnicianEntity technician = techrepo.findById(techid)
		            .orElseThrow(() -> new RuntimeException("Technician not found"));

		        BookingEntity booking = bookrepo.findById(id);
		        		if (booking == null) {
		        		    throw new RuntimeException("Booking not found");
		        		}
		 TechBookingEntity techBooking = new TechBookingEntity();
	        techBooking.setTechnician(technician);
	        techBooking.setBooking(booking);
	        techBooking.setStatus("Assigned");
	        techBooking.setDate(LocalDate.now());
	        techbookrepo.save(techBooking);
		//techrepo.save();
		adminservice.moveToNextState(id);
		return "redirect:/admindashboard";
	}
   
	
	@GetMapping("/download")
	public void downloadToday(HttpServletResponse response) throws Exception {
		 List<BookingEntity> filter = adminservice.todayBookingsOrderByStatus(LocalDate.now());

	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=today_bookings.pdf");

	    String[] headers = {
	        "Serial No", "Customer Name", "Service", "Phone", 
	        "Address", "Landmark", "Time", "Price", "Status"
	    };

	    DownloadUtil.exportTable(
	        response.getOutputStream(),
	        "Today Bookings Report Pending " + LocalDate.now(),
	        headers,
	        filter
	    );
	}


//	public void download(HttpServletResponse response)throws IOException
//	{
//		response.setContentType("application/pdf");
//		response.setHeader("Content-Disposition", "attachment; filename=today_bookings.pdf");
//		Document document=new Document(PageSize.A4);
//		 PdfWriter.getInstance(document, response.getOutputStream());
//		document.open();
//		
//
//	    // Title
//	    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
//	    
//	    Paragraph title = new Paragraph("Today Bookings Report  "+LocalDate.now(), titleFont);
//	    title.setAlignment(Paragraph.ALIGN_CENTER);
//	    document.add(title);
//	    document.add(new Paragraph(" ")); // blank line
//
//	    // Create Table
//	    PdfPTable table = new PdfPTable(9); // 6 columns
////	    float wid[]= {2f,3f,4f};
////	    table.setWidths(wid);
////	    table.setWidthPercentage(100);
//	    table.setWidthPercentage(100);
//	    table.setSpacingBefore(10f);
//	    table.setSpacingAfter(10f);
//
//	    // Table headers
//	    String[] headers = {"Serial No", "Customer Name", "Service", "Phone", "Address", "Landmark" , "Time" , "Price" , "Status"};
//	    for (String header : headers) {
//	        PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
//	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        cell.setBackgroundColor(Color.LIGHT_GRAY);
//	        
//	       // cell.setMinimumHeight(30f);
//	        table.addCell(cell);
//	    }
//	    int x=1;
//	    
//	    for(BookingEntity pending:pending)
//	    {
//	    	table.addCell(String.valueOf(x++));
//	    	table.addCell(pending.getName());
//	    	table.addCell(pending.getServicename());
//	    	table.addCell(pending.getPhonenumber());
//	    	table.addCell(pending.getAddress());
//	    	table.addCell(pending.getLandmark());
//	    	table.addCell(String.valueOf(pending.getTime()));
//	    	table.addCell(String.valueOf(pending.getPrice()));
//	    	table.addCell(pending.getState());
//	    	
//	    	
//	    }
//	    for(BookingEntity pending:assigned)
//	    {
//	    	table.addCell(String.valueOf(x++));
//	    	table.addCell(pending.getName());
//	    	table.addCell(pending.getServicename());
//	    	table.addCell(pending.getPhonenumber());
//	    	table.addCell(pending.getAddress());
//	    	table.addCell(pending.getLandmark());
//	    	table.addCell(String.valueOf(pending.getTime()));
//	    	table.addCell(String.valueOf(pending.getPrice()));
//	    	table.addCell(pending.getState());
//	    	
//	    	
//	    }
//	    for(BookingEntity pending:completed)
//	    {
//	    	table.addCell(String.valueOf(x++));
//	    	table.addCell(pending.getName());
//	    	table.addCell(pending.getServicename());
//	    	table.addCell(pending.getPhonenumber());
//	    	table.addCell(pending.getAddress());
//	    	table.addCell(pending.getLandmark());
//	    	table.addCell(String.valueOf(pending.getTime()));
//	    	table.addCell(String.valueOf(pending.getPrice()));
//	    	table.addCell(pending.getState());
//	    	
//	    	
//	    }
//	    document.add(table);
//	    document.close();
//
//		
//	}
}
