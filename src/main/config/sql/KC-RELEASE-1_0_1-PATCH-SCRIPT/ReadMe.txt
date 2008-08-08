KCRA Release Version 1.0.1 Patch
------------------------------------

KCRA database release bundle contains patched database objects (tables, constraints, bootstrap data)
required to launch/execute KRA application

Edit KCRA-Release-1_0-1_0_1-Patch.bat (or .sh) file. Set username/password and service name for oracle user.
Make sure above oracle user has following privileges
	* CREATE PROCEDURE  
	* CREATE TABLE  
	* CREATE TYPE  
	* CREATE VIEW 
	* CREATE SEQUENCE
	* QUOTA UNLIMITED ON USERS DEFAULT TABLESPACE

Set path to oracle/bin
	* Database structures and base bootstrap data are loaded using SQLPLUS 
	* Bootstrap data for large object columns are loaded using SQLLDR


Execute KCRA-Release-1_0-1_0_1-Patch.bat to load patched database objects

Errors are listed in
	* KCRA base object errors are listed in install_kcra_release-1_0_1-Patch.log
	* Log files below refer error loading bootstrap LOB data


