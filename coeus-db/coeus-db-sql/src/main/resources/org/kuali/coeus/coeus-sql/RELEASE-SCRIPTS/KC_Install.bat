@echo off
if NOT EXIST "LOGS" mkdir LOGS
:mode
set /p mode="Enter Rice Mode (BUNDLE, EMBED) <%mode%>: "
if /i "%mode%" == "BUNDLE" (
	set InstRice=Y
	goto DBType
)
if /i "%mode%" == "EMBED" goto InstRice
echo invalid Rice Mode entered <%mode%>
goto mode
:InstRice
set /p InstRice="Install/Upgrade Embedded Rice Server Side (KC-related Rice data will still be loaded, regardless of response) (Y,N) <%InstRice%>: "
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
set /p Version="Enter Currently Installed Version (NEW, 3.1.1, 5.0, 5.0.1, 5.1, 5.1.1, 5.2, 5.2.1) <%Version%>: "
if /i "%Version%" == "NEW" goto User
if /i "%Version%" == "3.1.1" goto User
if /i "%Version%" == "5.0" goto User
if /i "%Version%" == "5.0.1" goto User
if /i "%Version%" == "5.1" goto User
if /i "%Version%" == "5.1.1" goto User
if /i "%Version%" == "5.2" goto User
if /i "%Version%" == "5.2.1" goto User
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
set Riceun=%un%
set Ricepw=%pw%
set RiceDBSvrNm=%DBSvrNm%
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

cd KC-RELEASE-0_0_0-SCRIPT
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-0_0_0-Upgrade-ORACLE.sql
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_0-CLEAN/oracle

if /i "%version%" == "NEW" (
    if /i "%mode%" == "BUNDLE" (
    sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < oracle_server_rice.sql
    )
    if /i "%mode%%InstRice%" == "EMBEDY" (
    sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < oracle_server_rice.sql
    )
)

sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < krrelease/datasql/KR_00_SEQ_BS.sql
cd ../..

goto %version%%dbtype%
goto usage

:NEWORACLE
cd KC-RELEASE-3_0-CLEAN/oracle
if /i "%InstRice%" == "Y" (
    sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < oracle_server.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < oracle_server_SR.sql
)
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < oracle_client.sql

move *.log ../../LOGS/
cd ../..

cd KC-RELEASE-3_0_1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-3_0-3_0_1-SR-Oracle-Install.sql
)
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.sql

if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Server-Release-1_0_3-1_0_3_1-Upgrade-Oracle-Install.sql
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-Release-3_0_1-3_1_S1-SR-Oracle-Install.sql
)

move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP2-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_SP2-SR-ORACLE.sql
)
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP3-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_SP3-SR-ORACLE.sql
)	

move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP4-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_1_SP4-Upgrade-ORACLE.sql

if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < RICE-1_0_3_1-1_0_3_2-Upgrade-ORACLE.sql
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_SP4-SR-ORACLE.sql
)

move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_1_1-Upgrade-ORACLE.sql
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_1-Upgrade-ORACLE.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_1_1-SR-ORACLE.sql
)
move *.log ../LOGS/
cd .. 

:3.1.1ORACLE
cd KC-RELEASE-3_2-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-3_2-Upgrade-ORACLE.sql
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_2-Upgrade-ORACLE.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-3_2-SR-ORACLE.sql
)
move *.log ../LOGS/
cd .. 


cd KC-RELEASE-4_0-SCRIPT
if /i "%mode%" == "EMBED" (
	sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC_RICE-RELEASE-4_0-Upgrade-ORACLE.sql
)

sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC_RICE-RELEASE-4_0-Upgrade-ORACLE.sql

if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR_RICE-RELEASE-4_0-Upgrade-ORACLE.sql
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-4_0-Upgrade-ORACLE.sql
) else (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-4_0-SR-ORACLE.sql
)

sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-4_0-Upgrade-ORACLE.sql

move *.log ../LOGS/
cd .. 

cd KC-RELEASE-5_0-SCRIPT

if /i "%mode%" == "EMBED" (
	sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC_RICE-RELEASE-5_0-Upgrade-ORACLE.sql
)

if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR_RICE-RELEASE-5_0-Upgrade-ORACLE.sql
)

sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-5_0-Upgrade-ORACLE.sql
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-5_0-Upgrade-ORACLE.sql

move *.log ../LOGS/
cd .. 

:5.0ORACLE
cd KC-RELEASE-5_0_1-SCRIPT

sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-5_0_1-Upgrade-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-5_0_1-Upgrade-ORACLE.sql
move *.log ../LOGS/
cd ..

:5.0.1ORACLE
cd KC-RELEASE-5_1_0-SCRIPT

if /i "%mode%" == "EMBED" (
	sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC_RICE-RELEASE-5_1_0-Upgrade-ORACLE.sql
)
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR_RICE-RELEASE-5_1_0-Upgrade-ORACLE.sql
)
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-5_1_0-Upgrade-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-5_1_0-Upgrade-ORACLE.sql
move *.log ../LOGS
cd ..

:5.1ORACLE
cd KC-RELEASE-5_1_1-SCRIPT

if /i "%mode%" == "EMBED" (
	sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC_RICE-RELEASE-5_1_1-Upgrade-ORACLE.sql
)
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR_RICE-RELEASE-5_1_1-Upgrade-ORACLE.sql
)
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-5_1_1-Upgrade-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-5_1_1-Upgrade-ORACLE.sql
move *.log ../LOGS
cd ..

:5.1.1ORACLE
cd KC-RELEASE-5_2_0-SCRIPT

if /i "%mode%" == "EMBED" (
	sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC_RICE-RELEASE-5_2_0-Upgrade-ORACLE.sql
)
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR_RICE-RELEASE-5_2_0-Upgrade-ORACLE.sql
)
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-5_2_0-Upgrade-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-5_2_0-Upgrade-ORACLE.sql
move *.log ../LOGS
cd ..

:5.2ORACLE
cd KC-RELEASE-5_2_1-SCRIPT
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-5_2_1-Upgrade-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-5_2_1-Upgrade-ORACLE.sql
move *.log ../LOGS
cd ..

:5.2.1ORACLE
cd KC-RELEASE-6_0_0-SCRIPT

if /i "%mode%" == "EMBED" (
	sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KRC_RICE-RELEASE-6_0_0-Upgrade-ORACLE.sql
)
if /i "%InstRice%" == "Y" (
	sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR_RICE-RELEASE-6_0_0-Upgrade-ORACLE.sql
)
sqlplus "%un%"/"%pw%"@"%DBSvrNm%" < KC-RELEASE-6_0_0-Upgrade-ORACLE.sql
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-6_0_0-Upgrade-ORACLE.sql
move *.log ../LOGS
cd ..

cd KC-RELEASE-99_9_9-SCRIPT
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < KR-RELEASE-99_9_9-Upgrade-ORACLE.sql
move *.log ../LOGS/
cd ..

cd KC-RELEASE-3_0-CLEAN/oracle
sqlplus "%Riceun%"/"%Ricepw%"@"%RiceDBSvrNm%" < krrelease/datasql/KR_00_CLEAN_SEQ_BS.sql
move *.log ../../LOGS/
cd ../..

goto FINISH

:MYSQL

cd KC-RELEASE-0_0_0-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-0_0_0-Upgrade-MYSQL.sql > KC-RELEASE-0_0_0-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-0_0_0-Upgrade-MYSQL.sql > KR-RELEASE-0_0_0-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_0-CLEAN/mysql

if /i "%version%" == "NEW" (
    if /i "%mode%" == "BUNDLE" (
    mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < mysql_server_rice.sql > KC-Release-3_0-Clean-Server-Rice-Mysql-Install.log 2>&1
    )
    if /i "%mode%%InstRice%" == "EMBEDY" (
    mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < mysql_server_rice.sql > KC-Release-3_0-Clean-Server-Rice-Mysql-Install.log 2>&1
    )
)

mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < krrelease/datasql/KR_00_SEQ_BS.sql > KR_SEQ_BS-Mysql-Install.log 2>&1

move *.log ../../LOGS/
cd ../..

goto %version%%dbtype%
goto usage

:NEWMYSQL
cd KC-RELEASE-3_0-CLEAN/mysql
if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < mysql_server.sql > KC-Release-3_0-Clean-Server-Mysql-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < mysql_server_SR.sql > KC-Release-3_0-Clean-Server-SR-Mysql-Install.log 2>&1
)
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < mysql_client.sql > KC-Release-3_0-Clean-Client-Mysql-Install.log 2>&1

move *.log ../../LOGS
cd ../..

cd KC-RELEASE-3_0_1-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-Release-3_0-3_0_1-Upgrade-Mysql-Install.sql > KC-Release-3_0-3_0_1-Upgrade-Mysql-Install.log 2>&1
if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-Release-3_0-3_0_1-Upgrade-Mysql-Install.sql > KR-Release-3_0-3_0_1-Upgrade-Mysql-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-Release-3_0-3_0_1-SR-Mysql-Install.sql > KR-Release-3_0-3_0_1-SR-Mysql-Install.log 2>&1
)
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP1-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.sql > KC-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.log 2>&1

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-Server-Release-1_0_3-1_0_3_1-Upgrade-Mysql-Install.sql > KR-Server-Release-1_0_3_1-Upgrade-Mysql-Install.log 2>&1
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.sql > KR-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-Release-3_0_1-3_1_S1-SR-Mysql-Install.sql > KR-Release-3_0_1-3_1_S1-SR-Mysql-Install.log 2>&1
)

move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP2-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_SP2-SR-MYSQL.sql > KR-RELEASE-3_1_SP2-SR-MYSQL-Install.log 2>&1
)
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP3-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_SP3-SR-MYSQL.sql > KR-RELEASE-3_1_SP3-SR-MYSQL-Install.log 2>&1
)
move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_SP4-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC-RELEASE-3_1_SP4-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-3_1_SP4-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < RICE-1_0_3_1-1_0_3_2-Upgrade-MYSQL.sql > RICE-1_0_3_1-1_0_3_2-Upgrade-MYSQL-Install.log 2>&1
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_SP4-Upgrade-MYSQL.sql  > KR-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_SP4-SR-MYSQL.sql  > KR-RELEASE-3_1_SP4-SR-MYSQL-Install.log 2>&1
)

move *.log ../LOGS
cd ..

cd KC-RELEASE-3_1_1-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-3_1_1-Upgrade-MYSQL.sql > KC-RELEASE-3_1_1-Upgrade-MYSQL-Install.log 2>&1
if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_1-Upgrade-MYSQL.sql > KR-RELEASE-3_1_1-Upgrade-MYSQL-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_1_1-SR-MYSQL.sql > KR-RELEASE-3_1_1-SR-MYSQL-Install.log 2>&1
)
move *.log ../LOGS/
cd ..

:3.1.1ORACLE
cd KC-RELEASE-3_2-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-3_2-Upgrade-MYSQL.sql > KC-RELEASE-3_2-Upgrade-MYSQL-Install.log 2>&1
if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_2-Upgrade-MYSQL.sql > KR-RELEASE-3_2-Upgrade-MYSQL-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-3_2-SR-MYSQL.sql > KR-RELEASE-3_2-SR-MYSQL-Install.log 2>&1
)
move *.log ../LOGS/
cd ..


cd KC-RELEASE-4_0-SCRIPT

if /i "%mode%" == "EMBED" (
	mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC_RICE-RELEASE-4_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
)

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC_RICE-RELEASE-4_0-Upgrade-MYSQL.sql > KC_RICE-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR_RICE-RELEASE-4_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-4_0-Upgrade-MYSQL.sql > KR-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
) else (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-4_0-SR-MYSQL.sql > KR-RELEASE-4_0-SR-MYSQL-Install.log 2>&1
)
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-4_0-Upgrade-MYSQL.sql > KC-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1

move *.log ../LOGS/
cd ..

cd KC-RELEASE-5_0-SCRIPT

if /i "%mode%" == "EMBED" (
	mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC_RICE-RELEASE-5_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
)

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR_RICE-RELEASE-5_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
)

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-5_0-Upgrade-MYSQL.sql > KC-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-5_0-Upgrade-MYSQL.sql > KR-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

:5.0MYSQL
cd KC-RELEASE-5_0_1-SCRIPT

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-5_0_1-Upgrade-MYSQL.sql > KC-RELEASE-5_0_1-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-5_0_1-Upgrade-MYSQL.sql > KR-RELEASE-5_0_1-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

:5.0.1MYSQL
cd KC-RELEASE-5_1_0-SCRIPT

if /i "%mode%" == "EMBED" (
	mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC_RICE-RELEASE-5_1_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
)

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR_RICE-RELEASE-5_1_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
)

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-5_1_0-Upgrade-MYSQL.sql > KC-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-5_1_0-Upgrade-MYSQL.sql > KR-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

:5.1MYSQL
cd KC-RELEASE-5_1_1-SCRIPT

if /i "%mode%" == "EMBED" (
	mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC_RICE-RELEASE-5_1_1-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
)

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR_RICE-RELEASE-5_1_1-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
)

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-5_1_1-Upgrade-MYSQL.sql > KC-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-5_1_1-Upgrade-MYSQL.sql > KR-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

:5.1.1MYSQL
cd KC-RELEASE-5_2_0-SCRIPT

if /i "%mode%" == "EMBED" (
	mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC_RICE-RELEASE-5_2_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
)

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR_RICE-RELEASE-5_2_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
)

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-5_2_0-Upgrade-MYSQL.sql > KC-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-5_2_0-Upgrade-MYSQL.sql > KR-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

:5.2MYSQL
cd KC-RELEASE-5_2_1-SCRIPT
mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-5_2_1-Upgrade-MYSQL.sql > KC-RELEASE-5_2_1-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-5_2_1-Upgrade-MYSQL.sql > KR-RELEASE-5_2_1-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

:5.2.1MYSQL
cd KC-RELEASE-6_0_0-SCRIPT

if /i "%mode%" == "EMBED" (
	mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KRC_RICE-RELEASE-6_0_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
)

if /i "%InstRice%" == "Y" (
	mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR_RICE-RELEASE-6_0_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
)

mysql -u %un% -p%pw% -D %DBSvrNm% -s -f < KC-RELEASE-6_0_0-Upgrade-MYSQL.sql > KC-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-6_0_0-Upgrade-MYSQL.sql > KR-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

cd KC-RELEASE-99_9_9-SCRIPT
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < KR-RELEASE-99_9_9-Upgrade-MYSQL.sql > KR-RELEASE-99_9_9-Upgrade-MYSQL-Install.log 2>&1
move *.log ../LOGS/
cd ..

cd KC-RELEASE-3_0-CLEAN/mysql
mysql -u %Riceun% -p%Ricepw% -D %RiceDBSvrNm% -s -f < krrelease/datasql/KR_00_CLEAN_SEQ_BS.sql > KR_CLEAN_SEQ_BS-Mysql-Install.log 2>&1
move *.log ../../LOGS/
cd ../..

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
Echo    - Ver = Choose one: NEW, 3.1.1, ...
Echo       - NEW = New install with an empty database schema
Echo       - 3.1.1, etc. = upgrading from 3.1.1 KC version, etc.
Echo    - un = The kc Database schema name to install database scripts to (bundled rice goes here too).
Echo    - pw = the password for username
Echo    - DB_svr_name = the TNS name used to locate the database server where kc schema is located (Oracle Only)
Echo    - riceun = The rice Database schema name to install embedded rice database scripts to.
Echo    - ricepw = the password for riceusername
Echo    - riceDB_svr_name = the TNS name used to locate the database server where rice schema is located (Oracle Only)
:end
