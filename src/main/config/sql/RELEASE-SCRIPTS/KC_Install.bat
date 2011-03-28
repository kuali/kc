@echo off
if NOT EXIST "LOGS" mkdir LOGS
:mode
set /p mode="Enter Rice Mode (BUNDLE, EMBED) <%mode%>: "
if /i "%mode%" == "BUNDLE" goto DBType
if /i "%mode%" == "EMBED" goto InstRice
echo invalid Rice Mode entered <%mode%>
goto mode
:InstRice
set /p InstRice="Install/Upgrade Embedded Rice Server Side (Y,N) <%InstRice%>: "
if /i "%InstRice%" == "Y" goto DBType
if /i "%InstRice%" == "N" goto DBType
echo Invalid Response <%InstRice%>
goto InstRice

:DBType
set /p dbtype="Enter Database Type (ORACLE,MYSQL) <%dbtype%>: "
if /i "%dbtype%" == "ORACLE" goto Version
if /i "%dbtype%" == "MYSQL" goto Version
echo Invalid Database Type <%dbtype%>
goto dbtype

:Version
set /p Version="Enter Version (NEW, 2.0, 3.0, 3.0.1) <%Version%>: "
if /i "%Version%" == "NEW" goto User
if /i "%Version%" == "2.0" goto User
if /i "%Version%" == "3.0" goto User
if /i "%Version%" == "3.0.1" goto User
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
goto RicePassword
)

if /i "%dbtype%" == "MYSQL" goto INSTALL
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
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KR-Release-1_0_2-Server-Oracle-Install.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-2_0-Base-Bundled-Oracle-Install.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-2_0-Base-Rice-Oracle-Install.sql
)
if /i "%mode%%InstRice%" == "EMBEDY" sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-1_0_2-Server-Oracle-Install.sql
if /i "%mode%" == "EMBED" (
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KR-Release-1_0_2-Client-Oracle-Install.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-2_0-Base-Bundled-Oracle-Install.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KC-Release-2_0-Base-Rice-Oracle-Install.sql
)
move *.log ../LOGS
cd ..

:2.0ORACLE
cd KC-RELEASE-3_0-SCRIPT
if /i "%mode%" == "BUNDLE" (
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < RICE-1_0_2-1_0_3\update_final_oracle.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-2_0-3_0-Upgrade-Oracle-Install.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KR-Release-2_0-3_0-Upgrade-Oracle-Install.sql
)
if /i "%mode%%InstRice%" == "EMBEDY" sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < RICE-1_0_2-1_0_3\update_final_oracle.sql
if /i "%mode%" == "EMBED" (
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < RICE-1_0_2-1_0_3\update_client_final_oracle.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-2_0-3_0-Upgrade-Oracle-Install.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-2_0-3_0-Upgrade-Oracle-Install.sql
)
move *.log ../LOGS
cd ..

:3.0ORACLE
cd KC-RELEASE-3_0_1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
move *.log ../LOGS
cd ..

:3.0.1ORACLE
cd POST-RELEASE-3_0_1-SCRIPT
if /i "%mode%" == "BUNDLE" sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < POST-3_0_1-KR-2011-01-26-ORACLE.sql
if /i "%mode%%InstRice%" == "EMBEDY" sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < POST-3_0_1-KR-2011-01-26-ORACLE.sql
move *.log ../LOGS
cd ..

goto FINISH

:NEWMYSQL
cd KC-RELEASE-2_0-SCRIPT
if /i "%mode%" == "BUNDLE" (
mysql -u %un% -p%pw% -D %un% -s -f < KR-Release-1_0_2-Server-MySql-Install.sql > KC-Release-1_0_2-Server-MySql-Install.log 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KC-Release-2_0-Base-Bundled-MySql-Install.sql > KC-Release-2_0-Base-Bundled-MySql-Install.log 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KC-Release-2_0-Base-Rice-MySql-Install.sql > KC-Release-2_0-Base-Rice-MySql-Install.log 2>&1
)
if /i "%mode%%InstRice%" == "EMBEDY" mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s < KR-Release-1_0_2-Server-MySql-Install.sql > KR-Release-1_0_2-Server-MySql-Install.log 2>&1
if /i "%mode%" == "EMBED" (
mysql -u %un% -p%pw% -D %un% -s -f < KR-Release-1_0_2-Client-MySql-Install.sql > KR-Release-1_0_2-Client-MySql-Install.log 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KC-Release-2_0-Base-Bundled-MySql-Install.sql > KC-Release-2_0-Base-Bundled-MySql-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s -f < KC-Release-2_0-Base-Rice-MySql-Install.sql > KC-Release-2_0-Base-Rice-MySql-Install.log 2>&1
)
move *.log ../LOGS
cd ..

:2.0MYSQL
cd KC-RELEASE-3_0-SCRIPT
if /i "%mode%" == "BUNDLE" (
mysql -u %un% -p%pw% -D %un% -s -f < RICE-1_0_2-1_0_3/update_final_mysql.sql > update_final_mysql.log 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KC-Release-2_0-3_0-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KR-Release-2_0-3_0-Upgrade-MySql-Install.sql > KR-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
)
if /i "%mode%%InstRice%" == "EMBEDY" mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s -f < RICE-1_0_2-1_0_3/update_final_mysql.sql > update_final_mysql.log 2>&1
if /i "%mode%" == "EMBED" (
mysql -u %un% -p%pw% -D %un% -s -f < RICE-1_0_2-1_0_3/update_client_final_mysql.sql > update_client_final_mysql.sql 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KC-Release-2_0-3_0-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s -f < KR-Release-2_0-3_0-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
)
move *.log ../LOGS
cd ..

:3.0MYSQL
cd KC-RELEASE-3_0-SCRIPT
mysql -u %un% -p%pw% -D %un% -s -f < KC-Release-3_0-3_0_1-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
move *.log ../LOGS
cd ..

:3.0.1MYSQL
cd POST-RELEASE-3_0_1-SCRIPT
if /i "%mode%" == "BUNDLE" mysql -u %un% -p%pw% -D %un% -s -f < POST-3_0_1-KR-2011-01-26-MYSQL.sql
if /i "%mode%%InstRice%" == "EMBEDY" mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s -f < POST-3_0_1-KR-2011-01-26-MYSQL.sql
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
Echo           - When installing in embedded mode, you will be asked to install embedded rice server.
Echo    - DB_Type = Choose one: oracle, mysql
Echo    - Ver = Choose one: new, 2.0, 3.0
Echo       - new = New install with an empty database schema
Echo       - 2.0 = upgrading from 2.0 KC version
Echo       - 3.0 = upgrading from 3.0 KC version
Echo    - un = The kc Database schema name to install database scripts to (bundled rice goes here too).
Echo    - pw = the password for username
Echo    - DB_svr_name = the TNS name used to locate the database server where kc schema is located (Oracle Only)
Echo    - riceun = The rice Database schema name to install embedded rice database scripts to.
Echo    - ricepw = the password for riceusername
Echo    - riceDB_svr_name = the TNS name used to locate the database server where rice schema is located (Oracle Only)
:end
