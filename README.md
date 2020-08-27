# Building a RESTful Web Service with Spring Boot and Hibernate Learning Project

![Alt text](/notes/applicationArchitecture.png?raw=true "Title")


Spring Frameworks used
- Spring boot
- Spring web (Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container)
- Spring Data JPA (Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate)
- Spring Boot DevTools (Provides fast application restarts, LiveReload, and configurations for enhanced development experience)
- MySQL Driver (MySQL JDBC and R2DBC driver)

Database used
- MYSQL Community Server

Development tools
- MYSQL Workbench
- Postman
- Eclipse IDE
- Apache Maven

---

Hibernate One-to-Many Bi-Directional mapping
- A household can have many family members (one to many).
- Many family members can have only 1 household (Many to one).

- Assumption: 
Each family member can only have 1 registered house hold, we will not deal with many to many mapping.
Assume the household table have unique address fields.

- Requirements: 
If you delete a household, do not delete the family members (No cascade delete).
If you delete a family member, do not delete the household.


---
# Installation
- MYSQL Community Server
- MYSQL Workbench
- Postman
- Eclipse IDE
- Apache Maven
- Java JDK 8
# Project Setup
1. Run the SQL scripts in  mysql-scipts folder.
2. Import the project with Eclipse IDE

# Running Application with Eclipse IDE
1. Open CruddemoApplication.java  -> run as Java Application

# Compiling Jar
1. Open command prompt in project root directory  
2. cmd> mvn install 

# Running Application Standalone
1. Download the latest jar file from release
2. cmd> java -jar cruddemo-0.0.1-SNAPSHOT.jar
---
# RESTful Endpoints
Apache Tomcat default endpoint: http://localhost:8080/
- Example: http://localhost:8080/household/list
#### Household CURD Endpoints
| HTTP Method | Endpoint | CURD action |
| ------ | ------ | ------ |
| GET | /household/list | Retrieve a list of households|
| GET | /household/list/{householdId} | Retrieve a single household |
| POST | /household/create | Create a new household |
| PUT | /household/update | Update a household |
| DELETE | /household/delete | Delete a household |

#### Family Member CURD Endpoints
| HTTP Method | Endpoint | CURD action |
| ------ | ------ | ------ |
| GET | /household/family/list | Retrieve a list of family members|
| GET | /household/family/list/{householdId} | Retrieve a single family member |
| POST | /household/family/create | Create a new household |
| PUT | /household/family/update | Update a family member |
| DELETE | /household/family/delete | Delete a family member |

#### Govt Grant Search Endpoints
| HTTP Method | Endpoint | CURD action | Optional Params |
| ------ | ------ | ------ | ------ |
| GET | /household/family/list/student?householdSize=2&totalIncome=3000 | Retrieve a list of Student Encouragement Bonus | householdSize, totalIncome |
| GET | /household/family/list/family?householdSize=2&totalIncome=3000 | Retrieve a list of Family Togetherness Scheme | householdSize, totalIncome |
| GET | /household/family/list/elder?householdSize=2&totalIncome=3000 | Retrieve a list of Elder Bonus | householdSize, totalIncome |
| GET | /household/family/list/baby?householdSize=2&totalIncome=3000 | Retrieve a list of Baby Sunshine Grant | householdSize, totalIncome |
| GET | /household/family/list/yolo?householdSize=2&totalIncome=3000 | Retrieve a list of Yolo GST Grant | householdSize, totalIncome |
| GET | /household/family/list/schemes?householdSize=2&totalIncome=3000 | Retrieve all grants | householdSize, totalIncome |
| GET | /household/family/list/scheme?type=yolo,student&householdSize=8&totalIncome=300000 | Retrieve grant or grant(s) where applicable | householdSize, totalIncome, type={student, family, elder, baby, yolo} |

*householdSize refers to the int maximum household size.* <br/>
*totalIncome refers to the int maximum total income.*  <br/>
<br/>

---
# Project Objectives

Provide REST End-Points for the following:
1. Create Household
   a. This endpoint lets you create the household (housing unit)
   b. Household fields:
      - HousingType (Possible options: Landed, Condominium, HDB)
2. Add a family member to household
  a. This endpoint lets you add a family member to the household
  b. Family member detail fields:
     - Name
     - Gender
     - MaritalStatus
     - Spouse (either name of spouse or primary key)
     - OccupationType (Options: Unemployed, Student, Employed)
     - AnnualIncome
     - DOB
3.	List households
  a. This endpoint lists all the households in the database
  b. Fields:
     - HouseholdType
     - FamilyMembers
       - Name
       - Gender
       - MaritalStatus
       - Spouse (either name of spouse or primary key)
       - OccupationType (Options: Unemployed, Student, Employed)
       - AnnualIncome
       - DOB
4.	Show household
  a. This endpoint shows the details of a household in the database
  b. Fields:
     - HouseholdType
     - FamilyMembers
       - Name
       - Gender
       - MaritalStatus
       - OccupationType (Options: Unemployed, Student, Employed)
       - AnnualIncome
       - DOB
5.	Search for households and recipients of grant disbursement endpoint. 
  a. This endpoint should accept search parameters (eg. Household size, total income) in the URL and return results based on the criteria below.
  b. You may include assumptions about each result in the README.
  c. Grant Schemes:
    - List households and qualifying family members for Student Encouragement Bonus
       - Households with children of less than 16 years old.
       - Household income of less than $150,000.
    - List households and qualifying family members for Family Togetherness Scheme
       - Households with husband & wife
       - Has child(ren) younger than 18 years old.
      
    - List households and qualifying family members for Elder Bonus
       - HDB household with family members above the age of 50.
    - List households and qualifying family members for Baby Sunshine Grant
       - Household with young children younger than 5.
    - List households that qualify for the YOLO GST Grant
       - HDB households with annual income of less than $100,000.

Optional End-Points 
- Delete household
  - Remove Household and family members.
- Delete Family Member
  - Remove Family Member from the Household.

---
Special thanks to the reference course and reference project from:  <br/>
https://www.udemy.com/course/spring-hibernate-tutorial/ 

