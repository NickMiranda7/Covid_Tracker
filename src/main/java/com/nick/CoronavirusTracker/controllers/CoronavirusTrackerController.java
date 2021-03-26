package com.nick.CoronavirusTracker.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nick.CoronavirusTracker.models.World;
import com.nick.CoronavirusTracker.services.CoronavirusDataService;


@Controller
public class CoronavirusTrackerController {


	@Autowired private CoronavirusDataService coronavirusDataService;
	
	
	@GetMapping("")
	private String landing(Model model) throws IOException, InterruptedException {
		
		ArrayList<String> worldNames = new ArrayList<String>();
		worldNames.add("worldCountries");
		worldNames.add("USA");
		
		for(String worldName : worldNames) {
			World world = new World();
			if (worldName == "USA") {
				world = coronavirusDataService.worlds.get(0);
				
			}
			else
			{
				world = coronavirusDataService.worlds.get(1);
			}
	
			//model.addAttribute("worldUS");
			model.addAttribute(worldName, world.getCountry_Regions());
		}
		model.addAttribute("worldUS", coronavirusDataService.worlds.get(0));
		model.addAttribute("worldWide", coronavirusDataService.worlds.get(1));
		
		
		return "index.jsp";
	}
}
