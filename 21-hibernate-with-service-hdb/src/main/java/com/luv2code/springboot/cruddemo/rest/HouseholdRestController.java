package com.luv2code.springboot.cruddemo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Household;
import com.luv2code.springboot.cruddemo.service.HdbService;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {

	private HdbService hdbService;
	
	// Use spring constructor injection
	@Autowired
	public void HouseholdRestController(HdbService theHdbService) {
		this.hdbService = theHdbService;
	}

	@GetMapping("/list")
	public List<Household> findAllHousehold(){
		return hdbService.findAllHousehold();
	}
	
	// add mapping got GET /employees/{employeeId}
	@GetMapping("/list/{householdId}")
	public Household findByIdHousehold(@PathVariable int householdId) {
		Household household = hdbService.findByIdHousehold(householdId);
		
		Map <String, String> responseMap = new HashMap<>();
		if(household == null) { 
			throw new RuntimeException("Household id not found - " + householdId);
		}
		 
		return household;
		
	}
	
	@PostMapping("/create")
	public Household addHousehold(@RequestBody Household theHousehold) {
		// also just in case they pass an id in json ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theHousehold.setId(0);
		
		hdbService.saveHousehold(theHousehold);
		
		return theHousehold;
	}
	
	@PutMapping("/update")
	public Household updateHousehold(@RequestBody Household theHousehold) {

		hdbService.saveHousehold(theHousehold);
		
		return theHousehold;
	}
	
	@DeleteMapping("/delete/{householdId}")
	public String deleteHousehold(@PathVariable int householdId) {
		Household tempHousehold = hdbService.findByIdHousehold(householdId);
		if(tempHousehold == null) {
			throw new RuntimeException("Household id not found - " + householdId);
		}
		hdbService.deleteByIdHousehold(householdId);
		return "Deleted employee id - " + householdId;
	}
	
}
