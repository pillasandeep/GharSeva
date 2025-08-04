package com.example.Webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Webapp.ServicesList.AllServicesTemplate;

import java.util.*;
@Controller
public class WmServiceController {

	    @GetMapping("/wm_services")
	    public String showWashingMachineServices(Model model) {
	        List<AllServicesTemplate> wmservices = new ArrayList<>();

	        wmservices.add(new AllServicesTemplate("Washing Machine Installation",999,"images/wm/wminstall.webp"));
	        wmservices.add(new AllServicesTemplate("Washing Machine All Sensor",2499,"images/wm/allsensor.webp"));
	        wmservices.add(new AllServicesTemplate("Washing Machine Washing Machine Mother Board",2499,"images/wm/wmmother.webp"));
	        wmservices.add(new AllServicesTemplate("Washing Machine Scaling Powder",999,"images/wm/wminstall.webp"));
	        wmservices.add(new AllServicesTemplate("Washing Machine Pressure Sensor",999,"images/wm/wmsensor.webp"));
	        wmservices.add(new AllServicesTemplate("Washing Machine Ac",999,"images/wm/ac.jpg"));
	        wmservices.add(new AllServicesTemplate("Washing Machine DC",799,"images/wm/dc.jpg"));
	        wmservices.add(new AllServicesTemplate("Washing Machine Inllet Wall",1499,"images/wm/inlet.webp"));
	        model.addAttribute("wmservices", wmservices);
	        return "wm_services";  // Thymeleaf template
	    }
	}
