package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.FamilyMemberDAO;
import com.luv2code.springboot.cruddemo.dao.HouseholdDAO;
import com.luv2code.springboot.cruddemo.entity.FamilyMember;
import com.luv2code.springboot.cruddemo.entity.Household;

//Delegate the calls to the DAO
@Service
public class HdbServiceImpl implements HdbService {
	
	private FamilyMemberDAO familyMemberDAO;
	
	private HouseholdDAO householdDAO;
	
	// Use spring constructor injection
	@Autowired
	public HdbServiceImpl(FamilyMemberDAO theFamilyMemberDAO, HouseholdDAO theHouseholdDAO) {
		this.familyMemberDAO = theFamilyMemberDAO;
		this.householdDAO = theHouseholdDAO;
	}

	@Override
	@Transactional
	public List<Household> findAllHousehold() {
		return householdDAO.findAll();
	}

	@Override
	@Transactional
	public Household findByIdHousehold(int theId) {
		return householdDAO.findById(theId);
	}

	@Override
	@Transactional
	public void saveHousehold(Household theHousehold) {
		householdDAO.save(theHousehold);

	}

	@Override
	@Transactional
	public void deleteByIdHousehold(int theId) {
		householdDAO.deleteById(theId);

	}

	@Override
	@Transactional
	public List<FamilyMember> findAllFamily() {
		return familyMemberDAO.findAll();
	}

	@Override
	@Transactional
	public FamilyMember findByIdFamilyMember(int theId) {
		return familyMemberDAO.findById(theId);
	}

	@Override
	@Transactional
	public void saveFamilyMember(FamilyMember theFamilyMember) {
		familyMemberDAO.save(theFamilyMember);
	}

	@Override
	@Transactional
	public void deleteByIdFamilyMember(int theId) {
		familyMemberDAO.deleteById(theId);
	}

}
