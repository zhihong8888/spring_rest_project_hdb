DROP SCHEMA IF EXISTS `hb-01-hdb`;

CREATE SCHEMA `hb-01-hdb`;

use `hb-01-hdb`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `household`;

CREATE TABLE `household` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `housing_type` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `family_member`;

CREATE TABLE `family_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `gender` ENUM('male', 'female') NOT NULL,
  `marital_status` varchar(128) DEFAULT NULL,
  `spouse_id` int(11) NULL,
  `occupation_type` ENUM('unemployed', 'student', 'employed') NOT NULL,
  `annual_income` int(11) DEFAULT NULL,
  `dob` DATE NOT NULL,
  `household_id` int(11) NOT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `NAME_UNIQUE` (`name`),
  
  KEY `FK_HOUSEHOLD_idx` (`household_id`),
  KEY `FK_SPOUSE_idx` (`spouse_id`),
  
  CONSTRAINT `FK_SPOUSE` FOREIGN KEY (`spouse_id`) REFERENCES `family_member` (`id`),
  CONSTRAINT `FK_HOUSEHOLD` FOREIGN KEY (`household_id`) REFERENCES `household` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;

