KC Release Version 1.1.1 Patch
------------------------------------

KC database release bundle contains patched database objects (tables, constraints, bootstrap data)
required to launch/execute KRA application

Pre-install system check
------------------------

Edit KC-Release-1_1-1_1_1-Pre-install-check.bat (or .sh) file. set username/password and service name for oracle user.
Execute KC-Release-1_1-1_1_1-Pre-install-check.bat
review files created beginning with install_kc_release-1_1_1-Patch_*.log for items requiring attention before patch is applied.

Patch installation steps
------------------------

Edit KC-Release-1_1-1_1_1-Patch.bat (or .sh) file. Set username/password and service name for oracle user.
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


Execute KC-Release-1_1-1_1_1-Patch.bat to load patched database objects

Errors are listed in
	* KC base object errors are listed in install_kc_release-1_1_1-Patch.log
	* Log files below refer error loading bootstrap LOB data


