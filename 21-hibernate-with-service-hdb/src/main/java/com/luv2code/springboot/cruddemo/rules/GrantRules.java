package com.luv2code.springboot.cruddemo.rules;

import java.util.List;
import java.util.Map;

public interface GrantRules {
	
	List<Map<String,String>> listGrantStudentEncouragementBonus(String householdSize, String totalIncome);
	
	List<Map<String,String>> listGrantFamilyTogethernessScheme(String householdSize, String totalIncome);
	
	List<Map<String,String>> listGrantElderBonus(String householdSize, String totalIncome);
	
	List<Map<String,String>> listGrantBabySunshineGrant(String householdSize, String totalIncome);
	
	List<Map<String,String>> listGrantYoloGstGrant(String householdSize, String totalIncome);

}
