SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE household;
TRUNCATE TABLE family_member;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO household (housing_type) VALUES ('Condominium');
INSERT INTO household (housing_type) VALUES ('Landed');
INSERT INTO household (housing_type) VALUES ('HDB');
INSERT INTO household (housing_type) VALUES ('HDB');
INSERT INTO household (housing_type) VALUES ('HDB');
#SELECT * FROM `hb-01-hdb`.household;

#
# Family Togetherness scheme
# Households with husband & wife
# Has child(ren) younger than 18 years old.
#
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Gold Fish', 'male', 'married', 'employed', '35000', '1994-01-24', '1');
INSERT INTO family_member (name, gender, marital_status, spouse_id, occupation_type, annual_income, dob, household_id) 
VALUES ('Shark', 'female', 'married', '1', 'employed', '35000', '1994-01-24', '1');
UPDATE family_member SET marital_status = 'married', spouse_id= '2' WHERE id='1';
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('salmon', 'female', 'single', 'student', '0', '2004-01-24', '1');

#
# Student Encouragement Bonus
# Households with children of less than 16 years old.
# Household income of less than $150,000.
#
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Sardine', 'female', 'single', 'student', '0', '2010-01-24', '2');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Pike', 'male', 'single', 'employed', '90000', '1994-01-24', '2');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Swordfish', 'female', 'employed', 'student', '50000', '1994-01-24', '2');

#
# Elder Bonus
# HDB household with family members above the age of 50
#
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Tuna', 'female', 'single', 'student', '80000', '1969-01-24', '3');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Lobster', 'male', 'single', 'employed', '90000', '1969-01-24', '3');

#
# Baby Sunshine Grant
# Household with young children younger than 5
#
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Crab', 'female', 'single', 'student', '80000', '2010-01-24', '4');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Whitefish', 'male', 'single', 'employed', '90000', '1994-01-24', '4');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Seabass', 'female', 'single', 'unemployed', '0', '2019-01-24', '4');

#
# yolo_gst_grant
# HDB households with annual income of less than $100,000.
#

INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Monkfish', 'female', 'single', 'student', '60000', '1994-01-24', '5');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Mullet', 'male', 'single', 'employed', '35000', '1994-01-24', '5');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('Parrotfish', 'female', 'single', 'unemployed', '0', '1994-01-24', '5');

#

SELECT * FROM `hb-01-hdb`.family_member;
SELECT * FROM `hb-01-hdb`.family_member INNER JOIN household on household.id = family_member.household_id;