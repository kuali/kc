@echo off
if "%1" == "" goto USAGE
if "%2" == "" goto USAGE
if "%3" == "" goto USAGE
goto usage
cd KC-RELEASE-1_1_1-PATCH-SCRIPT
sqlplus %1/%2@%3 @KCRA-Release-1_1-1_1_1-Pre-install-check.sql
move *.log ..
cd ..
Echo review files created beginning with install_kc_release-1_1_1-Patch_*.log for items requiring attention before patch is applied.
dir *.log
goto end
:usage
Echo USAGE:
Echo KC_Pre-Install_Check.bat username password DB_server_name
Echo    - username = The Database schema name to install database scripts to.
Echo    - password = the password for username
Echo    - DB_server_name = the name used to locate the database server where scripts are stored
:end
