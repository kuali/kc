:: This batch file downloads and applies the database schema and data of
:: Kuali Coeus' DBA, UNT, and/or embedded (client, server) databases in
:: an Microsoft Windows environment.
::
:: Requirements:
:: - Oracle SQLPlus which should be supplied with Oracle XE. Make sure sqlplus
:: is in the Windows PATH (i.e. sqlplus can be started from any directory).
:: - WGet (download from http://users.ugent.be/~bpuype/wget/) and set WGET_PROG
:: to point to wget.exe.
:: - 7-Zip (download from http://www.7-zip.org/download.html) and set ZIP_PROG
:: to point to 7z.exe.
::
:: Hint: Running this batch file without arguments displays usage hints.

@ECHO OFF

:: /* Basic configuration */

:: Your local database credentials
SET DBA_USER=KRADEV
SET DBA_PASS=dev174kra
SET UNT_USER=KRAUNT2
SET UNT_PASS=unt2174kra

SET EMB_CLIENT_USER=KCDEV
SET EMB_CLIENT_PASS=KCDEV
SET EMB_SERVER_USER=RICEDEV
SET EMB_SERVER_PASS=RICEDEV

:: URL of SQL scrips
SET DBA_SCRIPT_URL=https://db.kc.kuali.org/userContent/dba/dbadb_oracle.zip
SET UNT_SCRIPT_URL=https://db.kc.kuali.org/userContent/unt/untdb_oracle.zip
SET EMB_CLIENT_SCRIPT_URL=https://db.kc.kuali.org/userContent/embedded/embeddeddb_client.zip
SET EMB_SERVER_SCRIPT_URL=http://db.kc.kuali.org/userContent/embedded/embeddeddb_server.zip

:: External program locations
SET WGET_PROG=C:\cygwin\bin\wget.exe
SET ZIP_PROG=c:\edstorm\utils\7-zip\7z.exe
SET SQLPLUS_CMD=C:\oraclexe\app\oracle\product\10.2.0\server\BIN\sqlplus
:: /* Advanced configuration */

SET DBA_SQL_DIR=dbadb
SET UNT_SQL_DIR=untdb
SET EMB_CLIENT_SQL_DIR=embcdb
SET EMB_SERVER_SQL_DIR=embsdb

SET DBA_CMD=dba
SET UNT_CMD=unt
SET ALL_CMD=all
SET EMB_CMD=emb

SET WGET_OPTS=--no-check-certificate

:: /* The code below should not need to be changed */

SET CD_BASE_DIR=cd /d %~dp0
SET UPDATED=

IF "%1" == "%DBA_CMD%" GOTO DBA
IF "%1" == "%UNT_CMD%" GOTO UNT
IF "%1" == "%ALL_CMD%" GOTO ALL
IF "%1" == "%EMB_CMD%" GOTO EMB

ECHO.
ECHO Invalid argument: %1
ECHO To update the DBA database specify: %0 %DBA_CMD%
ECHO To update the UNT database specify: %0 %UNT_CMD%
ECHO To update both the DBA and UNT database specify: %0 %ALL_CMD%
ECHO To update both the EMBEDDED CLIENT and EMBEDDED SERVER database specify: %0 %EMB_CMD%
ECHO.
GOTO :END

:DBA
set URL=%DBA_SCRIPT_URL%
set SQL_DIR=%DBA_SQL_DIR%
set DB_USER=%DBA_USER%
set DB_PASS=%DBA_PASS%
set SCRIPT_NAME=oracle
set SUBDIR_NAME=oracle
call :UPDATE
GOTO :SUCCESS

:UNT
set URL=%UNT_SCRIPT_URL%
set SQL_DIR=%UNT_SQL_DIR%
set DB_USER=%UNT_USER%
set DB_PASS=%UNT_PASS%
set SCRIPT_NAME=oracle
set SUBDIR_NAME=oracle
call :UPDATE
GOTO :SUCCESS

:ALL
set URL=%DBA_SCRIPT_URL%
set SQL_DIR=%DBA_SQL_DIR%
set DB_USER=%DBA_USER%
set DB_PASS=%DBA_PASS%
set SCRIPT_NAME=oracle
set SUBDIR_NAME=oracle
call :UPDATE

set URL=%UNT_SCRIPT_URL%
set SQL_DIR=%UNT_SQL_DIR%
set DB_USER=%UNT_USER%
set DB_PASS=%UNT_PASS%
set SCRIPT_NAME=oracle
set SUBDIR_NAME=oracle
call :UPDATE
GOTO :SUCCESS

:EMB
set URL=%EMB_CLIENT_SCRIPT_URL%
set SQL_DIR=%EMB_CLIENT_SQL_DIR%
set DB_USER=%EMB_CLIENT_USER%
set DB_PASS=%EMB_CLIENT_PASS%
set SCRIPT_NAME=client
set SUBDIR_NAME=client
call :UPDATE

set URL=%EMB_SERVER_SCRIPT_URL%
set SQL_DIR=%EMB_SERVER_SQL_DIR%
set DB_USER=%EMB_SERVER_USER%
set DB_PASS=%EMB_SERVER_PASS%
set SCRIPT_NAME=server
set SUBDIR_NAME=server
call :UPDATE
GOTO :SUCCESS

::args: URL, SQL_DIR, DB_USER, DB_PASS, SUBDIR_NAME, SCRIPT_NAME
:UPDATE
call :CLEANUP
%WGET_PROG% %WGET_OPTS% -O tmp.zip %URL%
%ZIP_PROG% x tmp.zip -y -o%SQL_DIR%
cd %SQL_DIR%\sql\%SUBDIR_NAME%\
%SQLPLUS_CMD% -S %DB_USER%/%DB_PASS% @%SCRIPT_NAME%
%CD_BASE_DIR%
call :CLEANUP

SET UPDATED=%DB_USER% %UPDATED%

GOTO :EOF

::args: SQL_DIR
:CLEANUP
del /F /Q tmp.zip
rmdir /s /q %SQL_DIR%
GOTO :EOF

:SUCCESS
ECHO.
ECHO [%UPDATED%] has been updated
ECHO.
GOTO :END

:END

