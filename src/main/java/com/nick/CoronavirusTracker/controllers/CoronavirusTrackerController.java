package com.nick.CoronavirusTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nick.CoronavirusTracker.models.LocationStats;
import com.nick.CoronavirusTracker.services.CoronavirusDataService;

@Controller
public class CoronavirusTrackerController {

	@Autowired
	private CoronavirusDataService coronavirusDataService;
	
	@GetMapping("")
	private String landing(Model model) {

		// this grabs the last item in the array list which also has the most accurate total cases data
		LocationStats latestTotalStats2 = coronavirusDataService.allStats.get(coronavirusDataService.allStats.size() - 1);
		//adding that item to the model
		model.addAttribute("locationDataTotal", latestTotalStats2);
		
		model.addAttribute("locationData", coronavirusDataService.allStats);
		
		return "landingPage.jsp";
	}
}
