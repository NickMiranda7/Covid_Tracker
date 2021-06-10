package com.nick.CoronavirusTracker.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.CoronavirusTracker.models.World;
import com.nick.CoronavirusTracker.services.CoronavirusDataService;


@RestController
public class TrackerRestController {

	@Autowired private CoronavirusDataService coronavirusDataService;
	
	
	@GetMapping("/jsonobject")
	public List<World> worldJson() {
		List<World> worlds = coronavirusDataService.worlds;
		return worlds;
	}
}
