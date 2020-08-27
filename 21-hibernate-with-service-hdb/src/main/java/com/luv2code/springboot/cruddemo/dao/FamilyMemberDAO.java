package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.FamilyMember;


public interface FamilyMemberDAO {

	public List<FamilyMember> findAll();
	
	public FamilyMember findById(int theId);
	
	public FamilyMember findByName(String theName);
	
	public void save(FamilyMember theFamilyMember);
	
	public void deleteById(int theId);
}
