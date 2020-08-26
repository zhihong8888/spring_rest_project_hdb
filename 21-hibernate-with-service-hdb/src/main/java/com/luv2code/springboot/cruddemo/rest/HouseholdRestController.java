package com.luv2code.springboot.cruddemo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Household;
import com.luv2code.springboot.cruddemo.service.HdbServiceImpl;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {

	private HdbServiceImpl hdbServiceImpl;
	
	// Use spring constructor injection
	@Autowired
	public HouseholdRestController(HdbServiceImpl theHdbServiceImpl) {
		this.hdbServiceImpl = theHdbServiceImpl;
	}
	

	@GetMapping("/list")
	public List<Household> findAllHousehold(){
		return hdbServiceImpl.findAllHousehold();
	}
	
	// add mapping got GET /employees/{employeeId}
	@GetMapping("/list/{householdId}")
	public Household findHouseholdById(@PathVariable int householdId) {
		Household household = hdbServiceImpl.findByIdHousehold(householdId);
		
		Map <String, String> responseMap = new HashMap<>();
		if(household == null) { 
			throw new RuntimeException("Household id not found - " + householdId);
		}
		 
		return household;
		
	}
	
	@PostMapping("/create")
	public Household addHouseHold(@RequestBody Household theHousehold) {
		// also just in case they pass an id in json ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theHousehold.setId(0);
		
		hdbServiceImpl.saveHousehold(theHousehold);
		
		return theHousehold;
	}
	
	@PutMapping("/update")
	public Household updateHouseHold(@RequestBody Household theHousehold) {

		hdbServiceImpl.saveHousehold(theHousehold);
		
		return theHousehold;
	}
	
}
