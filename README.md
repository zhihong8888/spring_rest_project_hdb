# Building a RESTful Web Service with Spring Boot Application

![Alt text](/notes/applicationArchitecture.png?raw=true "Title")

Project Template Generated from https://start.spring.io/
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
- Ecplise IDE
- Apache Maven

Hibernate One-to-Many Bi-Directional mapping
- A household can have many family members (one to many)
- Many family members can have only 1 household (Many to one)

- Assumption: 
-- Each family member can only have 1 house hold, we will not deal with many to many mapping.
- Requirements 
If you delete a household, do not delete the family members (No cascade delete).
If you delete a family member, do not delete the household.