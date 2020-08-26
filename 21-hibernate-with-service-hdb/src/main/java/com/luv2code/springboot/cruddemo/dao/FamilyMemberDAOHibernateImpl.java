package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.FamilyMember;

@Repository
public class FamilyMemberDAOHibernateImpl implements FamilyMemberDAO {
	
	// define field for entity manager
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public FamilyMemberDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<FamilyMember> findAll() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<FamilyMember> theQuery = currentSession.createQuery("from FamilyMember", FamilyMember.class);
		
		// execute query and get result list
		List<FamilyMember> familyMembers = theQuery.getResultList();
		
		// return the results
		return familyMembers;
	}

	@Override
	public FamilyMember findById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the household
		FamilyMember theFamilyMember = currentSession.get(FamilyMember.class, theId);
		
		// return the household
		return theFamilyMember;
	}

	@Override
	public void save(FamilyMember theFamilyMember) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.update(theFamilyMember);

	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery(
				"delete from FamilyMember where id=:familyMemberId");
		theQuery.setParameter("familyMemberId", theId);
		theQuery.executeUpdate();	

	}

}
