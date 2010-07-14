:: This batch file downloads and applies the database schema and data of
:: Kuali Coeus' DBA, UNT, and/or embedded (client, server) databases in
:: an Microsoft Windows environment.
::
:: Requirements:
:: - Oracle (download from http://www.oracle.com/technology/software/products/database/xe/index.html) and set SQLPLUS_PROG
:: to point to sqlplus.exe.
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
SET SCRIPT_URL=https://db.kc.kuali.org/userContent/extracts/devdb_oracle.zip

:: External program locations
SET WGET_PROG="C:\Program Files\GnuWin32\bin\wget.exe"
SET ZIP_PROG="C:\Program Files\7-Zip\7z.exe"
SET SQLPLUS_PROG="C:\oraclexe\app\oracle\product\10.2.0\server\BIN\sqlplus.exe"

:: /* Advanced configuration */

:: directories
set TEMP_DIR=%TMP%
set EXTRACT_FILE=%TEMP_DIR%\db_extract.zip
set EXTRACT_DIR=%TEMP_DIR%\db_extract

:: /* The code below should not need to be changed */

SET DBA_CMD=dba
SET UNT_CMD=unt
SET ALL_CMD=all
SET EMB_CMD=emb

SET WGET_OPTS=--no-check-certificate

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
set DB_USER=%DBA_USER%
set DB_PASS=%DBA_PASS%
set SCRIPT_NAME=oracle_bundled
call :UPDATE
GOTO :SUCCESS

:UNT
set DB_USER=%UNT_USER%
set DB_PASS=%UNT_PASS%
set SCRIPT_NAME=oracle_test
call :UPDATE
GOTO :SUCCESS

:ALL
set DB_USER=%DBA_USER%
set DB_PASS=%DBA_PASS%
set SCRIPT_NAME=oracle_bundled
call :UPDATE

set DB_USER=%UNT_USER%
set DB_PASS=%UNT_PASS%
set SCRIPT_NAME=oracle_test
call :UPDATE
GOTO :SUCCESS

:EMB
set DB_USER=%EMB_CLIENT_USER%
set DB_PASS=%EMB_CLIENT_PASS%
set SCRIPT_NAME=oracle_client
call :UPDATE

set DB_USER=%EMB_SERVER_USER%
set DB_PASS=%EMB_SERVER_PASS%
set SCRIPT_NAME=oracle_server
call :UPDATE
GOTO :SUCCESS

::args: DB_USER, DB_PASS, SCRIPT_NAME
:UPDATE
call :CLEANUP
%WGET_PROG% %WGET_OPTS% -O %EXTRACT_FILE% %SCRIPT_URL%
%ZIP_PROG% x %EXTRACT_FILE% -y -o%EXTRACT_DIR%
cd %EXTRACT_DIR%\oracle\
%SQLPLUS_PROG% -S %DB_USER%/%DB_PASS% @%SCRIPT_NAME%
%CD_BASE_DIR%
call :CLEANUP

SET UPDATED=%DB_USER% %UPDATED%

GOTO :EOF

:CLEANUP
del /F /Q %EXTRACT_FILE%
rmdir /s /q %EXTRACT_DIR%
GOTO :EOF

:SUCCESS
ECHO.
ECHO [%UPDATED%] has been updated
ECHO.
GOTO :END

:END
