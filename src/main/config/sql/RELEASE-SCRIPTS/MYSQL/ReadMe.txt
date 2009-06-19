KC Release Version 2.0 New MySQL Install
----------------------------------------

KC database release bundle contains all SQL scripts needed to install a new schema 
with the database objects (tables, constraints, bootstrap data)
required to launch/execute KRA application.

Installation Steps
------------------

Ensure system path includes the <MySQL home>/bin directory
	* Database structures and base bootstrap data are loaded using MYSQL 
	* Bootstrap data for large object columns are loaded using ????

Ensure MySQL username is less than 8 characters
Make sure MySQL user has following privileges
?????
	
Run appropriate script (*.bat or *.sh):

KC_Install.bat Install_version username password DB_server_name

    - Install_Version = new
       - new = New 2.0 install into an empty database schema
    - username = The Database schema name to install database scripts to.
    - password = the password for username
    - DB_server_name = the name used to locate the database server

Review .log files for installation errors

