SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE household;
TRUNCATE TABLE family_member;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO household (housing_type) VALUES ('Condominium');
INSERT INTO household (housing_type) VALUES ('Landed');
INSERT INTO household (housing_type) VALUES ('HDB');
#SELECT * FROM `hb-01-hdb`.household;

INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('jason', 'male', 'married', 'unemployed', '400', '1994-01-24', '3');
INSERT INTO family_member (name, gender, marital_status, spouse_id, occupation_type, annual_income, dob, household_id) 
VALUES ('jiaqi', 'female', 'married', '1', 'unemployed', '500', '1994-01-24', '3');
UPDATE family_member SET marital_status = 'married', spouse_id= '2' WHERE id = 1;
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('jaime', 'female', 'single', 'student', '0', '2016-01-24', '3');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('lobster', 'male', 'single', 'employed', '0', '1956-01-24', '3');

INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('sun', 'female', 'single', 'unemployed', '1000', '1994-01-24', '3');
INSERT INTO family_member (name, gender, marital_status, occupation_type, annual_income, dob, household_id) 
VALUES ('eva', 'female', 'single', 'unemployed', '2000', '1994-01-24', '3');

SELECT * FROM `hb-01-hdb`.family_member;
SELECT * FROM `hb-01-hdb`.family_member INNER JOIN household on household.id = family_member.household_id;