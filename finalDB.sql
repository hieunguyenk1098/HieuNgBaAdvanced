-- Drop the database if it already exists
DROP DATABASE IF EXISTS FinalExam;
-- Create database
CREATE DATABASE IF NOT EXISTS FinalExam;
USE FinalExam;

-- Create table Department
DROP TABLE IF EXISTS 	`Department`;
CREATE TABLE IF NOT EXISTS `Department` (
	id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` 					NVARCHAR(50) NOT NULL UNIQUE KEY,
    total_member			INT	UNSIGNED,
    `type`					ENUM('Dev','Test','ScrumMaster','PM') NOT NULL,
    created_date			DATETIME DEFAULT NOW()
);

-- create table: Account
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username				VARCHAR(50) NOT NULL UNIQUE KEY,
	`password` 				VARCHAR(800) NOT NULL,
    first_name				NVARCHAR(50) NOT NULL,
    last_name				NVARCHAR(50) NOT NULL,
    `role` 					ENUM('ADMIN','EMPLOYEE','MANAGER') NOT NULL DEFAULT 'EMPLOYEE',
    department_id 			INT UNSIGNED,
    FOREIGN KEY(department_id) REFERENCES Department(id) ON DELETE SET NULL
);

-- =============================================
-- INSERT DATA 
-- =============================================
-- Add data Department
INSERT INTO Department(	`name`, 		total_member, 	`type`, 		created_date) 
VALUES
						(N'Marketing'	, 		1,		'Dev', 			'2020-03-05'),
						(N'Sale'		, 		2,		'Test', 		'2020-03-05'),
						(N'Bảo vệ'		, 		3,		'ScrumMaster', 	'2020-03-07'),
						(N'Nhân sự'		, 		4,		'PM', 			'2020-03-08'),
						(N'Kỹ thuật'	, 		5,		'Dev', 			'2020-03-10'),
						(N'Tài chính'	, 		6,		'ScrumMaster', 	NOW()		),
						(N'Phó giám đốc', 		7,		'PM', 			NOW()		),
						(N'Giám đốc'	, 		8,		'Test', 		'2020-04-07'),
						(N'Thư kí'		, 		9,		'PM', 			'2020-04-07'),
						(N'Bán hàng'	, 		1,		'Dev', 			'2020-04-09');
                    
-- Add data Account
-- Password: 123456
INSERT INTO `Account`(	username		,						`password`									,	first_name	,	last_name		,		`role`		,	department_id	)
VALUES 				(	'dangblack'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen Hai'	,	'Dang'		,		'ADMIN'		,		'5'			),
					(	'quanganh'		,	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen Quang'	,	'Anh'		,		'MANAGER'	,		'1'			),
                    (	'vanchien'		,	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Tran Van'		,	'Chien'		,		'ADMIN'		,		'1'			),
                    (	'cocoduongqua'	,	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen Co'		,	'Co'		,		'EMPLOYEE'	,		'1'			),
                    (	'doccocaubai'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen Doc'	,	'Co'		,		'ADMIN'		,		'2'			),
                    (	'khabanh'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Phan Kha'		,	'Bang'		,		'EMPLOYEE'	,		'2'			),
                    (	'huanhoahong'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Tran Van'		,	'Huan'		,		'ADMIN'		,		'2'			),
                    (	'tungnui'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Nguyen Tung'	,	'Nui'		,		'MANAGER'	,		'8'			),
                    (	'duongghuu'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Phan Duong'	,	'Huu'		,		'ADMIN'		,		'9'			),
                    (	'vtiaccademy'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'Tran'			,	'Academy'	,		'MANAGER'	,		'10'		);