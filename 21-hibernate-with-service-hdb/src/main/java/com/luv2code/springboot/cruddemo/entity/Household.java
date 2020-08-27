package com.luv2code.springboot.cruddemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="household")
public class Household {
	
	// annotate the class as an entity and map to the db table
	
	// define the fields
	
	// annotate the fields with db column names
	
	// ** set up mapping to FamilyMember Entity **
	
	// create constructors
	
	// generate getters and setters
	
	// generate toString() method
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="housing_type")
	private String housingType;
	
	@OneToMany(mappedBy="household", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JsonBackReference
	private List<FamilyMember> familyMember;
	
	public Household() {
		
	}

	public Household(String housingType) {
		this.housingType = housingType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHousingType() {
		return housingType;
	}

	public void setHousingType(String housingType) {
		this.housingType = housingType;
	}

	public List<FamilyMember> getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(List<FamilyMember> familyMember) {
		this.familyMember = familyMember;
	}
	
	// add convenience methods for bi-directional relationship
	public void add(FamilyMember tempFamilyMember) {
		if(tempFamilyMember == null) {
			familyMember = new ArrayList<>();
		}
		
		familyMember.add(tempFamilyMember);
		
		tempFamilyMember.setHousehold(this);
	}

	@Override
	public String toString() {
		return "Household [id=" + id + ", housingType=" + housingType + "]";
	}

}
