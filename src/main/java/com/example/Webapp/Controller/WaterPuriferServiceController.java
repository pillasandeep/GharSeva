package com.example.Webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Webapp.ServicesList.AllServicesTemplate;

import java.util.*;
@Controller
public class WaterPuriferServiceController {

	
	@GetMapping("/waterpure")
	public String LoadService(Model model)
	{
	List<AllServicesTemplate>waterpurifier=new ArrayList<>();
	waterpurifier.add(new AllServicesTemplate("Water Purifier Check Up",299,"/images/wp/checkfilters.webp"));
	waterpurifier.add(new AllServicesTemplate("Water Purifier Filters Check up",299,"/images/wp/filters.webp"));
	waterpurifier.add(new AllServicesTemplate("Water Purifier Regular Serviec",1799,"/images/wp/regularservice.webp"));
	waterpurifier.add(new AllServicesTemplate("Water Purifier Full Service",3799,"/images/wp/fullservice.png"));
	waterpurifier.add(new AllServicesTemplate("Water Purifier Installation",499,"/images/wp/install.webp"));
	waterpurifier.add(new AllServicesTemplate("Water Purifier Uninsatallation",399,"/images/wp/uninstall.png"));
	model.addAttribute("services",waterpurifier);
	return "waterpure";
	}
	
}
