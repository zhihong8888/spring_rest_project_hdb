package com.luv2code.springboot.cruddemo.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;
import com.google.gson.Gson;
import com.luv2code.springboot.cruddemo.dao.FamilyMemberDAO;
import com.luv2code.springboot.cruddemo.dao.HouseholdDAO;
import com.luv2code.springboot.cruddemo.entity.FamilyMember;
import com.luv2code.springboot.cruddemo.entity.Household;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {
	
	private FamilyMemberDAO familyMemberDAO;
	private HouseholdDAO householdDAO;
	
	// quick and dirty: inject HouseholdDAO (use constructor injection)
	@Autowired
	public HouseholdRestController(FamilyMemberDAO theFamilyMemberDAO, HouseholdDAO theHouseholdDAO) {
		familyMemberDAO = theFamilyMemberDAO;
		householdDAO = theHouseholdDAO;
	}
	
	@GetMapping("/family")
	public List<FamilyMember> findAllFamily(){
		return familyMemberDAO.findAll();
	}

	@GetMapping("/list")
	public List<Household> findAllHousehold(){
		return householdDAO.findHouseholdTypes();
	}
	
	@PostMapping("/create")
	public Household addHouseHold(@RequestBody Household theHousehold) {
		// also just in case they pass an id in json ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theHousehold.setId(0);
		
		householdDAO.save(theHousehold);
		
		return theHousehold;
	}
	
	@PutMapping("/update")
	public Household updateHouseHold(@RequestBody Household theHousehold) {

		householdDAO.save(theHousehold);
		
		return theHousehold;
	}
	
	
	
	@GetMapping("/family/list")
	public List<Object[]> listAllHousehold(){
		return householdDAO.listFamilyHouseholds();
	}
	
	// add mapping got GET /employees/{employeeId}
	@GetMapping("/family/list/{householdId}")
	public List<Object[]>  getHousehold(@PathVariable int householdId) {
		List<Object[]> household = householdDAO.findFamilyHouseholdById(householdId);
		/*
		 * if(household == null || household.isEmpty()) { throw new
		 * RuntimeException("Household id not found - " + householdId); }
		 */
		return household;
		
	}
	
	// add mapping got GET /employees/{employeeId}
	@GetMapping("/family/list/search")
	public List<Map<String,String>>  getHouseholdScheme(
			@RequestParam (required=false) int householdSize, @RequestParam (required=false) int totalIncome) {

		 
		 //ObjectMapper mapper = new ObjectMapper();
        //mapper.registerModule(new Hibernate3Module());
        //json = mapper.writeValueAsString(details);
		
		//Gson gson = new Gson();
		//HashMap<*,*> object = new ObjectMapper().readValue(body, HashMap.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate3Module());
		List<Map<String,String>> listCar = null;
		try {
			List<Object[]> result = householdDAO.listGrantStudentEncouragement(householdSize, totalIncome);
			String jsonString = objectMapper.writeValueAsString(result);
			System.out.println(jsonString);
			
			listCar = objectMapper.readValue(jsonString, new TypeReference<List<Map<String,String>>>(){});
			System.out.println(listCar);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//return householdDAO.listGrantStudentEncouragement(householdSize, totalIncome);
		return listCar;
		
	}
	
	// add mapping for POST /household - add new household
	/*
	{
    "name": "tangs",
    "gender": "male",
    "maritalStatus": "married",
    "spouseId": "2",
    "occupationType": "unemployed",
    "annualIncome": 0,
    "date": "1994-01-24",
    "household": {
	        "id": 1,
	        "housingType": "Condominium"
	    }
	}
	 */
	@PostMapping("/family/create")
	public FamilyMember addFamilyMember(@RequestBody FamilyMember theFamilyMember) {
		// also just in case they pass an id in json ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theFamilyMember.setId(0);
		
		familyMemberDAO.save(theFamilyMember);
		
		return theFamilyMember;
	}
	
	/*
	 {
	    "id": 10,
	    "name": "william",
	    "gender": "male",
	    "maritalStatus": "married",
	    "spouseId": "2",
	    "occupationType": "unemployed",
	    "annualIncome": 0,
	    "date": "1994-01-25",
	    "household": {
	        "id": 1,
	        "housingType": "Condominium"
	    }
	}

	 */
	
	@PutMapping("/family/update")
	public FamilyMember updateFamilyMember(@RequestBody FamilyMember theFamilyMember) {

		familyMemberDAO.save(theFamilyMember);
		
		return theFamilyMember;
	}
	
}
