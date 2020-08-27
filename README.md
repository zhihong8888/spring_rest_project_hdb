# Building a RESTful Web Service with Spring Boot and Hibernate

![Alt text](/notes/applicationArchitecture.png?raw=true "Title")


Spring Frameworks used
- Spring boot
- Spring web (Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container)
- Spring Data JPA (Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate)
- Spring Boot DevTools (Provides fast application restarts, LiveReload, and configurations for enhanced development experience)
- MySQL Driver (MySQL JDBC and R2DBC driver.)

Database used
- MYSQL Community Server

Development tools
- MYSQL Workbench
- Postman
- Eclipse IDE
- Apache Maven

---

Hibernate One-to-Many Bi-Directional mapping
- A household can have many family members (one to many)
- Many family members can have only 1 household (Many to one)

- Assumption: 
Each family member can only have 1 registered house hold, we will not deal with many to many mapping.

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
# Endpoints
Apache Tomcat default endpoint: http://localhost:8080/
- example: http://localhost:8080/household/list
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
| GET | /household/family/list/student?householdSize=2&totalIncome=3000 | Retrieve a list of Student Encouragement Bonus | householdSize, total_income |
| GET | /household/family/list/family?householdSize=2&totalIncome=3000 | Retrieve a list of Family Togetherness Scheme | householdSize, total_income |
| GET | /household/family/list/elder?householdSize=2&totalIncome=3000 | Retrieve a list of Elder Bonus | householdSize, total_income |
| GET | /household/family/list/baby?householdSize=2&totalIncome=3000 | Retrieve a list of Baby Sunshine Grant | householdSize, total_income |
| GET | /household/family/list/yolo?householdSize=2&totalIncome=3000 | Retrieve a list of Yolo GST Grant | householdSize, total_income |
| GET | /household/family/list/schemes?householdSize=2&totalIncome=3000 | Retrieve all grants | householdSize, total_income |
| GET | /household/family/list/scheme?type=yolo,student&householdSize=8&totalIncome=300000 | Retrieve grant or grant(s) where applicable | householdSize, total_income, type={student, family, elder, baby, yolo} |