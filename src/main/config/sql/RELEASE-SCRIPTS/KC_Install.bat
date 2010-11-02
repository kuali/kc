@echo off
if NOT EXIST "LOGS" mkdir LOGS
:mode
set /p mode="Enter Rice Mode (BUNDLE, EMBED) <%mode%>: "
if /i "%mode%" == "BUNDLE" goto DBType
if /i "%mode%" == "EMBED" goto DBType
echo invalid Rice Mode entered <%mode%>
goto mode

:DBType
set /p dbtype="Enter Database Type (ORACLE,MYSQL) <%dbtype%>: "
if /i "%dbtype%" == "ORACLE" goto Version
if /i "%dbtype%" == "MYSQL" goto Version
echo Invalid Database Type <%dbtype%>
goto dbtype

:Version
set /p Version="Enter Version (NEW, 2.0) <%Version%>: "
if /i "%Version%" == "NEW" goto User
if /i "%Version%" == "2.0" goto User
echo Invalid Version <%Version%>
goto Version

:User
set /p un="Enter DB Username <%un%>: "
if "%un%" == "" (
echo ------------------------
echo Username must be entered
echo ------------------------
goto User
)

:Password
set /p pw="Enter DB Password <%pw%>: "
if "%pw%" == "" (
echo ------------------------
echo Password must be entered
echo ------------------------
goto Password
)

if /i "%dbtype%" == "MYSQL" goto RICE
:DBSvrNm
set /p DBSvrNm="Enter Database TNS Name <%DBSvrNm%>:"
if "%DBSvrNm%" == "" (   
echo ---------------------------------
echo Database TNS Name must be entered
echo ---------------------------------
goto DBSvrNm
)

:RICE
if /i "%mode%" == "BUNDLE" goto INSTALL
:RiceUser
set /p Riceun="Enter Rice DB Username <%Riceun%>: "
if "%Riceun%" == "" (
echo -----------------------------
echo Rice Username must be entered
echo -----------------------------
goto RiceUser
)

:RicePassword
set /p Ricepw="Enter Rice DB Password <%Ricepw%>: "
if "%Ricepw%" == "" (
echo -----------------------------
echo Rice Password must be entered
echo -----------------------------
goto Password
)

if /i "%dbtype%" == "MYSQL" goto RICE
:RiceDBSvrNm
set /p RiceDBSvrNm="Enter Rice Database TNS Name <%RiceDBSvrNm%>:"
if "%RiceDBSvrNm%" == "" (   
echo --------------------------------------
echo Rice Database TNS Name must be entered
echo --------------------------------------
goto RiceDBSvrNm
)

:Install
goto %version%%dbtype%
goto usage

:NEWORACLE
cd KC-RELEASE-2_0-SCRIPT
if /i "%mode%" == "BUNDLE" (
sqlplus "%4"/"%5"@"%6" < KC-Release-2_0-Base-Bundled-Oracle-Install.sql
sqlplus "%4"/"%5"@"%6" < KR-Release-1_0_2-Bundled.sql
if /i "%mode%" == "EMBED" (
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-2_0-Embedded-Oracle-Install.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KR-Release-1_0_2-EmbeddedClient-Oracle-Install.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-1_0_2-EmbeddedServer-Oracle-Install.sql
)
echo move *.log ../LOGS
cd ..

:2.0ORACLE
cd KC-RELEASE-3_0-SCRIPT
if /i "%mode%" == "BUNDLE" (
echo sqlplus "%un%"/"%pw%"@"%DBSvrNm%" * KC-Release-2_0-3_0.sql

echo *********sqlplus "%un%"/"%pw%"@"%DBSvrNm%" * rice-102-103.sql**********************

echo sqlplus "%un%"/"%pw%"@"%DBSvrNm%" * KR-Release-2_0-3_0.sql
)
if /i "%mode%" == "EMBED" (
echo sqlplus "%un%"/"%pw%"@"%DBSvrNm%" * KC-Release-2_0-3_0.sql

echo *********sqlplus "%un%"/"%pw%"@"%DBSvrNm%" * rice-102-103Client.sql**********************
echo *********sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" * rice-102-103server.sql**********************

echo sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" * KR-Release-2_0-3_0.sql
)
echo move *.log ../LOGS
cd ..
goto FINISH

:NEWMYSQL
cd KC-RELEASE-2_0-SCRIPT
if /i "%mode%" == "BUNDLE" echo mysql -u %un% -p%pw% -D %un% -s * KC-Release-2_0-Bundled-MySql-Install.sql > KC-Release-2_0-Bundled-MySql-Install.log
if /i "%mode%" == "EMBED" (
echo mysql -u %un% -p%pw% -D %un% -s * KC-Release-2_0-Embedded-MySql-Install.sql
echo mysql -u %un% -p%pw% -D %un% -s * KR-Release-1_0_2-EmbeddedClient-MySql-Install.sql
echo mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s * KR-Release-1_0_2-EmbeddedServer-MySql-Install.sql
)
move *.log ../LOGS
cd ..

:2.0MYSQL
cd KC-RELEASE-3_0-SCRIPT
if /i "%mode%" == "BUNDLE" (
echo mysql -u %un% -p%pw% -D %un% -s * KC-Release-2_0-3_0-MySql.sql

echo *********mysql -u %un% -p%pw% -D %un% -s * rice-102-103-MySql.sql**********************

echo mysql -u %un% -p%pw% -D %un% -s * KR-Release-2_0-3_0-MySql.sql
)
if /i "%mode%" == "EMBED" (
echo mysql -u %un% -p%pw% -D %un% -s * KC-Release-2_0-3_0-MySql.sql

echo *********mysql -u %un% -p%pw% -D %un% -s * rice-102-103Client-MySql.sql**********************
echo *********mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s * rice-102-103server-MySql.sql**********************

echo mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s * KR-Release-2_0-3_0-MySql.sql
)
move *.log ../LOGS
cd ..
goto FINISH

:FINISH
cd LOGS
Echo Review log files for errors during database install.
dir *.log
goto end

:usage
Echo USAGE:
Echo KC_Install.bat
Echo You will be prompted for the following:
Echo    - Mode = Choose one: bundle, embed
Echo       - bundle = Rice installed with KC client tables
Echo       - embed = Rice installed in a separate schema 
Echo    - DB_Type = Choose one: oracle, mysql
Echo    - Ver = Choose one: new, 2.0
Echo       - new = New install with an empty database schema
Echo       - 2.0 = upgrading from 2.0 KC version
Echo    - un = The kc Database schema name to install database scripts to (bundled rice goes here too).
Echo    - pw = the password for username
Echo    - DB_svr_name = the TNS name used to locate the database server where kc schema is located (Oracle Only)
Echo    - riceun = The rice Database schema name to install embedded rice database scripts to.
Echo    - ricepw = the password for riceusername
Echo    - riceDB_svr_name = the TNS name used to locate the database server where rice schema is located (Oracle Only)
:end
