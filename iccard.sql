-- 创建数据库
drop database if exists iccard;
create database iccard DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use iccard;

-- 创建用户表
create table user (
	id  		int(11) 		primary key NOT NULL auto_increment,
	userName 	varchar(20)		NOT NULL,
	password	varchar(32)		NOT NULL,
	workId		varchar(10)		NOT NULL,
	sex			varchar(6)		NOT NULL,
	phoneNum	varchar(20)		NOT NULL,
	email		varchar(20)		NOT NULL DEFAULT '',
	name 		varchar(20)		NOT NULL
);

-- 创建安装任务表
create table installation (
	id  			int(11) 		primary key NOT NULL auto_increment,
	address			varchar(40)		NOT NULL,
	userName 		varchar(20)		NOT NULL,
	postDate		datetime		NOT NULL,
	completeDate	datetime,
	isComplete		bit(1)			NOT NULL DEFAULT 0,
	uploadFlag		bit(1)			NOT NULL DEFAULT 0,
	barCode			varchar(20)		NOT NULL DEFAULT '',
	indication		int(4)		 	NOT NULL DEFAULT 0,
	customerId		int(11)			NOT NULL DEFAULT 0
);


-- 创建登录session表
create table loginsession (
	id  			int(11) 		primary key NOT NULL auto_increment,
	JSESSIONID		varchar(48)		NOT NULL,
	userName		varchar(20)		NOT NULL,
	ticket			varchar(32)		NOT NULL
);

-- 创建维修表
create table repair (
	id  			int(11) 		primary key NOT NULL auto_increment,
	type			varchar(4)		NOT NULL,
	description		varchar(255)	NOT NULL DEFAULT '',
	userName 		varchar(20)		NOT NULL,
	oldBarCode		varchar(20)		NOT NULL DEFAULT '',
	oldIndication 	int(4)			NOT	NULL DEFAULT 0,
	newBarCode		varchar(20)		NOT NULL DEFAULT '',
	newIndication	int(4)			NOT NULL DEFAULT 0,
	postDate		datetime		NOT NULL,
	completeDate	datetime,
	address			varchar(255)	NOT NULL DEFAULT '',
	isUpdate		bit(1)			NOT NULL DEFAULT 0,
	uploadFlag		bit(1)			NOT NULL DEFAULT 0,
	isComplete		bit(1) 			NOT NULL DEFAULT 0

);


-- 插入测试用户
INSERT INTO user 
(userName, password, workId, sex, phoneNum, email, name) VALUES 
('root', '63a9f0ea7bb98050796b649e85481845', '001', '男', '15927430669', 'admin@gmail.com', '系统管理员'),
('admin', '21232f297a57a5a743894a0e4a801fc3', '002', '男', '15227430669', 'test@163.com', '管理员' );

-- 插入测试维修任务
INSERT INTO installation
(address, userName, postDate) VALUES
('湖北省武汉市武昌区友谊大道华城广场1号', 'root', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和友谊道华城广场2号', 'root', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和友谊道华城广场3号', 'root', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和友谊道华城广场4号', 'root', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和平大道联盟小区1号', 'admin', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和平大道联盟小区2号', 'admin', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和平大道联盟小区3号', 'admin', '2014-11-17 14:37:06'),
('湖北省武汉市武昌区和平大道联盟小区4号', 'admin', '2014-11-17 14:37:06');


-- 插入测试维修任务
INSERT INTO repair
(type, userName, postDate, address) VALUES
('001', 'root', '2014-11-14 14:37:06', '湖北省武汉市武昌区友谊大道华城广场1号'),
('002', 'root', '2014-11-14 14:37:06', '湖北省武汉市武昌区友谊大道华城广场2号'),
('001', 'admin', '2014-11-14 14:37:06', '湖北省武汉市武昌区友谊大道华城广场3号'),
('004', 'admin', '2014-11-14 14:37:06', '湖北省武汉市武昌区友谊大道华城广场4号');


