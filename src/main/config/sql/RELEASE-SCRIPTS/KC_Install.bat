@echo off
if "%1" == "" goto USAGE
if "%2" == "" goto USAGE
if "%3" == "" goto USAGE
if "%4" == "" goto USAGE
if "%1" == "new" goto new
if "%1" == "1.0" goto v10
if "%1" == "1.1" goto v11
goto usage
:new
cd KRA-RELEASE-1_0-SCRIPT
sqlplus %2/%3@%4 @KRA-Release1_0

sqlldr %2/%3@%4 control=DML/LOB-CTL/EXEMPTION_TYPE.ctl
sqlldr %2/%3@%4 control=DML/LOB-CTL/EN_DOC_HDR_CNTNT_T.ctl
sqlldr %2/%3@%4 control=DML/LOB-CTL/EN_RULE_ATTRIB_T.ctl
sqlldr %2/%3@%4 control=DML/LOB-CTL/FP_MAINTENANCE_DOCUMENT_T.ctl
sqlldr %2/%3@%4 control=DML/LOB-CTL/SPONSOR_FORM_TEMPLATES.ctl
sqlldr %2/%3@%4 control=DML/LOB-CTL/YNQ_EXPLANATION.ctl
move *.log ..
cd ..
:v10
cd KRA-RELEASE-1_1-SCRIPT
sqlplus %2/%3@%4 @KRA-Release-1_1.sql
move *.log ..
cd ..
:v11
cd KC-RELEASE-1_1_1-PATCH-SCRIPT
sqlplus %2/%3@%4 @KCRA-Release-1_1-1_1_1-Patch.sql
move *.log ..
cd ..
Echo review log files for errors during database install.
dir *.log
goto end
:usage
Echo USAGE:
Echo KC_Install.bat Install_version username password DB_server_name
Echo    - Install_Version = Choose one: new, 1.0, 1.1
Echo       - new = New install with an empty database schema
Echo       - 1.0 = upgrading from 1.0 KC version
Echo       - 1.1 = upgrading from 1.1 KC version
Echo    - username = The Database schema name to install database scripts to.
Echo    - password = the password for username
Echo    - DB_server_name = the name used to locate the database server where scripts are stored
:end
