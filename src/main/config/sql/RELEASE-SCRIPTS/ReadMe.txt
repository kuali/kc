KC Release Version 1.1.1
------------------------

KC database release bundle contains all SQL scripts needed to install a new schema 
or upgrade existing KC schema with the database objects (tables, constraints, bootstrap data)
required to launch/execute KRA application.

Pre-install system check
------------------------------------------

The pre-install system check should only be run when performing an upgrade from previous version of KC. You will receive a "Table Not Found" error 
when running against an empty schema.

Run appropriate script (*.bat or *.sh):

KC_Pre-Install_Check.bat username password DB_server_name

    - username = The Database schema name to run the Pre-Install database scripts against.
    - password = the password for username
    - DB_server_name = the name used to locate the database server

review files created beginning with install_kc_release-1_1_1-Patch_*.log for items requiring attention before installation scripts are applied.

Installation Steps
------------------

Ensure system path includes the <oracle home>/bin directory
	* Database structures and base bootstrap data are loaded using SQLPLUS 
	* Bootstrap data for large object columns are loaded using SQLLDR

Ensure oracle username is less than 8 characters
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

Run appropriate script (*.bat or *.sh):

KC_Install.bat Install_version username password DB_server_name

    - Install_Version = Choose one: new, 1.0, 1.1
       - new = New install with an empty database schema
       - 1.0 = upgrading from 1.0 KC version
       - 1.1 = upgrading from 1.1 KC version
    - username = The Database schema name to install database scripts to.
    - password = the password for username
    - DB_server_name = the name used to locate the database server

Review .log files for installation errors

