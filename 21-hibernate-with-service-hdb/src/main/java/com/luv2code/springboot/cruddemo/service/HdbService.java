package com.luv2code.springboot.cruddemo.service;

import java.util.List;

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
	
	public void saveFamilyMember (FamilyMember theFamilyMember);
	
	public void deleteByIdFamilyMember(int theId);

}
