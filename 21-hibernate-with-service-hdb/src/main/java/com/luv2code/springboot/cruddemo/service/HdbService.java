package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Map;

import com.luv2code.springboot.cruddemo.entity.FamilyMember;
import com.luv2code.springboot.cruddemo.entity.Household;

public interface HdbService {
	
	// Household CRUD
	public List<Household> findAllHousehold();
	
	public Household findByIdHousehold(int theId);
	
	public void saveHousehold(Household theHousehold);
	
	public void deleteByIdHousehold (int theId);
	
	
	// Family CRUD
	public List<FamilyMember> findAllFamily();
	
	public FamilyMember findByIdFamilyMember (int theId);
	
	public FamilyMember findByNameFamilyMember (String theName);
	
	public void saveFamilyMember (FamilyMember theFamilyMember);
	
	public void deleteByIdFamilyMember(int theId);
	
	// Grant queries
	
	public List<Map<String,String>> listGrantStudentEncouragementBonus(String householdSize, String totalIncome);

	public List<Map<String,String>> listGrantFamilyTogethernessScheme(String householdSize, String totalIncome);
	
	public List<Map<String,String>> listGrantElderBonus(String householdSize, String totalIncome);
	
	public List<Map<String,String>> listGrantBabySunshineGrant(String householdSize, String totalIncome);
	
	public List<Map<String,String>> listGrantYoloGstGrant(String householdSize, String totalIncome);

}
