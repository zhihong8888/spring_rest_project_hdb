package com.luv2code.springboot.cruddemo.rules;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;

@Repository
public class GrantRulesImpl implements GrantRules {
	
	// define field for entity manager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public GrantRulesImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<Map<String,String>> listGrantStudentEncouragementBonus(String householdSize, String totalIncome) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		System.out.println("householdSize: " + householdSize + " , " + "totalIncome: " + totalIncome);
		Query tempQuery = currentSession.createSQLQuery(
				"call student_encouragement_bonus("+ householdSize +","+ totalIncome +")");
		tempQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Object[]> queryResult = tempQuery.list();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate3Module());
		
		List<Map<String,String>> parsedQueryResult = null;
		try {
			String jsonString = objectMapper.writeValueAsString(queryResult);
			System.out.println(jsonString);
			
			parsedQueryResult = objectMapper.readValue(jsonString, new TypeReference<List<Map<String,String>>>(){});
			System.out.println(parsedQueryResult);
			
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
		
		return parsedQueryResult;
	}

	@Override
	public List<Map<String, String>> listGrantFamilyTogethernessScheme(String householdSize, String totalIncome) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		System.out.println("householdSize: " + householdSize + " , " + "totalIncome: " + totalIncome);
		Query tempQuery = currentSession.createSQLQuery(
				"call family_togetherness("+ householdSize +","+ totalIncome +")");
		tempQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Object[]> queryResult = tempQuery.list();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate3Module());
		
		List<Map<String,String>> parsedQueryResult = null;
		try {
			String jsonString = objectMapper.writeValueAsString(queryResult);
			System.out.println(jsonString);
			
			parsedQueryResult = objectMapper.readValue(jsonString, new TypeReference<List<Map<String,String>>>(){});
			System.out.println(parsedQueryResult);
			
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
		
		return parsedQueryResult;
	}

	@Override
	public List<Map<String, String>> listGrantElderBonus(String householdSize, String totalIncome) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		System.out.println("householdSize: " + householdSize + " , " + "totalIncome: " + totalIncome);
		Query tempQuery = currentSession.createSQLQuery(
				"call elder_bonnus("+ householdSize +","+ totalIncome +")");
		tempQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Object[]> queryResult = tempQuery.list();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate3Module());
		
		List<Map<String,String>> parsedQueryResult = null;
		try {
			String jsonString = objectMapper.writeValueAsString(queryResult);
			System.out.println(jsonString);
			
			parsedQueryResult = objectMapper.readValue(jsonString, new TypeReference<List<Map<String,String>>>(){});
			System.out.println(parsedQueryResult);
			
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
		
		return parsedQueryResult;
	}

	@Override
	public List<Map<String, String>> listGrantBabySunshineGrant(String householdSize, String totalIncome) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		System.out.println("householdSize: " + householdSize + " , " + "totalIncome: " + totalIncome);
		Query tempQuery = currentSession.createSQLQuery(
				"call baby_sunshine_grant("+ householdSize +","+ totalIncome +")");
		tempQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Object[]> queryResult = tempQuery.list();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate3Module());
		
		List<Map<String,String>> parsedQueryResult = null;
		try {
			String jsonString = objectMapper.writeValueAsString(queryResult);
			System.out.println(jsonString);
			
			parsedQueryResult = objectMapper.readValue(jsonString, new TypeReference<List<Map<String,String>>>(){});
			System.out.println(parsedQueryResult);
			
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
		
		return parsedQueryResult;
	}
	
	@Override
	public List<Map<String, String>> listGrantYoloGstGrant(String householdSize, String totalIncome) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		System.out.println("householdSize: " + householdSize + " , " + "totalIncome: " + totalIncome);
		Query tempQuery = currentSession.createSQLQuery(
				"call yolo_gst_grant("+ householdSize +","+ totalIncome +")");
		tempQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Object[]> queryResult = tempQuery.list();
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate3Module());
		
		List<Map<String,String>> parsedQueryResult = null;
		try {
			String jsonString = objectMapper.writeValueAsString(queryResult);
			System.out.println(jsonString);
			
			parsedQueryResult = objectMapper.readValue(jsonString, new TypeReference<List<Map<String,String>>>(){});
			System.out.println(parsedQueryResult);
			
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
		
		return parsedQueryResult;
	}

}
