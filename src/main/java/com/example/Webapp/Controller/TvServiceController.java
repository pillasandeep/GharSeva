package com.example.Webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Webapp.ServicesList.AllServicesTemplate;

import java.util.*;
@Controller
public class TvServiceController {

	@GetMapping("/tv")
	public String LoadService(Model model)
	{
		List<AllServicesTemplate>tvservice=new ArrayList<>();
		tvservice.add(new AllServicesTemplate("Tv Installation",499,"/images/tv/tvinstall.png"));
		tvservice.add(new AllServicesTemplate("Tv Uninstall",399,"/images/tv/uninstall.png"));
		tvservice.add(new AllServicesTemplate("Tv Cleaning",399,"/images/tv/clean.png"));
		model.addAttribute("service", tvservice);
		return "tv";
	}
}
