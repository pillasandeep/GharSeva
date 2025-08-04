package com.example.Webapp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Webapp.ServicesList.AllServicesTemplate;



@Controller
public class AcServiceController {

	@GetMapping("/ac_services")
	public String loadAcServices(Model model)
	{
		
		List<AllServicesTemplate> acservices = new ArrayList<>();

        acservices.add(new AllServicesTemplate("Ac Installation",999,"images/ac/acinstallation.webp"));
        acservices.add(new AllServicesTemplate("Ac Uninstallation",489,"images/ac/acinstallation.webp"));
        acservices.add(new AllServicesTemplate("Ac General Service",499,"images/ac/generalservice.webp"));
        acservices.add(new AllServicesTemplate("Ac Chemical Service",999,"images/ac/acchemicalservice.webp"));
        acservices.add(new AllServicesTemplate("Ac Foam Serivce",899,"images/ac/acfoamservice.webp"));
        acservices.add(new AllServicesTemplate("Ac Gas filling",2499,"images/ac/acgasfilling.webp"));
        acservices.add(new AllServicesTemplate("Ac Noise | Smell ",499,"images/ac/acnoise.webp"));
        acservices.add(new AllServicesTemplate("Ac Less Cooling",399,"images/ac/acnocooling.webp"));
        acservices.add(new AllServicesTemplate("Ac Power Issue",499,"images/ac/acpowerissue.webp"));
        acservices.add(new AllServicesTemplate("Ac Water Leakage",499,"images/ac/acwaterlekage.webp"));

        //acservices.add(new WmSerivcesList("Inllet Wall",1499,"images/wm/inlet.webp"));
        model.addAttribute("wmservices", acservices);
        return "ac_services";  // Thymeleaf template
		
	}
	
	
	
}
