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
import org.springframework.web.bind.annotation.RequestParam;
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
		
		try 
		{ 
		    // checking valid integer using parseInt() method 
		    Integer.parseInt(theFamilyMember.getSpouseId()); 
		}  
		catch (NumberFormatException e)  { 
		    // Not a primary key, 
			String spouseName = theFamilyMember.getSpouseId();
			FamilyMember tempFamilyMember = hdbService.findByNameFamilyMember(spouseName);
			if(tempFamilyMember == null) {
				throw new RuntimeException("Spouse not found - " + spouseName);
			}
			theFamilyMember.setSpouseId(Integer.toString(tempFamilyMember.getId()));
		} 
	    
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
	
	
	//---------------------------------------------------------------------------------------------
	// Grants
	
	@GetMapping("/list/student")
	public List<Map<String,String>> listGrantStudentEncouragementBonus (
			@RequestParam (required=false) String householdSize, @RequestParam (required=false) String totalIncome) {
		return hdbService.listGrantStudentEncouragementBonus(householdSize, totalIncome);
		
	}
	
	@GetMapping("/list/family")
	public List<Map<String,String>> listGrantFamilyTogethernessScheme (
			@RequestParam (required=false) String householdSize, @RequestParam (required=false) String totalIncome) {
		return hdbService.listGrantFamilyTogethernessScheme(householdSize, totalIncome);
		
	}
	
	@GetMapping("/list/elder")
	public List<Map<String,String>> listGrantElderBonus (
			@RequestParam (required=false) String householdSize, @RequestParam (required=false) String totalIncome) {
		return hdbService.listGrantElderBonus(householdSize, totalIncome);
		
	}
	
	@GetMapping("/list/baby")
	public List<Map<String,String>> listGrantBabySunshineGrant (
			@RequestParam (required=false) String householdSize, @RequestParam (required=false) String totalIncome) {
		return hdbService.listGrantBabySunshineGrant(householdSize, totalIncome);
		
	}
	
	@GetMapping("/list/yolo")
	public List<Map<String,String>> listGrantYoloGstGrant (
			@RequestParam (required=false) String householdSize, @RequestParam (required=false) String totalIncome) {
		return hdbService.listGrantYoloGstGrant(householdSize, totalIncome);
		
	}
	
	@GetMapping("/list/schemes")
	public Map<String,List<Map<String,String>>> listAllGrants (
			@RequestParam (required=false) String householdSize, 
			@RequestParam (required=false) String totalIncome) {
		Map<String,List<Map<String,String>>> responseMap = new HashMap<>();
		
		responseMap.put("Student Encouragement Bonus", hdbService.listGrantStudentEncouragementBonus(householdSize, totalIncome));
		responseMap.put("Family Togetherness", hdbService.listGrantFamilyTogethernessScheme(householdSize, totalIncome));
		responseMap.put("Elder Bonus", hdbService.listGrantElderBonus(householdSize, totalIncome));
		responseMap.put("Baby Sunshine Grant", hdbService.listGrantBabySunshineGrant(householdSize, totalIncome));
		responseMap.put("Yolo Gst Grant", hdbService.listGrantYoloGstGrant(householdSize, totalIncome));
		
		return responseMap;
		
	}
	
	@GetMapping("/list/scheme")
	public Map<String,List<Map<String,String>>> listGrants (
			@RequestParam (required=false) String householdSize, 
			@RequestParam (required=false) String totalIncome,
			@RequestParam (value="type",required=false) List<String> schemes) {
		Map<String,List<Map<String,String>>> responseMap = new HashMap<>();
		
		if(schemes == null) {
			throw new RuntimeException("Missing params type={student, family, elder, baby, yolo}");
		}
		
		for(String scheme: schemes) {
			if(scheme.equals("student")) {
				responseMap.put("Student Encouragement Bonus", hdbService.listGrantStudentEncouragementBonus(householdSize, totalIncome));
			}
			
			if(scheme.equals("family")) {
				responseMap.put("Family Togetherness", hdbService.listGrantFamilyTogethernessScheme(householdSize, totalIncome));
			}
			
			if(scheme.equals("elder")) {
				responseMap.put("Elder Bonus", hdbService.listGrantElderBonus(householdSize, totalIncome));
			}
			
			if(scheme.equals("baby")) {
				responseMap.put("Baby Sunshine Grant", hdbService.listGrantBabySunshineGrant(householdSize, totalIncome));
			}
			
			if(scheme.equals("yolo")) {
				responseMap.put("Yolo Gst Grant", hdbService.listGrantYoloGstGrant(householdSize, totalIncome));
			}
		}
		
		if(responseMap.isEmpty()) {
			throw new RuntimeException("No valid scheme type found in query, valid type= {student, family, elder, baby, yolo}");
		}
		

		return responseMap;
		
	}

}
