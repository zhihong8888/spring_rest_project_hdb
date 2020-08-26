package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Household;

@Repository
public class HouseholdDAOHibernateImpl implements HouseholdDAO {

	// define field for entity manager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public HouseholdDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Household> findAll() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<Household> theQuery = currentSession.createQuery("from Household", Household.class);

		// execute query and get result list
		List<Household> households = theQuery.getResultList();

		return households;
	}
	
	@Override
	public Household findById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the household
		Household theHousehold = currentSession.get(Household.class, theId);

		// return the household
		return theHousehold;
	}

	@Override
	public void save(Household theHousehold) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save employee
		currentSession.saveOrUpdate(theHousehold);

	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Household where id=:householdId");
		theQuery.setParameter("householdId", theId);
		theQuery.executeUpdate();

	}

}
