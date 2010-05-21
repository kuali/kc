rem @echo off
mkdir LOGS
if "%1" == "" goto USAGE
if "%1" == "mysql" goto MYSQL
if "%1" == "oracle" goto ORACLE
:ORACLE
if "%2" == "" goto USAGE
if "%3" == "" goto USAGE
if "%4" == "" goto USAGE
if "%5" == "" goto USAGE
if "%2" == "new" goto newora
if "%2" == "newemb" goto USAGE
if "%2" == "1.0" goto USAGE
if "%2" == "1.1" goto USAGE
if "%2" == "1.1.1" goto USAGE
goto usage
:newora
cd KC-RELEASE-2_0-SCRIPT
sqlplus "%3"/"%4"@"%5" < KC-Release-2_0-Bundled-Oracle-Install.sql
move *.log ../LOGS
cd ../LOGS
Echo Review log files for errors during database install.
dir *.log
goto end
:MYSQL
if "%2" == "" goto USAGE
if "%3" == "" goto USAGE
if "%4" == "" goto USAGE
if "%2" == "new" goto newmysql
goto usage
:newmysql
cd KC-RELEASE-2_0-SCRIPT
mysql -u %3 -p%4 -D %3 -s < KC-Release-2_0-Bundled-Install.sql > KC-Release-2_0-Bundled-MySql-Install.log
move *.log ../LOGS
cd ../LOGS
Echo Review log files for errors during database install.
dir *.log
goto end
REM :newemb
REM if "%5" == "" goto USAGE
REM if "%6" == "" goto USAGE
REM if "%7" == "" goto USAGE
REM cd KC-RELEASE-2_0-SCRIPT
REM sqlplus %5/%6@%7 @KR-Release-1_0_2-EmbeddedServer.sql
REM sqlplus %2/%3@%4 @KR-Release-1_0_2-EmbeddedClient.sql
REM sqlplus %2/%3@%4 @KC-Release-2_0-Full-Install.sql
REM move *.log ../LOGS
REM cd ../LOGS
REM Echo Review log files for errors during database install.
REM dir *.log
REM goto end
REM 
REM :v10
REM cd KRA-RELEASE-1_1-SCRIPT
REM sqlplus %2/%3@%4 @KRA-Release1_1.sql
REM move *.log ../LOGS
REM cd ..
REM 
REM :v11
REM cd KC-RELEASE-1_1_1-PATCH-SCRIPT
REM sqlplus %2/%3@%4 @KCRA-Release-1_1-1_1_1-Patch.sql
REM move *.log ../LOGS
REM cd ..
REM :v111
REM cd KC-RELEASE-2_0-SCRIPT
REM sqlplus %2/%3@%4 @KC-Release-1_1_1-2_0-Patch.sql
REM move *.log ../LOGS
REM cd ../LOGS
REM Echo Review log files for errors during database install.
REM dir *.log
REM goto end
:usage
Echo USAGE:
Echo KC_Install.bat DB_Server new username password DB_server_name
REM Echo    - Install_Version = Choose one: new, 1.0, 1.1, 1.1.1
Echo    - DB_Server = Choose one: oracle, mysql
Echo    - new = New install with an empty database schema with bundled rice
REM Echo       - newemb = New install with an empty database schema with embedded rice
REM Echo       - 1.0 = upgrading from 1.0 KC version
REM Echo       - 1.1 = upgrading from 1.1 KC version
Echo    - username = The kc Database schema name to install database scripts to (bundled rice goes here too).
Echo    - password = the password for username
Echo    - DB_server_name = the name used to locate the database server where kc schema is located (Oracle only)
REM Echo    - riceusername = The rice Database schema name to install embedded rice database scripts to.
REM Echo    - ricepassword = the password for riceusername
REM Echo    - riceDB_server_name = the name used to locate the database server where rice schema is located
:end
