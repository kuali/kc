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
if /i "%dbtype%" == "ORACLE" goto User
if /i "%dbtype%" == "MYSQL" goto User
echo Invalid Database Type <%dbtype%>
goto dbtype

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

:DBSvrNm
set /p DBSvrNm="Enter Database TNS/schema Name <%DBSvrNm%>:"
if "%DBSvrNm%" == "" (   
echo ---------------------------------
echo Database TNS/schema Name must be entered
echo ---------------------------------
goto DBSvrNm
)

:RICE
if /i "%mode%" == "BUNDLE" (
set Riceun="%un%"
set Ricepw="%pw%"
set RiceDBSvrNm="%DBSvrNm%"
goto INSTALL
)
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

:RiceDBSvrNm
set /p RiceDBSvrNm="Enter Rice Database TNS/schema Name <%RiceDBSvrNm%>:"
if "%RiceDBSvrNm%" == "" (   
echo --------------------------------------
echo Rice Database TNS/schema Name must be entered
echo --------------------------------------
goto RiceDBSvrNm
)

:Install
goto %dbtype%

:ORACLE
cd KC-RELEASE-3_1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC-RELEASE-3_1-Demo-ORACLE.sql		
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_1-Demo-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1-Demo-ORACLE.sql
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_1_1-Demo-ORACLE.sql
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_2-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_2-Demo-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_2-Demo-ORACLE.sql
move *.log ../LOGS
cd ..

goto FINISH

:MYSQL
cd KC-RELEASE-3_1-SCRIPT
mysql -u %un% -p%pw% -D %un% -s -f < KRC-RELEASE-3_1-Demo-MYSQL.sql > KRC-RELEASE-3_1-Demo-MYSQL-Install.log 2>&1
mysql -u %un% -p%pw% -D %un% -s -f < KC-RELEASE-3_1-Demo-MYSQL.sql > KC-RELEASE-3_1-Demo-MYSQL-Install.log
mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s -f < KR-RELEASE-3_1-Demo-MYSQL.sql > KR-RELEASE-3_1-Demo-MYSQL-Install.log 2>&1
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_1-SCRIPT
mysql -u %un% -p%pw% -D %un% -s -f < KC-RELEASE-3_1_1-Demo-MYSQL.sql > KC-RELEASE-3_1_1-Demo-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

cd KC-RELEASE-3_2-SCRIPT
mysql -u %un% -p%pw% -D %un% -s -f < KC-RELEASE-3_2-Demo-MYSQL.sql > KC-RELEASE-3_2-Demo-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %Riceun% -s -f < KR-RELEASE-3_2-Demo-MYSQL.sql > KR-RELEASE-3_2-Demo-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

goto FINISH

:FINISH
cd LOGS
Echo Review log files for errors during database install.
dir *.log
goto end

:usage
Echo USAGE:
Echo KC_Install_Demo.bat
Echo You will be prompted for the following:
Echo    - Mode = Choose one: bundle, embed
Echo       - bundle = Rice installed with KC client tables
Echo       - embed = Rice installed in a separate schema 
Echo    - DB_Type = Choose one: oracle, mysql
Echo    - un = The kc Database schema name to install database scripts to (bundled rice goes here too).
Echo    - pw = the password for username
Echo    - DB_svr_name = the TNS name used to locate the database server where kc schema is located (Oracle Only)
Echo    - riceun = The rice Database schema name to install embedded rice database scripts to.
Echo    - ricepw = the password for riceusername
Echo    - riceDB_svr_name = the TNS name used to locate the database server where rice schema is located (Oracle Only)
:end
