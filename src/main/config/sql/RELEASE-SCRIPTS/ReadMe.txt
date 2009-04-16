KC Release Version 1.1.1
------------------------

KC database release bundle contains all SQL scripts needed to install a new schema 
or upgrade existing KC schema with the database objects (tables, constraints, bootstrap data)
required to launch/execute KRA application

Pre-install system check for Upgrades Only
------------------------------------------

Run appropriate script (*.bat or *.sh):

KC_Pre-Install_Check.bat username password DB_server_name

    - username = The Database schema name to install database scripts to.
    - password = the password for username
    - DB_server_name = the name used to locate the database server where scripts are stored

review files created beginning with install_kc_release-1_1_1-Patch_*.log for items requiring attention before patch is applied.

Patch installation steps
------------------------

Ensure oracle username is less than 8 characters
Make sure oracle user has following privileges
	* CREATE PROCEDURE  
	* CREATE TABLE  
	* CREATE TYPE  
	* CREATE VIEW 
	* CREATE SEQUENCE
	* QUOTA UNLIMITED ON USERS DEFAULT TABLESPACE

Set path to oracle/bin
	* Database structures and base bootstrap data are loaded using SQLPLUS 
	* Bootstrap data for large object columns are loaded using SQLLDR

Run appropriate script (*.bat or *.sh):

KC_Install.bat Install_version username password DB_server_name

    - Install_Version = Choose one: new, 1.0, 1.1
       - new = New install with an empty database schema
       - 1.0 = upgrading from 1.0 KC version
       - 1.1 = upgrading from 1.1 KC version
    - username = The Database schema name to install database scripts to.
    - password = the password for username
    - DB_server_name = the name used to locate the database server where scripts are stored

Review .log files for installation errors

