package com.luv2code.springboot.cruddemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="family_member")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class FamilyMember {

	// annotate the class as an entity and map to the db table
	
	// define the fields
	
	// annotate the fields with db column names
	
	// ** set up mapping to instructorDetail Entity **
	
	// create constructors
	
	// generate getters and setters
	
	// generate toString() method
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="marital_status")
	private String maritalStatus;
	
	@Column(name="spouse_id")
	private String spouseId;
	
	@Column(name="occupation_type")
	private String occupationType;

	@Column(name="annual_income")
	private int annualIncome;
	
	@Column(name="dob")
	private String date;
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="household_id")
	//@JsonManagedReference 
	private Household household;
	
	public FamilyMember() {
		
	}

	public FamilyMember(String name, String gender, String maritalStatus, String spouseId, String occupationType,
			int annualIncome, String date, Household household) {
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.spouseId = spouseId;
		this.occupationType = occupationType;
		this.annualIncome = annualIncome;
		this.date = date;
		this.household = household;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

	public String getSpouseId() {
		return spouseId;
	}

	public void setSpouseId(String spouseId) {
		this.spouseId = spouseId;
	}

	@Override
	public String toString() {
		return "FamilyMember [id=" + id + ", name=" + name + ", gender=" + gender + ", maritalStatus=" + maritalStatus
				+ ", spouseId=" + spouseId + ", occupationType=" + occupationType + ", annualIncome=" + annualIncome
				+ ", date=" + date + ", household=" + household + "]";
	}
	
	
	
}
