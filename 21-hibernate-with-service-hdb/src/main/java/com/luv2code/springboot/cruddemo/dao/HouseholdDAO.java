package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Household;

public interface HouseholdDAO {
	
	public Household findById(int theId);
	
	public void save(Household theHousehold);
	
	public void deleteById(int theId);

	public List<Household> findAll();
	
}
