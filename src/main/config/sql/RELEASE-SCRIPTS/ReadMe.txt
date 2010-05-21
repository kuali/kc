KC Release Version 2.0
------------------------

KC database release bundle contains all SQL scripts needed to 
install a new schema in a rice bundled mode
with the database objects (tables, constraints, bootstrap data)
required to launch/execute KC application.

KC database install scripts for a rice embedded version 
or upgrade existing KC schema will be released separately.

Installation Steps - Oracle
---------------------------

Installation has been tested with sqlplus and SQL Developer.

Create oracle username of less than 8 characters
Make sure oracle user has following privileges
	* DEFAULT TABLESPACE set to <Users Tablespace> (the intended location where the schema database structures and base bootstrap data are stored).
	* QUOTA UNLIMITED ON <Users Tablespace>
	* CREATE SESSION
	* CREATE SYNONYM
	* CREATE PROCEDURE  
	* CREATE TRIGGER
	* CREATE TABLE  
	* CREATE TYPE  
	* CREATE VIEW 
	* CREATE SEQUENCE
	
Note: a users DEFAULT TABLESPACE is set with the CREATE USER statement or ALTER USER statement. The TABLESPACE should not be the SYSTEM tablespace.

Run: KC_Install.bat oracle new username password DB_Server_Name

NOTE: This will COMPLETELY clear data from any existing KC tables in this schema!


Installation Steps - MySQL
--------------------------

Installation has been tested with mysql client.

Set the following setting in MySQL

max_allowed_packet=1M
transaction-isolation=READ-COMMITTED
lower_case_table_names=1
max_connections=1000

Create MySQL username of less than 8 characters
Create MySQL schema matching username
Make sure MySQL user has following privileges on the schema
	* Select
	* Insert
	* Update
	* Delete
	* Create  
	* Drop
	* Index 
	* Alter
	* Create_view
	* Create_routine
	* Alter_routine
	* Create_tmp_table
	* Lock_tables

Edit KC-Release-2_0-Bundled-Install.sql change kcprd to username

Run: KC_Install.bat mysql new username password

NOTE: This will COMPLETELY clear data from any existing KC tables in this schema!

KC_Install.bat Usage
--------------------------

KC_Install.bat DB_Server new username password DB_server_name
   - DB_Server = Choose one: oracle, mysql
   - new = New install with an empty database schema with bundled rice
   - username = The kc Database schema name to install database scripts to (bundled rice goes here too).
   - password = the password for username
   - DB_server_name = the name used to locate the database server where kc schema is located (Oracle Only)

Review .log files for installation errors

