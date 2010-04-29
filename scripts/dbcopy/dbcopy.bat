:: This batch file downloads and applies the database schema and data of
:: Kuali Coeus' DBA and/or UNT databases in an Microsoft Windows environment.
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

:: Your local database creditentials
SET DBA_USER=KRADBA
SET DBA_PASS=mypassword
SET UNT_USER=KRAUNT
SET UNT_PASS=mypassword

SET EMB_CLIENT_USER=KCDEV
SET EMB_CLIENT_PASS=mypassword
SET EMB_SERVER_USER=RICEDEV
SET EMB_SERVER_PASS=mypassword

:: URL of SQL scrips
SET DBA_SCRIPT_URL=https://ci.kc.kuali.net/userContent/dba/dbadb_oracle.zip
SET UNT_SCRIPT_URL=https://ci.kc.kuali.net/userContent/unt/untdb_oracle.zip
SET EMB_CLIENT_SCRIPT_URL=https://ci.kc.kuali.org/userContent/embedded/embeddeddb_client.zip
SET EMB_SERVER_SCRIPT_URL=http://ci.kc.kuali.org/userContent/embedded/embeddeddb_server.zip

:: External program locations
SET WGET_PROG=C:\java\db\update\wget.exe
SET ZIP_PROG=C:\Progra~1\7-Zip\7z.exe

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
GOTO END

:DBA
%WGET_PROG% %WGET_OPTS% -O tmp.zip %DBA_SCRIPT_URL%
rmdir /s /q %DBA_SQL_DIR%
%ZIP_PROG% x tmp.zip -y -o%DBA_SQL_DIR%
cd %DBA_SQL_DIR%\sql\oracle\
SQLPLUS %DBA_USER%/%DBA_PASS% @oracle.sql
%CD_BASE_DIR%

ECHO.
ECHO DBA has been updated
ECHO.
GOTO END

:UNT
%WGET_PROG% %WGET_OPTS% -O tmp.zip %UNT_SCRIPT_URL%
rmdir /s /q %UNT_SQL_DIR%
%ZIP_PROG% x tmp.zip -y -o%UNT_SQL_DIR%
cd %UNT_SQL_DIR%\sql\oracle\
SQLPLUS %UNT_USER%/%UNT_PASS% @oracle.sql
%CD_BASE_DIR%

ECHO.
ECHO UNT has been updated
ECHO.
GOTO END

:ALL
%WGET_PROG% %WGET_OPTS% -O tmp.zip %DBA_SCRIPT_URL%
rmdir /s /q %DBA_SQL_DIR%
%ZIP_PROG% x tmp.zip -y -o%DBA_SQL_DIR%
cd %DBA_SQL_DIR%\sql\oracle\
SQLPLUS %DBA_USER%/%DBA_PASS% @oracle.sql
%CD_BASE_DIR%

%WGET_PROG% %WGET_OPTS% -O tmp.zip %UNT_SCRIPT_URL%
rmdir /s /q %UNT_SQL_DIR%
%ZIP_PROG% x tmp.zip -y -o%UNT_SQL_DIR%
cd %UNT_SQL_DIR%\sql\oracle\
SQLPLUS %UNT_USER%/%UNT_PASS% @oracle.sql
%CD_BASE_DIR%

ECHO.
ECHO DBA and UNT has been updated
ECHO.
GOTO END

:EMB
%WGET_PROG% %WGET_OPTS% -O tmp.zip %EMB_CLIENT_SCRIPT_URL%
rmdir /s /q %EMB_CLIENT_SQL_DIR%
%ZIP_PROG% x tmp.zip -y -o%EMB_CLIENT_SQL_DIR%
cd %EMB_CLIENT_SQL_DIR%\sql\oracle\
SQLPLUS %EMB_CLIENT_USER%/%EMB_CLIENT_PASS% @oracle.sql
%CD_BASE_DIR%

%WGET_PROG% %WGET_OPTS% -O tmp.zip %EMB_SERVER_SCRIPT_URL%
rmdir /s /q %EMB_SERVER_SQL_DIR%
%ZIP_PROG% x tmp.zip -y -o%EMB_SERVER_SQL_DIR%
cd %EMB_SERVER_SQL_DIR%\sql\oracle\
SQLPLUS %EMB_SERVER_USER%/%EMB_SERVER_PASS% @oracle.sql
%CD_BASE_DIR%

ECHO.
ECHO EMBEDDED CLIENT and EMBEDDED SERVER has been updated
ECHO.
GOTO END

:END