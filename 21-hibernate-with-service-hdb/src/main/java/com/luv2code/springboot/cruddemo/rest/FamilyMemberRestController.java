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

import com.luv2code.springboot.cruddemo.entity.FamilyMember;
import com.luv2code.springboot.cruddemo.service.HdbService;

@RestController
@RequestMapping("/household/family")
public class FamilyMemberRestController {
	
	private HdbService hdbService;
	
	// Use spring constructor injection
	@Autowired
	public FamilyMemberRestController(HdbService theHdbService) {
		this.hdbService = theHdbService;
	}
	
	@GetMapping("/list")
	public List<FamilyMember> findAllFamily(){
		return hdbService.findAllFamily();
	}
	
	// add mapping got GET /employees/{employeeId}
	@GetMapping("/list/{familyMemberId}")
	public FamilyMember findByIdFamilyMember(@PathVariable int familyMemberId) {
		FamilyMember familyMember = hdbService.findByIdFamilyMember(familyMemberId);
		
		Map <String, String> responseMap = new HashMap<>();
		if(familyMember == null) { 
			throw new RuntimeException("FamilyMember id not found - " + familyMemberId);
		}
		 
		return familyMember;
		
	}
	
	@PostMapping("/create")
	public FamilyMember addFamilyMember(@RequestBody FamilyMember theFamilyMember) {
		// also just in case they pass an id in json ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theFamilyMember.setId(0);
		
		hdbService.saveFamilyMember(theFamilyMember);
		
		return theFamilyMember;
	}
	
	@PutMapping("/update")
	public FamilyMember updateFamilyMember(@RequestBody FamilyMember theFamilyMember) {

		hdbService.saveFamilyMember(theFamilyMember);
		
		return theFamilyMember;
	}
	
	@DeleteMapping("/delete/{familyMemberId}")
	public String deleteHousehold(@PathVariable int familyMemberId) {
		FamilyMember tempFamilyMember = hdbService.findByIdFamilyMember(familyMemberId);
		if(tempFamilyMember == null) {
			throw new RuntimeException("Household id not found - " + familyMemberId);
		}
		hdbService.deleteByIdFamilyMember(familyMemberId);
		return "Deleted employee id - " + familyMemberId;
	}
	

}
