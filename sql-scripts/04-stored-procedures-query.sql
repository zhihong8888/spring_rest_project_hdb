#SELECT * FROM `hb-01-hdb`.household;

#
### select age from family_member
#
# SELECT *, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(), dob)),'%Y') AS age 
# FROM family_member;

#
### select hosing type hdb
#
# select f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
# from family_member f join household h  on f.household_id = h.id where h.housing_type = 'HDB';

#
### select husband and wife
#
# select f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id from family_member f
# join family_member b
# on f.id = b.spouse_id;

#
# Family Togetherness
# Households with husband & wife
# Has child(ren) younger than 18 years old
#
DROP procedure IF EXISTS family_togetherness;
DELIMITER $$
CREATE PROCEDURE family_togetherness(
	householdSize int,
    totalIncome int
) 
BEGIN
	SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
	FROM family_member f JOIN household h  ON f.household_id = h.id WHERE f.household_id in (
    
		SELECT f.household_id FROM family_member f
		JOIN family_member b
		ON f.id = b.spouse_id WHERE f.household_id in (
        
            SELECT  f.household_id FROM (
				SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(), dob)),'%Y') AS age 
				FROM family_member f JOIN household h ON f.household_id = h.id 
			) AS f
			WHERE age < 18
			GROUP BY f.household_id
			HAVING 
				sum(f.annual_income) < 150000
			AND
				(householdSize IS NULL OR count(f.id) <= householdSize)
			AND
				(totalIncome IS NULL OR sum(f.annual_income) <= totalIncome)
		)
	);
END 
$$ DELIMITER ;
call family_togetherness(null,null);

#
# Student Encouragement Bonus
# Households with children of less than 16 years old.
# Household income of less than $150,000.
#
DROP procedure IF EXISTS student_encouragement_bonus;
DELIMITER $$
CREATE PROCEDURE student_encouragement_bonus(
	householdSize int,
    totalIncome int
) 
BEGIN
	SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
	FROM family_member f JOIN household h  ON f.household_id = h.id WHERE f.household_id in (
		SELECT  f.household_id FROM (
			SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(), dob)),'%Y') AS age 
			FROM family_member f JOIN household h ON f.household_id = h.id 
		) AS f
		WHERE age < 16
		GROUP BY f.household_id
		HAVING 
			sum(f.annual_income) < 150000
        AND
			(householdSize IS NULL OR count(f.id) <= householdSize)
		AND
			(totalIncome IS NULL OR sum(f.annual_income) <= totalIncome)
	);
END 
$$ DELIMITER ;
call student_encouragement_bonus(null,null);


#
# Elder Bonus
# HDB household with family members above the age of 50
#
DROP procedure IF EXISTS elder_bonnus;
DELIMITER $$
CREATE PROCEDURE elder_bonnus(
	householdSize int,
    totalIncome int
) 
BEGIN
	SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
	FROM family_member f JOIN household h  ON f.household_id = h.id WHERE f.household_id in (
		SELECT  f.household_id FROM (
			SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(), dob)),'%Y') AS age 
			FROM family_member f JOIN household h ON f.household_id = h.id WHERE h.housing_type = 'HDB'
		) AS f
		WHERE age > 50
		GROUP BY f.household_id
		HAVING 
			(householdSize IS NULL OR count(f.id) <= householdSize)
		AND
			(totalIncome IS NULL OR sum(f.annual_income) <= totalIncome)
	);
END 
$$ DELIMITER ;
call elder_bonnus(null,null);

#
# Baby Sunshine Grant
# Household with young children younger than 5
#
DROP procedure IF EXISTS baby_sunshine_grant;
DELIMITER $$
CREATE PROCEDURE baby_sunshine_grant(
	householdSize int,
    totalIncome int
) 
BEGIN
	SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
	FROM family_member f JOIN household h  ON f.household_id = h.id WHERE f.household_id in (
		SELECT  f.household_id FROM (
			SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(), dob)),'%Y') AS age 
			FROM family_member f JOIN household h ON f.household_id = h.id
		) AS f
		WHERE age < 5
		GROUP BY f.household_id
		HAVING 
			(householdSize IS NULL OR count(f.id) <= householdSize)
		AND
			(totalIncome IS NULL OR sum(f.annual_income) <= totalIncome)
	);
END 
$$ DELIMITER ;
call baby_sunshine_grant(null,null);

#
# yolo_gst_grant
# HDB households with annual income of less than $100,000.
#
DROP procedure IF EXISTS yolo_gst_grant;
DELIMITER $$
CREATE PROCEDURE yolo_gst_grant(
	householdSize int,
    totalIncome int
) 
BEGIN
	SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
	FROM family_member f JOIN household h  ON f.household_id = h.id WHERE f.household_id in (
		SELECT f.household_id from (
			SELECT f.id, f.name, f.gender, f.marital_status, f.spouse_id, f.occupation_type, f.annual_income, f.dob, f.household_id, h.housing_type
			FROM family_member f JOIN household h  ON f.household_id = h.id WHERE h.housing_type = 'HDB'
		) as f
		GROUP BY f.household_id
		HAVING 
			sum(f.annual_income) < 100000
		AND 
			(householdSize IS NULL OR count(f.id) <= householdSize)
		AND
			(totalIncome IS NULL OR sum(f.annual_income) <= totalIncome)
    );
END 
$$ DELIMITER ;
call yolo_gst_grant(null,null);


