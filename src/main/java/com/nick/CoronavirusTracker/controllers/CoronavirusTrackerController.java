package com.nick.CoronavirusTracker.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nick.CoronavirusTracker.services.CoronavirusDataService;

@Controller
public class CoronavirusTrackerController {

	@Autowired
	private CoronavirusDataService coronavirusDataService;
	
	@GetMapping("")
	private String landing(Model model) throws IOException, InterruptedException {
		
		//model.addAttribute("World", coronavirusDataService.fetchVirusData());
		
		return "index.jsp";
	}
}
