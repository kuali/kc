KC Release 2.0 Beta
-------------------

KC database release bundle contains all SQL scripts needed to install a new schema (tables, constraints, bootstrap data) 
required to launch/execute KC application.


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

Run the oracle.sql script in sql/oracle directory of the distribution.  All paths in the script are relative, so depending on your tool you may need
to start the script from within that directory for it to function properly.
NOTE: This will COMPLETELY clear data from any existing KC tables in this schema!


Installation Steps - MySQL
--------------------------

Installation has been tested with mysql client.

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
	
Run the mysql.sql script in sql/mysql directory of the distribution.  All paths in the script are relative, so depending on your tool you may need
to start the script from within that directory for it to function properly.
NOTE: This will COMPLETELY clear data from any existing KC tables in this schema!
Example with mysql client:
mysql -u kcpre -D kcpre -p -s < mysql.sql

