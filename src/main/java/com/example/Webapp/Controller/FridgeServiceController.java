package com.example.Webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Webapp.ServicesList.AllServicesTemplate;

import java.util.*;
@Controller
public class FridgeServiceController {

	@GetMapping("/fridge")
	public String LoadServices(Model model)
	{
		List<AllServicesTemplate>fridgeservice=new ArrayList<>();
		fridgeservice.add(new AllServicesTemplate("Fridge Power Issue",399,"/images/fridge/Fridgepowerinssue.png"));
		fridgeservice.add(new AllServicesTemplate("Fridge Less Cooling",349,"/images/fridge/lesscooling.webp"));
		fridgeservice.add(new AllServicesTemplate("Fridge Noise Issue",359,"/images/fridge/noise.png"));
		fridgeservice.add(new AllServicesTemplate("Fridge Door Issue",259,"/images/fridge/door.webp"));
		fridgeservice.add(new AllServicesTemplate("Fridge Water Leakage",399,"/images/fridge/waterleakage.webp"));
		model.addAttribute("services", fridgeservice);
		
		return "fridge";
	}
	
	
	
}
