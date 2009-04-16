KRA Release Version 1.0
------------------------------------

KRA database release bundle contains all database objects (tables, constraints, bootstrap data)
required to launch/execute KRA application

Edit KRA-Release1_0.bat file. Set username/password and service name for oracle user.
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


Execute KRA-Release1_0.bat to load all database objects

Errors are listed in
	* KRA base object errors are listed in install_kra_release-1.log
	* Log files below refer error loading bootstrap LOB data
		- EN_DOC_HDR_CNTNT_T.log
		- EN_RULE_ATTRIB_T.log
		- EXEMPTION_TYPE.log
		- FP_MAINTENANCE_DOCUMENT_T.log
		- SPONSOR_FORM_TEMPLATES.log
		- YNQ_EXPLANATION.log


