KC Release Version 3.1 M2
-------------------------

KC database release contains all SQL scripts needed to 
install a new schema or upgrade a KC 3.0.x database using 
rice bundled or embedded mode with the database objects 
(tables, constraints, bootstrap data) for the KC application.
 
If installing in an embedded rice environment you will be prompted 
to install/upgrade rice.  If your Rice server has been upgraded 
already you will answer no to the question. 

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

Run: KC_Install.bat (or KC_Install.sh)

NOTE: A New install will COMPLETELY clear data from any existing KC tables in this schema!


Installation Steps - MySQL
--------------------------

Installation has been tested with mysql client.

Set the following settings in MySQL

max_allowed_packet=1M
transaction-isolation=READ-COMMITTED
lower_case_table_names=1
max_connections=1000

Create MySQL username of less than 8 characters
Create MySQL schema matching username with the default character set of UTF8, if a different character set is desired, 
the ddl scripts will need to be updated to the new character set.
  
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

Run: KC_Install.bat (or KC_Install.sh)

NOTE: Be sure the schema is empty before installation.

KC_Install.bat Usage
--------------------------

KC_Install.bat

You will be prompted for the following:
   - Rice Mode = Choose one: Bundle, Embed
   - DB_Server = Choose one: oracle, mysql
   - Version = Choose one: New, 2.0
   - username = The kc Database schema name to install database scripts to (bundled rice goes here too).
   - password = the password for username
   - DB_server_name = the tns name used to connect to kc database (Oracle only).
   If embedded mode selected the following will be asked
   - Rice Username = the rice database schema name to install rice scripts to (embedded rice).
   - rice Password = password for rice username
   - Rice DB Server name = the tns name used to connect to rice database (Oracle Only).
   - Do you wish to Install/Upgrade your Embedded Rice Server (Don't perform if your server was upgraded separately)

Review .log files for installation errors

Install Demonstration Data
--------------------------

The demonstration data can be found in the db_scripts/<Release-Version>/demo-data/<Database> folder.  Run the KC_DEMO.sql and KR_DEMO.sql
in your Kuali Coeus and Kuali Rice database schemas respectively.  If you are running in bundled mode they will be run 
in the same schema. 