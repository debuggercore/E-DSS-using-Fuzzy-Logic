-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.19-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for electivecourse
DROP DATABASE IF EXISTS `electivecourse`;
CREATE DATABASE IF NOT EXISTS `electivecourse` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `electivecourse`;


-- Dumping structure for table electivecourse.datalocator
DROP TABLE IF EXISTS `datalocator`;
CREATE TABLE IF NOT EXISTS `datalocator` (
  `LOCATORNAME` varchar(500) NOT NULL,
  `LOCATORVALUE` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table electivecourse.datalocator: ~1 rows (approximately)
DELETE FROM `datalocator`;
/*!40000 ALTER TABLE `datalocator` DISABLE KEYS */;
INSERT INTO `datalocator` (`LOCATORNAME`, `LOCATORVALUE`) VALUES
	('FILEPATH', 'D:\\2017_PROJECTS_CODE\\IGEEKS\\ELECTIVECOURSE_SUGGESTION\\SUBJECTS\\');
/*!40000 ALTER TABLE `datalocator` ENABLE KEYS */;


-- Dumping structure for table electivecourse.filenames
DROP TABLE IF EXISTS `filenames`;
CREATE TABLE IF NOT EXISTS `filenames` (
  `FILEID` int(11) NOT NULL auto_increment,
  `FILENAME` varchar(255) NOT NULL,
  `ELECTIVENAME` varchar(255) NOT NULL,
  PRIMARY KEY  (`FILEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table electivecourse.filenames: ~7 rows (approximately)
DELETE FROM `filenames`;
/*!40000 ALTER TABLE `filenames` DISABLE KEYS */;
INSERT INTO `filenames` (`FILEID`, `FILENAME`, `ELECTIVENAME`) VALUES
	(20, 'subjects.arff', 'ANGULAR'),
	(21, 'angular1.fcl', 'angular1'),
	(22, 'angular2.fcl', 'angular2'),
	(23, 'linearalgebra1.fcl', 'linearalgebra1'),
	(24, 'linearalgebra2.fcl', 'linearalgebra2'),
	(25, 'management1.fcl', 'management1'),
	(26, 'management2.fcl', 'management2');
/*!40000 ALTER TABLE `filenames` ENABLE KEYS */;


-- Dumping structure for table electivecourse.login
DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `FIRSTNAME` varchar(500) NOT NULL,
  `LASTNAME` varchar(500) NOT NULL,
  `PASSWORD` varchar(500) NOT NULL,
  `EMAIL` varchar(500) NOT NULL,
  `USERID` varchar(100) NOT NULL,
  `LOGINTYPE` int(11) NOT NULL default '1',
  `ANSWER1` longtext,
  `ANSWER2` longtext,
  `ANSWER3` longtext,
  `ANSWER4` longtext,
  `AGE` int(11) default NULL,
  `SEX` int(11) default NULL,
  `DOB` longtext,
  `TIMESTAMP` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table electivecourse.login: ~5 rows (approximately)
DELETE FROM `login`;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`FIRSTNAME`, `LASTNAME`, `PASSWORD`, `EMAIL`, `USERID`, `LOGINTYPE`, `ANSWER1`, `ANSWER2`, `ANSWER3`, `ANSWER4`, `AGE`, `SEX`, `DOB`, `TIMESTAMP`) VALUES
	('virat', '12', 'admin123', 'virat@gmail.com', 'admin', 1, NULL, NULL, NULL, NULL, 0, 1, '2017-02-07', '2017-02-12 00:12:22'),
	('sachin', '12', 'pass123', 'sachin@gmail.com', 'sachin12', 4, NULL, NULL, NULL, NULL, 0, 1, '2017-02-07', '2017-02-12 00:12:22'),
	('test', '12', 'pass123', 'test12@gmail.com', 'test123', 4, NULL, NULL, NULL, NULL, 0, 1, '1986-02-05', '2017-03-15 23:47:30'),
	('virat', 'kohli', 'pass123', 'virat@gmail.com', 'virat123', 4, NULL, NULL, NULL, NULL, 0, 1, '1986-12-01', '2017-02-12 09:55:03'),
	('virat', 'anu', 'pass1234', 'virat124@gmail.com', 'virat123anu', 4, NULL, NULL, NULL, NULL, 0, 1, '1986-04-15', '2017-03-12 19:52:47');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


-- Dumping structure for table electivecourse.predictionvalue
DROP TABLE IF EXISTS `predictionvalue`;
CREATE TABLE IF NOT EXISTS `predictionvalue` (
  `PREDICTIONID` int(11) NOT NULL auto_increment,
  `SUBJECT1NAME` varchar(255) NOT NULL,
  `SUBJECT2NAME` varchar(255) NOT NULL,
  `SUBJECT3NAME` varchar(255) NOT NULL,
  `SUBJECT4NAME` varchar(255) NOT NULL,
  `SUBJECT1` varchar(255) NOT NULL,
  `SUBJECT2` varchar(255) NOT NULL,
  `SUBJECT3` varchar(255) NOT NULL,
  `SUBJECT4` varchar(255) NOT NULL,
  `ELECTIVECOURSE` varchar(255) NOT NULL,
  `PREDICTEDVALUE` varchar(255) NOT NULL,
  `USERID` varchar(255) NOT NULL,
  `COG` varchar(255) default NULL,
  PRIMARY KEY  (`PREDICTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table electivecourse.predictionvalue: ~27 rows (approximately)
DELETE FROM `predictionvalue`;
/*!40000 ALTER TABLE `predictionvalue` DISABLE KEYS */;
INSERT INTO `predictionvalue` (`PREDICTIONID`, `SUBJECT1NAME`, `SUBJECT2NAME`, `SUBJECT3NAME`, `SUBJECT4NAME`, `SUBJECT1`, `SUBJECT2`, `SUBJECT3`, `SUBJECT4`, `ELECTIVECOURSE`, `PREDICTEDVALUE`, `USERID`, `COG`) VALUES
	(1, 'HTML5', 'MATHS', 'COMPUTERORGANIZATION', 'JAVA', '5.4', '6', '7', '1.4', 'ANGULAR', 'NO', 'virat123', ''),
	(2, 'MATHS', 'HTML5', 'JAVA', 'COMPUTERORGANIZATION', '5.1', '3.5', '1.4', '0.2', 'ANGULAR', 'YES', 'sachin12', ''),
	(3, 'MATHS', 'HTML5', 'JAVA', 'COMPUTERORGANIZATION', '5.1', '3.5', '1.4', '0.2', 'ANGULAR', 'YES', 'sachin12', ''),
	(4, 'maths', 'html5', 'computerorganization', 'java', '10', '65', '85', '85', 'ANGULAR', 'LOW', 'virat123', NULL),
	(5, 'maths', 'html5', 'computerorganization', 'java', '10', '90', '10', '88', 'ANGULAR', 'MEDIUM', 'virat123', NULL),
	(6, 'maths', 'html5', 'computerorganization', 'java', '10', '78', '89', '97', 'ANGULAR', 'MEDIUM', 'virat123', NULL),
	(7, 'maths', 'html5', 'computerorganization', 'java', '10', '96', '17', '98', 'ANGULAR', 'HIGH', 'virat123', NULL),
	(8, 'maths', 'html5', 'computerorganization', 'java', '10', '67', '8', '90', 'ANGULAR', 'MEDIUM', 'virat123', NULL),
	(9, 'diffrentialequations', 'html5', 'computerorganization', 'engineeringmath1', '10', '20', '15', '20', 'linearalgebra', 'LOW', 'virat123', NULL),
	(10, 'maths', 'html5', 'marketing', 'java', '96', '10', '98', '15', 'management', 'HIGH', 'virat123', NULL),
	(11, 'maths', 'html5', 'computerorganization', 'java', '10', '85', '90', '98', 'ANGULAR', 'HIGH', 'virat123', NULL),
	(12, 'maths', 'html5', 'computerorganization', 'java', '10', '70', '15', '90', 'ANGULAR', 'MEDIUM', 'virat123', '68.89374324149095'),
	(13, 'diffrentialequations', 'html5', 'computerorganization', 'engineeringmath1', '89', '10', '14', '97', 'linearalgebra', 'HIGH', 'virat123', '85.08241513811885'),
	(14, 'maths', 'html5', 'marketing', 'java', '10', '15', '10', '45', 'management', 'LOW', 'virat123', '17.68815711436056'),
	(15, 'MATHS', 'HTML5', 'COMPUTERORGANIZATION', 'JAVA', '5.1', '3.4', '5.7', '6.7', 'ANGULAR', 'NO', 'virat123anu', NULL),
	(16, 'maths', 'html5', 'computerorganization', 'java', '35', '89', '50', '90', 'ANGULAR', 'HIGH', 'virat123anu', '85.08462206464031'),
	(17, 'diffrentialequations', 'html5', 'computerorganization', 'engineeringmath1', '15', '90', '78', '54', 'linearalgebra', 'MEDIUM', 'virat123anu', '51.37839784736127'),
	(18, 'maths', 'html5', 'marketing', 'java', '70', '10', '78', '79', 'management', 'MEDIUM', 'virat123anu', '68.89599495914013'),
	(19, 'MATHS', 'HTML5', 'JAVA', 'COMPUTERORGANIZATION', '5.1', '3.5', '1.4', '0.2', 'ANGULAR', 'YES', 'virat123anu', NULL),
	(20, 'maths', 'html5', 'computerorganization', 'java', '10', '85', '78', '85', 'ANGULAR', 'HIGH', 'virat123anu', '85.07581021836782'),
	(21, 'diffrentialequations', 'html5', 'computerorganization', 'engineeringmath1', '95', '95', '90', '80', 'linearalgebra', 'HIGH', 'virat123anu', '85.07805078678669'),
	(22, 'maths', 'html5', 'marketing', 'java', '97', '67', '10', '90', 'management', 'MEDIUM', 'virat123anu', '51.38983514213952'),
	(23, 'HTML5', 'MATHS', 'COMPUTERORGANIZATION', 'JAVA', '5.1', '2.5', '3.5', '6.5', 'ANGULAR', 'NO', 'virat123anu', NULL),
	(24, 'maths', 'html5', 'computerorganization', 'java', '25', '96', '57', '95', 'ANGULAR', 'HIGH', 'virat123anu', '85.08534234691577'),
	(25, 'diffrentialequations', 'html5', 'computerorganization', 'engineeringmath1', '26', '70', '85', '35', 'linearalgebra', 'LOW', 'virat123anu', '17.748006410410717'),
	(26, 'maths', 'html5', 'marketing', 'java', '87', '23', '89', '78', 'management', 'HIGH', 'virat123anu', '85.0739224707116'),
	(27, 'MATHS', 'HTML5', 'COMPUTERORGANIZATION', 'JAVA', '10', '95', '98', '100', 'ANGULAR', 'YES', 'test123', NULL);
/*!40000 ALTER TABLE `predictionvalue` ENABLE KEYS */;


-- Dumping structure for table electivecourse.ranking
DROP TABLE IF EXISTS `ranking`;
CREATE TABLE IF NOT EXISTS `ranking` (
  `ELECTIVENAME` varchar(255) NOT NULL,
  `COG` double NOT NULL,
  `USERID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table electivecourse.ranking: ~6 rows (approximately)
DELETE FROM `ranking`;
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
INSERT INTO `ranking` (`ELECTIVENAME`, `COG`, `USERID`) VALUES
	('management', 17.6881571143606, 'virat123'),
	('linearalgebra', 85.0824151381188, 'virat123'),
	('ANGULAR', 68.893743241491, 'virat123'),
	('management', 85.0739224707116, 'virat123anu'),
	('linearalgebra', 17.7480064104107, 'virat123anu'),
	('ANGULAR', 85.0853423469158, 'virat123anu');
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
