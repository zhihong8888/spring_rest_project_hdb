package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Household;

public interface HouseholdDAO {
	
	public Household findById(int theId);
	
	public void save(Household theHousehold);
	
	public void deleteById(int theId);

	public List<Object[]> listHouseholds();

	public List<Household> findHouseholdTypes();

	public List<Object[]> listFamilyHouseholds();

	public List<Object[]> findFamilyHouseholdById(int theId);

	public List<Object[]> listGrantStudentEncouragement(int householdSize, int totalIncome);
}
