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
	@Transactional
	public List<Household> findHouseholdTypes() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<Household> theQuery = currentSession.createQuery("from Household", Household.class);

		// execute query and get result list
		List<Household> households = theQuery.getResultList();

		return households;
	}

	@Override
	@Transactional
	public List<Object[]> listHouseholds() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		List<Object[]> theQuery = currentSession.createQuery("from Household h join h.familyMember").list();

		return theQuery;
	}

	@Override
	@Transactional
	public Household findById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the household
		Household theHousehold = currentSession.get(Household.class, theId);

		// return the household
		return theHousehold;
	}

	@Override
	@Transactional
	public List<Object[]> findFamilyHouseholdById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query tempQuery = currentSession.createQuery("from Household h join h.familyMember where h.id=:householdId");
		tempQuery.setParameter("householdId", theId);
		List<Object[]> theQuery = tempQuery.list();

		return theQuery;
	}

	@Override
	@Transactional
	public List<Object[]> listFamilyHouseholds() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		List<Object[]> theQuery = currentSession.createQuery("from Household h join h.familyMember").list();

		return theQuery;
	}

	@Override
	@Transactional
	public void save(Household theHousehold) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save employee
		currentSession.saveOrUpdate(theHousehold);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Household where id=:householdId");
		theQuery.setParameter("householdId", theId);
		theQuery.executeUpdate();

	}

	// Households with children of less than 16 years old.
	// Household income of less than $150,000.

	@Override
	@Transactional
	public List<Object[]> listGrantStudentEncouragement(int householdSize, int totalIncome) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		System.out.println("householdSize: " + householdSize + " , " + "totalIncome: " + totalIncome);
		Query tempQuery = currentSession.createSQLQuery(
				"call student_encouragement_bonus("+ householdSize +","+ totalIncome +")");
		tempQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Object[]> theQuery = tempQuery.list();
		
		return theQuery;
	}

}
//"( select f.household.id, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(), f.date)),'%Y') as age from Household h join h.familyMember f )");
//"select f.household.id ,h.housingType from Household h join h.familyMember f"